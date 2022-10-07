package dev.ososuna.springbook.service;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import dev.ososuna.springbook.model.RegisterRequest;
import dev.ososuna.springbook.model.User;
import dev.ososuna.springbook.repository.UserRepository;
import dev.ososuna.springbook.util.UserUtil;

@Service
public class AuthService implements UserDetailsService {
  
  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;
  private final UserUtil userUtil;

  public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder, UserUtil userUtil) {
    this.userRepository = userRepository;
    this.passwordEncoder = passwordEncoder;
    this.userUtil = userUtil;
  }

  @Override
  public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
    var user = userUtil.getUserByEmail(email);

    Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
    authorities.add(new SimpleGrantedAuthority(user.getRole().toString()));

    return new org.springframework.security.core.userdetails.User(
      user.getEmail(),
      user.getPassword(),
      authorities
    );
  }

  public User register(RegisterRequest request) throws ResponseStatusException {
    if (userRepository.findByEmailAndActiveTrue(request.getEmail()).isPresent()) {
      throw new ResponseStatusException(
        HttpStatus.BAD_REQUEST,
        "Correo electrónico ya existente"
      );
    }
    if (request.getPassword().length() < 6) {
      throw new ResponseStatusException(
        HttpStatus.BAD_REQUEST,
        "La contraseña debe tener al menos 6 caracteres"
      );
    }
    User user = new User();
    BeanUtils.copyProperties(request, user);
    user.setActive(true);
    user.setPassword(passwordEncoder.encode(request.getPassword()));
    user.setRole(request.getRole());
    return userRepository.save(user);
  }
}

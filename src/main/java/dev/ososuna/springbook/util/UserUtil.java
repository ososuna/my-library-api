package dev.ososuna.springbook.util;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import dev.ososuna.springbook.model.User;
import dev.ososuna.springbook.repository.UserRepository;

@Component
public class UserUtil {
  
  private final UserRepository userRepository;

  public UserUtil(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  public User getUserByEmail(String email) {
    return userRepository.findByEmailAndActiveTrue(email).orElseThrow(() ->
      new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found")
    );
  }

  public User getUserById(Long id) {
    return userRepository.findByIdAndActiveTrue(id).orElseThrow(() ->
      new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found")
    );
  }

}

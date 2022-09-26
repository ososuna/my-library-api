package dev.ososuna.mylibrary.service;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import dev.ososuna.mylibrary.model.User;
import dev.ososuna.mylibrary.repository.UserRepository;

@Service
public class UserService {
  
  private final UserRepository userRepository;

  public UserService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  public List<User> getAllUsers() {
    return this.userRepository.findAllByActiveTrue();
  }

  public User getUserByEmail(String email) {
    return userRepository.findByEmailAndActiveTrue(email)
      .orElseThrow(() ->
        new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found")
      );
  }

  public User createUser(User user) {
    userRepository.findByEmailAndActiveTrue(user.getEmail()).ifPresent(action -> {
      throw new ResponseStatusException(
        HttpStatus.BAD_REQUEST,
        "User with email " + user.getEmail() + " already exists"
      );
    });
    return userRepository.save(user);
  }

  public User deleteUser(Long id) {
    var user = userRepository.findById(id).orElseThrow(() ->
      new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found")
    );
    user.setActive(false);
    return userRepository.save(user);
  }
}

package dev.ososuna.springbook.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import dev.ososuna.springbook.model.LoginRequest;
import dev.ososuna.springbook.model.User;
import dev.ososuna.springbook.repository.UserRepository;

@Service
public class UserService {
  
  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;
  private final S3Service s3Service;

  public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, S3Service s3Service) {
    this.userRepository = userRepository;
    this.passwordEncoder = passwordEncoder;
    this.s3Service = s3Service;
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
    user.setPassword(passwordEncoder.encode(user.getPassword()));
    return userRepository.save(user);
  }

  public User loginUser(LoginRequest loginRequest) {
    return userRepository.findByEmailAndPasswordAndActiveTrue(
      loginRequest.getEmail(),
      loginRequest.getPassword()
    ).orElseThrow(() ->
      new ResponseStatusException(HttpStatus.NOT_FOUND, "Invalid credentials")
    );
  }

  public User updateUser(Long id, User user, MultipartFile file) {

    User userToUpdate = userRepository.findByIdAndActiveTrue(id)
      .orElseThrow(() ->
        new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found")
      );
    
    if (!userToUpdate.getEmail().equals(user.getEmail())) {
      userRepository.findByEmailAndActiveTrue(user.getEmail()).ifPresent(action -> {
        throw new ResponseStatusException(
          HttpStatus.BAD_REQUEST,
          "Already existing email"
        );
      });
    }

    if (file != null) {
      Map<String, String> fileUploaded = s3Service.uploadFile(file);
      user.setProfileImageUrl(fileUploaded.get("url"));
    } else {
      user.setProfileImageUrl(userRepository.findById(id).get().getProfileImageUrl());
    }

    BeanUtils.copyProperties(user, userToUpdate, "id", "password", "active", "role", "createdBy", "createdBy", "createdDate", "updatedDate");
    
    return userRepository.save(userToUpdate);
  }

  public User deleteUser(Long id) {
    var user = userRepository.findById(id).orElseThrow(() ->
      new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found")
    );
    user.setActive(false);
    return userRepository.save(user);
  }
}

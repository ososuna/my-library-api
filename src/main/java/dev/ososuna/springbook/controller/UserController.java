package dev.ososuna.springbook.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import dev.ososuna.springbook.model.LoginRequest;
import dev.ososuna.springbook.model.User;
import dev.ososuna.springbook.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

  private final UserService userService;

  public UserController(UserService userService) {
    this.userService = userService;
  }

  @GetMapping("/all")
  public ResponseEntity<List<User>> getAllUsers() {
    return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
  }

  @GetMapping
  public ResponseEntity<User> getUserByEmail(@RequestParam String email) {
    return new ResponseEntity<>(userService.getUserByEmail(email), HttpStatus.OK);
  }

  @PostMapping
  public ResponseEntity<User> createUser(@RequestBody User user) {
    return new ResponseEntity<>(userService.createUser(user), HttpStatus.CREATED);
  }

  @PostMapping("/login")
  public ResponseEntity<User> login(@RequestBody LoginRequest loginRequest) {
    return new ResponseEntity<>(userService.loginUser(loginRequest), HttpStatus.OK);
  }

  @PutMapping("/{id}")
  public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestPart User user, @RequestPart(name = "file", required = false) MultipartFile file) {
    return new ResponseEntity<>(userService.updateUser(id, user, file), HttpStatus.OK);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<User> deleteUser(@PathVariable Long id) {
    return new ResponseEntity<>(userService.deleteUser(id), HttpStatus.OK);
  }
}

package dev.ososuna.springbook.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RegisterRequest {
  private String firstName;
  private String lastName;
  private String email;
  private String password;
  private String role;
  private int age;
}

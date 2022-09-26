package dev.ososuna.mylibrary.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.ososuna.mylibrary.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
  Optional<User> findByEmail(String email);
}

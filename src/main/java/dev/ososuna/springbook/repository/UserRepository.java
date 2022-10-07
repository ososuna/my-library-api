package dev.ososuna.springbook.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dev.ososuna.springbook.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
  Optional<User> findByEmailAndActiveTrue(String email);
  Optional<User> findByEmailAndPasswordAndActiveTrue(String email, String password);
  Optional<User> findByIdAndActiveTrue(Long id);
  List<User> findAllByActiveTrue();
}

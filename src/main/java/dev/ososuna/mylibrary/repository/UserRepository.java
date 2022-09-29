package dev.ososuna.mylibrary.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dev.ososuna.mylibrary.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
  Optional<User> findByEmailAndActiveTrue(String email);
  Optional<User> findByIdAndActiveTrue(Long id);
  List<User> findAllByActiveTrue();
}

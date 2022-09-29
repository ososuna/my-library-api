package dev.ososuna.mylibrary.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dev.ososuna.mylibrary.model.Bookshelf;

@Repository
public interface BookshelfRepository extends JpaRepository<Bookshelf, Long> {
  Optional<Bookshelf> findByIdAndActiveTrue(Long id);
}

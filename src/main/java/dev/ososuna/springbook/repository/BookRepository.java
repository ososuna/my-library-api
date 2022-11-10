package dev.ososuna.springbook.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dev.ososuna.springbook.model.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
  List<Book> findAllByActiveTrue();
  List<Book> findAllByActiveTrueAndUserIdIs(Long bookId);
  Optional<Book> findByIdAndActiveTrue(Long id);
}

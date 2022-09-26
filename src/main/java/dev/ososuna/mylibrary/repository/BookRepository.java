package dev.ososuna.mylibrary.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.ososuna.mylibrary.model.Book;

public interface BookRepository extends JpaRepository<Book, Long> {
}

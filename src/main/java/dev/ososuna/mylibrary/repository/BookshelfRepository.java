package dev.ososuna.mylibrary.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.ososuna.mylibrary.model.Bookshelf;

public interface BookshelfRepository extends JpaRepository<Bookshelf, Long> {
}

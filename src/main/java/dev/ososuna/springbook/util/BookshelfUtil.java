package dev.ososuna.springbook.util;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import dev.ososuna.springbook.model.Bookshelf;
import dev.ososuna.springbook.repository.BookshelfRepository;

@Component
public class BookshelfUtil {

  private final BookshelfRepository bookshelfRepository;

  public BookshelfUtil(BookshelfRepository bookshelfRepository) {
    this.bookshelfRepository = bookshelfRepository;
  }

  public Bookshelf getBookshelfById(Long id) {
    return bookshelfRepository.findByIdAndActiveTrue(id).orElseThrow(() ->
      new ResponseStatusException(HttpStatus.NOT_FOUND, "Bookshelf not found")
    );
  }
}

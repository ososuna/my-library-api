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
import org.springframework.web.bind.annotation.RestController;

import dev.ososuna.springbook.model.Book;
import dev.ososuna.springbook.model.dto.BookDto;
import dev.ososuna.springbook.service.BookService;

@RestController
@RequestMapping("/book")
public class BookController {

  private final BookService bookService;

  public BookController(BookService bookService) {
    this.bookService = bookService;
  }
  
  @GetMapping("/all")
  public ResponseEntity<List<BookDto>> getAllBooks() {
    return ResponseEntity.ok(bookService.getAllBooks());
  }

  @GetMapping("/user/{userId}")
  public ResponseEntity<List<BookDto>> getBooksByUser(@PathVariable Long userId) {
    return ResponseEntity.ok(bookService.getBooksByUser(userId));
  }
  
  @GetMapping("/{id}")
  public ResponseEntity<Book> getBookById(@PathVariable Long id) {
    return ResponseEntity.ok(bookService.getBookById(id));
  }
  
  @PostMapping
  public ResponseEntity<Book> createBook(@RequestBody BookDto bookDto) {
    return new ResponseEntity<Book>(bookService.createBook(bookDto), HttpStatus.CREATED);
  }

  @PutMapping("/{id}")
  public ResponseEntity<Book> updateBook(@PathVariable Long id, @RequestBody BookDto bookDto) {
    return new ResponseEntity<Book>(bookService.updateBook(id, bookDto), HttpStatus.CREATED);
  }
  
  @DeleteMapping("/{id}")
  public ResponseEntity<Book> deleteBook(@PathVariable Long id) {
    return new ResponseEntity<Book>(bookService.deleteBook(id), HttpStatus.CREATED);
  }

}

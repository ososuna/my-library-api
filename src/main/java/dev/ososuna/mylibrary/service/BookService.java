package dev.ososuna.mylibrary.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import dev.ososuna.mylibrary.model.Book;
import dev.ososuna.mylibrary.model.dto.BookDto;
import dev.ososuna.mylibrary.repository.BookRepository;
import dev.ososuna.mylibrary.util.BookUtil;

@Component
public class BookService {
  
  private final BookRepository bookRepository;
  private final BookUtil bookUtil;

  public BookService(BookRepository bookRepository, BookUtil bookUtil) {
    this.bookRepository = bookRepository;
    this.bookUtil = bookUtil;
  }

  public List<BookDto> getAllBooks() {
    return bookRepository.findAllByActiveTrue()
      .stream()
      .map(bookUtil::transformBookToDto)
      .collect(Collectors.toList());
  }

  public Book getBookById(Long id) {
    return bookUtil.getBookById(id);
  }

  public Book createBook(BookDto bookDto) {
    var book = bookUtil.transformDtoToBook(bookDto);
    book.setActive(true);
    return bookRepository.save(book);
  }

  public Book updateBook(BookDto bookDto) {
    bookUtil.getBookById(bookDto.getId());
    var book = bookUtil.transformDtoToBook(bookDto);
    book.setActive(true);
    return bookRepository.save(book);
  }

  public Book deleteBook(Long id) {
    Book book = bookUtil.getBookById(id);
    book.setActive(false);
    return bookRepository.save(book);
  }
}

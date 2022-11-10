package dev.ososuna.springbook.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import dev.ososuna.springbook.model.Book;
import dev.ososuna.springbook.model.dto.BookDto;
import dev.ososuna.springbook.repository.BookRepository;
import dev.ososuna.springbook.util.BookUtil;

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

  public List<BookDto> getBooksByUser(Long userId) {
    return bookRepository.findAllByActiveTrueAndUserIdIs(userId)
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

  public Book updateBook(Long id, BookDto bookDto) {
    var book = bookUtil.getBookById(id);
    BeanUtils.copyProperties(bookDto, book);
    book.setId(id);
    book.setActive(true);
    return bookRepository.save(book);
  }

  public Book deleteBook(Long id) {
    Book book = bookUtil.getBookById(id);
    book.setActive(false);
    return bookRepository.save(book);
  }
}

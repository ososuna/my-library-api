package dev.ososuna.springbook.util;

import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import dev.ososuna.springbook.model.Book;
import dev.ososuna.springbook.model.dto.BookDto;
import dev.ososuna.springbook.repository.BookRepository;

@Component
public class BookUtil {
  
  private final BookRepository bookRepository;
  private final UserUtil userUtil;

  public BookUtil(BookRepository bookRepository, UserUtil userUtil) {
    this.bookRepository = bookRepository;
    this.userUtil = userUtil;
  }

  public Book transformDtoToBook(BookDto bookDto) {
    Book book = new Book();
    BeanUtils.copyProperties(bookDto, book);
    if (bookDto.getUserId() != null) {
      book.setUser(userUtil.getUserById(bookDto.getUserId()));
    }
    return book;
  }

  public BookDto transformBookToDto(Book book) {
    BookDto bookDto = new BookDto();
    BeanUtils.copyProperties(book, bookDto);
    if (book.getUser() != null) {
      bookDto.setUserId(book.getUser().getId());
    }
    return bookDto;
  }

  public Book getBookById(Long id) {
    return bookRepository.findByIdAndActiveTrue(id).orElseThrow(() ->
      new ResponseStatusException(HttpStatus.NOT_FOUND, "Book not found")
    );
  }  
}

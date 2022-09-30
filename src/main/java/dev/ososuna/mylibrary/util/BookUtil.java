package dev.ososuna.mylibrary.util;

import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import dev.ososuna.mylibrary.model.Book;
import dev.ososuna.mylibrary.model.dto.BookDto;
import dev.ososuna.mylibrary.repository.BookRepository;

@Component
public class BookUtil {
  
  private final BookRepository bookRepository;
  private final UserUtil userUtil;
  private final BookshelfUtil bookshelfUtil;

  public BookUtil(
    BookRepository bookRepository,
    UserUtil userUtil,
    BookshelfUtil bookshelfUtil
  ) {
    this.bookRepository = bookRepository;
    this.userUtil = userUtil;
    this.bookshelfUtil = bookshelfUtil;
  }

  public Book transformDtoToBook(BookDto bookDto) {
    Book book = new Book();
    BeanUtils.copyProperties(bookDto, book);
    if (bookDto.getCustomerId() != null) {
      book.setCustomer(userUtil.getUserById(bookDto.getCustomerId()));
    }
    book.setBookshelf(bookshelfUtil.getBookshelfById(bookDto.getBookshelfId()));
    return book;
  }

  public BookDto transformBookToDto(Book book) {
    BookDto bookDto = new BookDto();
    BeanUtils.copyProperties(book, bookDto);
    if (book.getCustomer() != null) {
      bookDto.setCustomerId(book.getCustomer().getId());
    }
    bookDto.setBookshelfId(book.getBookshelf().getId());
    return bookDto;
  }

  public Book getBookById(Long id) {
    return bookRepository.findByIdAndActiveTrue(id).orElseThrow(() ->
      new ResponseStatusException(HttpStatus.NOT_FOUND, "Book not found")
    );
  }  
}

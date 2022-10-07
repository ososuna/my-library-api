package dev.ososuna.springbook.model.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BookDto {
  private Long id;
  private String name;
  private String author;
  private String description;
  private Long bookshelfId;
  private Long customerId;
}

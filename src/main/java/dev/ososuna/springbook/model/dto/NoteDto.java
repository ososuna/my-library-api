package dev.ososuna.springbook.model.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class NoteDto {
  private Long id;
  private String name;
  private String description;
  private Long bookId;
}

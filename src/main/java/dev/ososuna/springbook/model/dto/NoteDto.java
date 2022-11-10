package dev.ososuna.springbook.model.dto;

import java.time.LocalDate;

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
  private LocalDate date;
  private Long bookId;
}

package dev.ososuna.springbook.model.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UpdateNoteDto {
  private String name;
  private String description;
}

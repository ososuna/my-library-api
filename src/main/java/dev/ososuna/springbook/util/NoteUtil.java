package dev.ososuna.springbook.util;

import org.springframework.stereotype.Component;

import dev.ososuna.springbook.model.Note;
import dev.ososuna.springbook.model.dto.NoteDto;

@Component
public class NoteUtil {
  
  private final BookUtil bookUtil;

  public NoteUtil(BookUtil bookUtil) {
    this.bookUtil = bookUtil;
  }

  public Note transformDtoToNote(NoteDto noteDto) {
    var book = bookUtil.getBookById(noteDto.getBookId());
    var note = new Note();
    note.setName(noteDto.getName());
    note.setDescription(noteDto.getDescription());
    note.setBook(book);
    return note;
  }

  public NoteDto transformNoteToDto(Note note) {
    var noteDto = new NoteDto();
    noteDto.setId(note.getId());
    noteDto.setName(note.getName());
    noteDto.setDescription(note.getDescription());
    noteDto.setBookId(note.getBook().getId());
    return noteDto;
  }

}

package dev.ososuna.springbook.util;

import java.time.LocalDate;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import dev.ososuna.springbook.model.Note;
import dev.ososuna.springbook.model.dto.NewNoteDto;
import dev.ososuna.springbook.model.dto.NoteDto;
import dev.ososuna.springbook.repository.NoteRepository;

@Component
public class NoteUtil {
  
  private final BookUtil bookUtil;
  private final NoteRepository noteRepository;

  public NoteUtil(BookUtil bookUtil, NoteRepository noteRepository) {
    this.bookUtil = bookUtil;
    this.noteRepository = noteRepository;
  }

  public Note getNoteById(Long id) {
    return noteRepository.findByIdAndActiveTrue(id).orElseThrow(() ->
      new ResponseStatusException(HttpStatus.NOT_FOUND, "Note not found")
    );
  }

  public Note transformDtoToNote(NoteDto noteDto) {
    var book = bookUtil.getBookById(noteDto.getBookId());
    var note = new Note();
    note.setName(noteDto.getName());
    note.setDescription(noteDto.getDescription());
    note.setDate(noteDto.getDate());
    note.setBook(book);
    return note;
  }

  public Note transformNewNoteDtoToNote(NewNoteDto newNoteDto) {
    var book = bookUtil.getBookById(newNoteDto.getBookId());
    var note = new Note();
    note.setName(newNoteDto.getName());
    note.setDescription(newNoteDto.getDescription());
    note.setBook(book);
    note.setDate(LocalDate.now());
    note.setActive(true);
    return note;
  }

  public NoteDto transformNoteToDto(Note note) {
    var noteDto = new NoteDto();
    noteDto.setId(note.getId());
    noteDto.setName(note.getName());
    noteDto.setDescription(note.getDescription());
    noteDto.setDate(note.getDate());
    noteDto.setBookId(note.getBook().getId());
    return noteDto;
  }

}

package dev.ososuna.springbook.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.ososuna.springbook.model.Note;
import dev.ososuna.springbook.model.dto.NoteDto;
import dev.ososuna.springbook.service.NoteService;

@RestController
@RequestMapping("/note")
public class NoteController {

  private final NoteService noteService;
  
  public NoteController(NoteService noteService) {
    this.noteService = noteService;
  }

  @GetMapping("/all")
  public ResponseEntity<List<NoteDto>> getAllNotes() {
    return new ResponseEntity<>(noteService.getAllNotes(), HttpStatus.OK);
  }

  @GetMapping("/{bookId}")
  public ResponseEntity<List<NoteDto>> getNotesByBook(@PathVariable Long bookId) {
    return new ResponseEntity<>(noteService.getNotesByBook(bookId), HttpStatus.OK);
  }

  @PostMapping
  public ResponseEntity<Note> createNote(@RequestBody NoteDto noteDto) {
    return new ResponseEntity<>(noteService.createNote(noteDto), HttpStatus.CREATED);
  }

}

package dev.ososuna.springbook.service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import dev.ososuna.springbook.model.Note;
import dev.ososuna.springbook.model.dto.NewNoteDto;
import dev.ososuna.springbook.model.dto.NoteDto;
import dev.ososuna.springbook.model.dto.UpdateNoteDto;
import dev.ososuna.springbook.repository.NoteRepository;
import dev.ososuna.springbook.util.NoteUtil;

@Service
public class NoteService {
  
  private final NoteRepository noteRepository;
  private final NoteUtil noteUtil;

  public NoteService(NoteRepository noteRepository, NoteUtil noteUtil) {
    this.noteRepository = noteRepository;
    this.noteUtil = noteUtil;
  }

  public List<NoteDto> getAllNotes() {
    return noteRepository.findAllByActiveTrue()
      .stream()
      .map(noteUtil::transformNoteToDto)
      .collect(Collectors.toList());
  }

  public Note getNoteById(Long id) {
    return noteUtil.getNoteById(id);
  }

  public List<NoteDto> getNotesByBook(Long bookId) {
    return noteRepository.findAllByActiveTrueAndBookIdIs(bookId)
      .stream()
      .map(noteUtil::transformNoteToDto)
      .collect(Collectors.toList());
  }

  public Note createNote(NewNoteDto newNoteDto) {
    var note = noteUtil.transformNewNoteDtoToNote(newNoteDto);
    return noteRepository.save(note);
  }

  public NoteDto updateNote(Long id, UpdateNoteDto updateNoteDto) {
    var note = noteUtil.getNoteById(id);
    note.setName(updateNoteDto.getName());
    note.setDescription(updateNoteDto.getDescription());
    note.setDate(LocalDate.now());
    return noteUtil.transformNoteToDto(noteRepository.save(note));
  }

  public Note deleteNote(Long id) {
    var note = noteUtil.getNoteById(id);
    note.setActive(false);
    return noteRepository.save(note);
  }

}

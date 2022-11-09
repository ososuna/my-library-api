package dev.ososuna.springbook.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import dev.ososuna.springbook.model.Note;
import dev.ososuna.springbook.model.dto.NoteDto;
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

  public Note createNote(NoteDto noteDto) {
    var book = noteRepository.save(noteUtil.transformDtoToNote(noteDto));
    book.setActive(true);
    return noteRepository.save(book);
  }

}

package dev.ososuna.springbook.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dev.ososuna.springbook.model.Note;

@Repository
public interface NoteRepository extends JpaRepository<Note, Long> {
  List<Note> findAllByActiveTrue();
  List<Note> findAllByActiveTrueAndBookIdIs(Long bookId);
  Optional<Note> findByIdAndActiveTrue(Long id);
}

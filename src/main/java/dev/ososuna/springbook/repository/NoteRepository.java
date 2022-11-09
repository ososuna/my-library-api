package dev.ososuna.springbook.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dev.ososuna.springbook.model.Note;

@Repository
public interface NoteRepository extends JpaRepository<Note, Long> {
  List<Note> findAllByActiveTrue();
}

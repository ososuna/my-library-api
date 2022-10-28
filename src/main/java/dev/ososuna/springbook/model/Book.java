package dev.ososuna.springbook.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name="book")
public class Book extends AbstractNamedEntity {
  @Column
  private String author;

  @Column
  private String description;

  @ManyToOne
  @JoinColumn(name="user_id")
  private User user;
}

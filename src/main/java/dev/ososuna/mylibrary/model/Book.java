package dev.ososuna.mylibrary.model;

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

  @ManyToOne
  @JoinColumn(name="bookshelf_id")
  private Bookshelf bookshelf;

  @ManyToOne
  @JoinColumn(name="customer_id")
  private User customer;
}

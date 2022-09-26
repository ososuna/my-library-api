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
@Table(name="bookshelf")
public class Bookshelf extends AbstractNamedEntity {
  @Column
  private String location;

  @ManyToOne
  @JoinColumn(name="owner_id")
  private User owner;
}

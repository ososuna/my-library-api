package dev.ososuna.springbook.model;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@MappedSuperclass
public abstract class AbstractSimpleEntity {
  @Id  
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(
    name="id",
    updatable=false,
    nullable=false,
    unique=true
  )
  private Long id;
}

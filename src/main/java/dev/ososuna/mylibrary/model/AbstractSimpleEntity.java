package dev.ososuna.mylibrary.model;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.hibernate.annotations.GenericGenerator;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@MappedSuperclass
public abstract class AbstractSimpleEntity {
  @Id
  @GeneratedValue(generator="uuid")
  @GenericGenerator(
    name="uuid",
    strategy="org.hibernate.id.UUIDGenerator"
  )
  @Column(
    name="id",
    updatable=false,
    nullable=false,
    unique=true
  )
  private UUID id;
}

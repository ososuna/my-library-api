package dev.ososuna.mylibrary.model;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@MappedSuperclass
public abstract class AbstractNamedEntity extends AbstractModificationAttributesEntity {
  @Column
  private String name;
}

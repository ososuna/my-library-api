package dev.ososuna.mylibrary.model;

import javax.persistence.Column;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AbstractNamedEntity extends AbstractModificationAttributesEntity {
  @Column
  private String name;
}

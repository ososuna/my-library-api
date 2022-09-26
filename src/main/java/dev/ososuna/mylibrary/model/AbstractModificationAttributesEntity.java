package dev.ososuna.mylibrary.model;

import java.time.LocalDate;

import javax.persistence.Column;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AbstractModificationAttributesEntity extends AbstractSimpleEntity {
  @Column(name="created_by")
  private String createdBy;

  @Column(name="updated_by")
  private String updatedBy;

  @Column(name="created_date")
  private LocalDate createdDate;

  @Column(name="updated_date")
  private LocalDate updatedDate;

  @Column(name="active")
  private boolean active;
}

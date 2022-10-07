package dev.ososuna.mylibrary.model.enums;

public enum Role {

  ROLE_ADMIN("ADMIN"),
  ROLE_USER("USER");


  private final String text;

  /**
   * @param text
   */
  Role(final String text) {
    this.text = text;
  }

  @Override
  public String toString() {
    return text;
  }

}

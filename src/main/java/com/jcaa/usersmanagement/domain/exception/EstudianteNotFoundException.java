package com.jcaa.usersmanagement.domain.exception;

public final class EstudianteNotFoundException extends DomainException {

  private static final String MESSAGE_BY_ID = "The estudiante with id '%s' was not found.";

  private EstudianteNotFoundException(final String message) {
    super(message);
  }

  public static EstudianteNotFoundException becauseIdWasNotFound(final String estudianteId) {
    return new EstudianteNotFoundException(String.format(MESSAGE_BY_ID, estudianteId));
  }
}

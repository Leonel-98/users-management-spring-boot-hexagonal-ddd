package com.jcaa.usersmanagement.domain.exception;

public final class InvalidEstudianteException extends DomainException {

  private static final String MESSAGE_REQUIRED_FIELD = "The estudiante field '%s' is required.";
  private static final String MESSAGE_POSITIVE_NUMBER =
      "The estudiante field '%s' must be greater than zero.";

  private InvalidEstudianteException(final String message) {
    super(message);
  }

  public static InvalidEstudianteException becauseFieldIsRequired(final String fieldName) {
    return new InvalidEstudianteException(String.format(MESSAGE_REQUIRED_FIELD, fieldName));
  }

  public static InvalidEstudianteException becauseNumberMustBePositive(final String fieldName) {
    return new InvalidEstudianteException(String.format(MESSAGE_POSITIVE_NUMBER, fieldName));
  }
}

package com.jcaa.usersmanagement.application.service;

import com.jcaa.usersmanagement.application.port.in.DeleteEstudianteUseCase;
import com.jcaa.usersmanagement.application.port.out.DeleteEstudiantePort;
import com.jcaa.usersmanagement.application.port.out.GetEstudianteByIdPort;
import com.jcaa.usersmanagement.application.service.dto.command.DeleteEstudianteCommand;
import com.jcaa.usersmanagement.domain.exception.EstudianteNotFoundException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeleteEstudianteService implements DeleteEstudianteUseCase {

  private final DeleteEstudiantePort deleteEstudiantePort;
  private final GetEstudianteByIdPort getEstudianteByIdPort;
  private final Validator validator;

  @Override
  public void execute(final DeleteEstudianteCommand command) {
    validateCommand(command);
    ensureEstudianteExists(command.id());
    deleteEstudiantePort.delete(command.id());
  }

  private void validateCommand(final DeleteEstudianteCommand command) {
    final Set<ConstraintViolation<DeleteEstudianteCommand>> violations =
        validator.validate(command);
    if (!violations.isEmpty()) {
      throw new ConstraintViolationException(violations);
    }
  }

  private void ensureEstudianteExists(final String id) {
    getEstudianteByIdPort
        .getById(id)
        .orElseThrow(() -> EstudianteNotFoundException.becauseIdWasNotFound(id));
  }
}

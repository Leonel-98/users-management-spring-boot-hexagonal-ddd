package com.jcaa.usersmanagement.application.service;

import com.jcaa.usersmanagement.application.port.in.UpdateEstudianteUseCase;
import com.jcaa.usersmanagement.application.port.out.GetEstudianteByIdPort;
import com.jcaa.usersmanagement.application.port.out.UpdateEstudiantePort;
import com.jcaa.usersmanagement.application.service.dto.command.UpdateEstudianteCommand;
import com.jcaa.usersmanagement.application.service.mapper.EstudianteApplicationMapper;
import com.jcaa.usersmanagement.domain.exception.EstudianteNotFoundException;
import com.jcaa.usersmanagement.domain.model.EstudianteModel;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UpdateEstudianteService implements UpdateEstudianteUseCase {

  private final UpdateEstudiantePort updateEstudiantePort;
  private final GetEstudianteByIdPort getEstudianteByIdPort;
  private final Validator validator;

  @Override
  public EstudianteModel execute(final UpdateEstudianteCommand command) {
    validateCommand(command);
    ensureEstudianteExists(command.id());

    final EstudianteModel estudianteToUpdate =
        EstudianteApplicationMapper.fromUpdateCommandToModel(command);
    return updateEstudiantePort.update(estudianteToUpdate);
  }

  private void validateCommand(final UpdateEstudianteCommand command) {
    final Set<ConstraintViolation<UpdateEstudianteCommand>> violations =
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

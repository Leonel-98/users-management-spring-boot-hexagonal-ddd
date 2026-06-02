package com.jcaa.usersmanagement.application.service;

import com.jcaa.usersmanagement.application.port.in.CreateEstudianteUseCase;
import com.jcaa.usersmanagement.application.port.out.SaveEstudiantePort;
import com.jcaa.usersmanagement.application.service.dto.command.CreateEstudianteCommand;
import com.jcaa.usersmanagement.application.service.mapper.EstudianteApplicationMapper;
import com.jcaa.usersmanagement.domain.model.EstudianteModel;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import java.util.Set;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateEstudianteService implements CreateEstudianteUseCase {

  private final SaveEstudiantePort saveEstudiantePort;
  private final Validator validator;

  @Override
  public EstudianteModel execute(final CreateEstudianteCommand command) {
    validateCommand(command);

    final String id = UUID.randomUUID().toString();
    final EstudianteModel estudianteToSave =
        EstudianteApplicationMapper.fromCreateCommandToModel(id, command);
    return saveEstudiantePort.save(estudianteToSave);
  }

  private void validateCommand(final CreateEstudianteCommand command) {
    final Set<ConstraintViolation<CreateEstudianteCommand>> violations =
        validator.validate(command);
    if (!violations.isEmpty()) {
      throw new ConstraintViolationException(violations);
    }
  }
}

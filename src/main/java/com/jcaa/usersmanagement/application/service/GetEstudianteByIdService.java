package com.jcaa.usersmanagement.application.service;

import com.jcaa.usersmanagement.application.port.in.GetEstudianteUseCase;
import com.jcaa.usersmanagement.application.port.out.GetEstudianteByIdPort;
import com.jcaa.usersmanagement.application.service.dto.query.GetEstudianteByIdQuery;
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
public class GetEstudianteByIdService implements GetEstudianteUseCase {

  private final GetEstudianteByIdPort getEstudianteByIdPort;
  private final Validator validator;

  @Override
  public EstudianteModel execute(final GetEstudianteByIdQuery query) {
    validateQuery(query);
    return getEstudianteByIdPort
        .getById(query.id())
        .orElseThrow(() -> EstudianteNotFoundException.becauseIdWasNotFound(query.id()));
  }

  private void validateQuery(final GetEstudianteByIdQuery query) {
    final Set<ConstraintViolation<GetEstudianteByIdQuery>> violations =
        validator.validate(query);
    if (!violations.isEmpty()) {
      throw new ConstraintViolationException(violations);
    }
  }
}

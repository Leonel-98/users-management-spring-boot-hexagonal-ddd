package com.jcaa.usersmanagement.application.port.in;

import com.jcaa.usersmanagement.application.service.dto.command.CreateEstudianteCommand;
import com.jcaa.usersmanagement.domain.model.EstudianteModel;

public interface CreateEstudianteUseCase {

  EstudianteModel execute(CreateEstudianteCommand command);
}

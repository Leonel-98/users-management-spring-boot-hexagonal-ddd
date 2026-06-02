package com.jcaa.usersmanagement.application.port.in;

import com.jcaa.usersmanagement.application.service.dto.command.UpdateEstudianteCommand;
import com.jcaa.usersmanagement.domain.model.EstudianteModel;

public interface UpdateEstudianteUseCase {

  EstudianteModel execute(UpdateEstudianteCommand command);
}

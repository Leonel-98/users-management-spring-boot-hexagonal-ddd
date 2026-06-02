package com.jcaa.usersmanagement.application.port.in;

import com.jcaa.usersmanagement.application.service.dto.command.DeleteEstudianteCommand;

public interface DeleteEstudianteUseCase {

  void execute(DeleteEstudianteCommand command);
}

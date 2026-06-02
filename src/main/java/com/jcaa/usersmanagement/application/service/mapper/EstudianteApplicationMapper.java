package com.jcaa.usersmanagement.application.service.mapper;

import com.jcaa.usersmanagement.application.service.dto.command.CreateEstudianteCommand;
import com.jcaa.usersmanagement.application.service.dto.command.UpdateEstudianteCommand;
import com.jcaa.usersmanagement.domain.model.EstudianteModel;
import lombok.experimental.UtilityClass;

@UtilityClass
public class EstudianteApplicationMapper {

  public EstudianteModel fromCreateCommandToModel(
      final String id, final CreateEstudianteCommand command) {
    return new EstudianteModel(
        id,
        command.nombre(),
        command.apellido(),
        command.fechaNacimiento(),
        command.semestre(),
        command.email(),
        command.genero(),
        command.telefono(),
        command.programa(),
        command.universidad());
  }

  public EstudianteModel fromUpdateCommandToModel(final UpdateEstudianteCommand command) {
    return new EstudianteModel(
        command.id(),
        command.nombre(),
        command.apellido(),
        command.fechaNacimiento(),
        command.semestre(),
        command.email(),
        command.genero(),
        command.telefono(),
        command.programa(),
        command.universidad());
  }
}

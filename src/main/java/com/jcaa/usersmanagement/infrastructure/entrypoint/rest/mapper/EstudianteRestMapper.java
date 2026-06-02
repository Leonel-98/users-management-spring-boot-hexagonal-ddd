package com.jcaa.usersmanagement.infrastructure.entrypoint.rest.mapper;

import com.jcaa.usersmanagement.application.service.dto.command.CreateEstudianteCommand;
import com.jcaa.usersmanagement.application.service.dto.command.DeleteEstudianteCommand;
import com.jcaa.usersmanagement.application.service.dto.command.UpdateEstudianteCommand;
import com.jcaa.usersmanagement.application.service.dto.query.GetEstudianteByIdQuery;
import com.jcaa.usersmanagement.domain.model.EstudianteModel;
import com.jcaa.usersmanagement.infrastructure.entrypoint.rest.dto.request.CreateEstudianteRestRequest;
import com.jcaa.usersmanagement.infrastructure.entrypoint.rest.dto.request.UpdateEstudianteRestRequest;
import com.jcaa.usersmanagement.infrastructure.entrypoint.rest.dto.response.EstudianteRestResponse;
import java.util.List;
import lombok.experimental.UtilityClass;

@UtilityClass
public class EstudianteRestMapper {

  public CreateEstudianteCommand toCreateCommand(final CreateEstudianteRestRequest request) {
    return new CreateEstudianteCommand(
        request.nombre(),
        request.apellido(),
        request.fechaNacimiento(),
        request.semestre(),
        request.email(),
        request.genero(),
        request.telefono(),
        request.programa(),
        request.universidad());
  }

  public UpdateEstudianteCommand toUpdateCommand(
      final String id, final UpdateEstudianteRestRequest request) {
    return new UpdateEstudianteCommand(
        id,
        request.nombre(),
        request.apellido(),
        request.fechaNacimiento(),
        request.semestre(),
        request.email(),
        request.genero(),
        request.telefono(),
        request.programa(),
        request.universidad());
  }

  public GetEstudianteByIdQuery toGetByIdQuery(final String id) {
    return new GetEstudianteByIdQuery(id);
  }

  public DeleteEstudianteCommand toDeleteCommand(final String id) {
    return new DeleteEstudianteCommand(id);
  }

  public EstudianteRestResponse toResponse(final EstudianteModel estudiante) {
    return new EstudianteRestResponse(
        estudiante.getId(),
        estudiante.getNombre(),
        estudiante.getApellido(),
        estudiante.getFechaNacimiento(),
        estudiante.getSemestre(),
        estudiante.getEmail(),
        estudiante.getGenero(),
        estudiante.getTelefono(),
        estudiante.getPrograma(),
        estudiante.getUniversidad());
  }

  public List<EstudianteRestResponse> toResponseList(final List<EstudianteModel> estudiantes) {
    return estudiantes.stream().map(EstudianteRestMapper::toResponse).toList();
  }
}

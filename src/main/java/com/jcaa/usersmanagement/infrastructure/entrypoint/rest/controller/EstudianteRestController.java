package com.jcaa.usersmanagement.infrastructure.entrypoint.rest.controller;

import com.jcaa.usersmanagement.application.port.in.CreateEstudianteUseCase;
import com.jcaa.usersmanagement.application.port.in.DeleteEstudianteUseCase;
import com.jcaa.usersmanagement.application.port.in.GetAllEstudiantesUseCase;
import com.jcaa.usersmanagement.application.port.in.GetEstudianteUseCase;
import com.jcaa.usersmanagement.application.port.in.UpdateEstudianteUseCase;
import com.jcaa.usersmanagement.application.service.dto.command.CreateEstudianteCommand;
import com.jcaa.usersmanagement.application.service.dto.command.DeleteEstudianteCommand;
import com.jcaa.usersmanagement.application.service.dto.command.UpdateEstudianteCommand;
import com.jcaa.usersmanagement.application.service.dto.query.GetEstudianteByIdQuery;
import com.jcaa.usersmanagement.domain.model.EstudianteModel;
import com.jcaa.usersmanagement.infrastructure.entrypoint.rest.dto.request.CreateEstudianteRestRequest;
import com.jcaa.usersmanagement.infrastructure.entrypoint.rest.dto.request.UpdateEstudianteRestRequest;
import com.jcaa.usersmanagement.infrastructure.entrypoint.rest.dto.response.EstudianteRestResponse;
import com.jcaa.usersmanagement.infrastructure.entrypoint.rest.mapper.EstudianteRestMapper;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/estudiantes")
@RequiredArgsConstructor
public class EstudianteRestController {

  private final CreateEstudianteUseCase createEstudianteUseCase;
  private final UpdateEstudianteUseCase updateEstudianteUseCase;
  private final DeleteEstudianteUseCase deleteEstudianteUseCase;
  private final GetEstudianteUseCase getEstudianteUseCase;
  private final GetAllEstudiantesUseCase getAllEstudiantesUseCase;

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public EstudianteRestResponse create(
      @Valid @RequestBody final CreateEstudianteRestRequest request) {
    final CreateEstudianteCommand command = EstudianteRestMapper.toCreateCommand(request);
    final EstudianteModel estudiante = createEstudianteUseCase.execute(command);
    return EstudianteRestMapper.toResponse(estudiante);
  }

  @GetMapping("/{id}")
  public EstudianteRestResponse getById(@PathVariable final String id) {
    final GetEstudianteByIdQuery query = EstudianteRestMapper.toGetByIdQuery(id);
    final EstudianteModel estudiante = getEstudianteUseCase.execute(query);
    return EstudianteRestMapper.toResponse(estudiante);
  }

  @PutMapping("/{id}")
  public EstudianteRestResponse update(
      @PathVariable final String id,
      @Valid @RequestBody final UpdateEstudianteRestRequest request) {
    final UpdateEstudianteCommand command = EstudianteRestMapper.toUpdateCommand(id, request);
    final EstudianteModel estudiante = updateEstudianteUseCase.execute(command);
    return EstudianteRestMapper.toResponse(estudiante);
  }

  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void delete(@PathVariable final String id) {
    final DeleteEstudianteCommand command = EstudianteRestMapper.toDeleteCommand(id);
    deleteEstudianteUseCase.execute(command);
  }

  @GetMapping
  public List<EstudianteRestResponse> getAll() {
    return EstudianteRestMapper.toResponseList(getAllEstudiantesUseCase.execute());
  }
}

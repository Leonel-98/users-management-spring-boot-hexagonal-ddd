package com.jcaa.usersmanagement.application.service;

import com.jcaa.usersmanagement.application.port.in.GetAllEstudiantesUseCase;
import com.jcaa.usersmanagement.application.port.out.GetAllEstudiantesPort;
import com.jcaa.usersmanagement.domain.model.EstudianteModel;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetAllEstudiantesService implements GetAllEstudiantesUseCase {

  private final GetAllEstudiantesPort getAllEstudiantesPort;

  @Override
  public List<EstudianteModel> execute() {
    return getAllEstudiantesPort.getAll();
  }
}

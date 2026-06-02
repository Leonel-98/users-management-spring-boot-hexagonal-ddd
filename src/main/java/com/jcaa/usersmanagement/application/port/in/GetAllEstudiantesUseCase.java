package com.jcaa.usersmanagement.application.port.in;

import com.jcaa.usersmanagement.domain.model.EstudianteModel;
import java.util.List;

public interface GetAllEstudiantesUseCase {

  List<EstudianteModel> execute();
}

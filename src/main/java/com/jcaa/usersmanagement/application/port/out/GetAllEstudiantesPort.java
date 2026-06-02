package com.jcaa.usersmanagement.application.port.out;

import com.jcaa.usersmanagement.domain.model.EstudianteModel;
import java.util.List;

public interface GetAllEstudiantesPort {

  List<EstudianteModel> getAll();
}

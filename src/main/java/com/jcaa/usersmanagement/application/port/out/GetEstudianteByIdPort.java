package com.jcaa.usersmanagement.application.port.out;

import com.jcaa.usersmanagement.domain.model.EstudianteModel;
import java.util.Optional;

public interface GetEstudianteByIdPort {

  Optional<EstudianteModel> getById(String id);
}

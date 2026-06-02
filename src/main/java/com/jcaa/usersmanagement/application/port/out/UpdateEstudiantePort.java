package com.jcaa.usersmanagement.application.port.out;

import com.jcaa.usersmanagement.domain.model.EstudianteModel;

public interface UpdateEstudiantePort {

  EstudianteModel update(EstudianteModel estudiante);
}

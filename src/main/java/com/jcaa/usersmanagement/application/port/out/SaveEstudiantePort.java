package com.jcaa.usersmanagement.application.port.out;

import com.jcaa.usersmanagement.domain.model.EstudianteModel;

public interface SaveEstudiantePort {

  EstudianteModel save(EstudianteModel estudiante);
}

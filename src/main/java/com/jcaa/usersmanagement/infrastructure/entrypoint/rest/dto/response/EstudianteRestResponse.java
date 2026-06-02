package com.jcaa.usersmanagement.infrastructure.entrypoint.rest.dto.response;

import java.time.LocalDate;

public record EstudianteRestResponse(
    String id,
    String nombre,
    String apellido,
    LocalDate fechaNacimiento,
    int semestre,
    String email,
    String genero,
    String telefono,
    String programa,
    String universidad) {}

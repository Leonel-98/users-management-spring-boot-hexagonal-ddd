package com.jcaa.usersmanagement.infrastructure.adapter.persistence.dto;

import java.time.LocalDate;

public record EstudiantePersistenceDto(
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

package com.jcaa.usersmanagement.application.service.dto.command;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.time.LocalDate;

public record UpdateEstudianteCommand(
    @NotBlank(message = "id must not be blank") String id,
    @NotBlank(message = "nombre must not be blank") String nombre,
    @NotBlank(message = "apellido must not be blank") String apellido,
    @NotNull(message = "fechaNacimiento must not be null") LocalDate fechaNacimiento,
    @NotNull(message = "semestre must not be null")
        @Positive(message = "semestre must be greater than zero")
        Integer semestre,
    @NotBlank(message = "email must not be blank")
        @Email(message = "email must be a valid email address")
        String email,
    @NotBlank(message = "genero must not be blank") String genero,
    @NotBlank(message = "telefono must not be blank") String telefono,
    @NotBlank(message = "programa must not be blank") String programa,
    @NotBlank(message = "universidad must not be blank") String universidad) {}

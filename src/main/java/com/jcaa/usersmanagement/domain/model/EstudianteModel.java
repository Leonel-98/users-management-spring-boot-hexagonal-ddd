package com.jcaa.usersmanagement.domain.model;

import com.jcaa.usersmanagement.domain.exception.InvalidEstudianteException;
import java.time.LocalDate;
import java.util.Objects;
import lombok.Value;

@Value
public class EstudianteModel {

  private static final String FIELD_ID = "id";
  private static final String FIELD_NOMBRE = "nombre";
  private static final String FIELD_APELLIDO = "apellido";
  private static final String FIELD_FECHA_NACIMIENTO = "fechaNacimiento";
  private static final String FIELD_SEMESTRE = "semestre";
  private static final String FIELD_EMAIL = "email";
  private static final String FIELD_GENERO = "genero";
  private static final String FIELD_TELEFONO = "telefono";
  private static final String FIELD_PROGRAMA = "programa";
  private static final String FIELD_UNIVERSIDAD = "universidad";

  String id;
  String nombre;
  String apellido;
  LocalDate fechaNacimiento;
  int semestre;
  String email;
  String genero;
  String telefono;
  String programa;
  String universidad;

  public EstudianteModel(
      final String id,
      final String nombre,
      final String apellido,
      final LocalDate fechaNacimiento,
      final int semestre,
      final String email,
      final String genero,
      final String telefono,
      final String programa,
      final String universidad) {
    requireText(id, FIELD_ID);
    requireText(nombre, FIELD_NOMBRE);
    requireText(apellido, FIELD_APELLIDO);
    requireObject(fechaNacimiento, FIELD_FECHA_NACIMIENTO);
    requirePositive(semestre, FIELD_SEMESTRE);
    requireText(email, FIELD_EMAIL);
    requireText(genero, FIELD_GENERO);
    requireText(telefono, FIELD_TELEFONO);
    requireText(programa, FIELD_PROGRAMA);
    requireText(universidad, FIELD_UNIVERSIDAD);

    this.id = id;
    this.nombre = nombre;
    this.apellido = apellido;
    this.fechaNacimiento = fechaNacimiento;
    this.semestre = semestre;
    this.email = email;
    this.genero = genero;
    this.telefono = telefono;
    this.programa = programa;
    this.universidad = universidad;
  }

  private static void requireText(final String value, final String fieldName) {
    if (Objects.isNull(value) || value.isBlank()) {
      throw InvalidEstudianteException.becauseFieldIsRequired(fieldName);
    }
  }

  private static void requireObject(final Object value, final String fieldName) {
    if (Objects.isNull(value)) {
      throw InvalidEstudianteException.becauseFieldIsRequired(fieldName);
    }
  }

  private static void requirePositive(final int value, final String fieldName) {
    if (value <= 0) {
      throw InvalidEstudianteException.becauseNumberMustBePositive(fieldName);
    }
  }
}

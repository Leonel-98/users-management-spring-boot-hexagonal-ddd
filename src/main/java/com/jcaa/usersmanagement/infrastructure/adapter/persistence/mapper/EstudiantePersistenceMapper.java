package com.jcaa.usersmanagement.infrastructure.adapter.persistence.mapper;

import com.jcaa.usersmanagement.domain.model.EstudianteModel;
import com.jcaa.usersmanagement.infrastructure.adapter.persistence.dto.EstudiantePersistenceDto;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import lombok.experimental.UtilityClass;

@UtilityClass
public class EstudiantePersistenceMapper {

  private static final String COLUMN_ID = "id";
  private static final String COLUMN_NOMBRE = "nombre";
  private static final String COLUMN_APELLIDO = "apellido";
  private static final String COLUMN_FECHA_NACIMIENTO = "fecha_nacimiento";
  private static final String COLUMN_SEMESTRE = "semestre";
  private static final String COLUMN_EMAIL = "email";
  private static final String COLUMN_GENERO = "genero";
  private static final String COLUMN_TELEFONO = "telefono";
  private static final String COLUMN_PROGRAMA = "programa";
  private static final String COLUMN_UNIVERSIDAD = "universidad";

  public EstudiantePersistenceDto fromModelToDto(final EstudianteModel estudiante) {
    return new EstudiantePersistenceDto(
        estudiante.getId(),
        estudiante.getNombre(),
        estudiante.getApellido(),
        estudiante.getFechaNacimiento(),
        estudiante.getSemestre(),
        estudiante.getEmail(),
        estudiante.getGenero(),
        estudiante.getTelefono(),
        estudiante.getPrograma(),
        estudiante.getUniversidad());
  }

  public EstudianteModel fromDtoToModel(final EstudiantePersistenceDto dto) {
    return new EstudianteModel(
        dto.id(),
        dto.nombre(),
        dto.apellido(),
        dto.fechaNacimiento(),
        dto.semestre(),
        dto.email(),
        dto.genero(),
        dto.telefono(),
        dto.programa(),
        dto.universidad());
  }

  public EstudianteModel fromResultSetToModel(final ResultSet resultSet) throws SQLException {
    return fromDtoToModel(fromResultSetToDto(resultSet));
  }

  public List<EstudianteModel> fromResultSetToModelList(final ResultSet resultSet)
      throws SQLException {
    final List<EstudianteModel> estudiantes = new ArrayList<>();
    while (resultSet.next()) {
      estudiantes.add(fromResultSetToModel(resultSet));
    }
    return estudiantes;
  }

  private EstudiantePersistenceDto fromResultSetToDto(final ResultSet resultSet)
      throws SQLException {
    return new EstudiantePersistenceDto(
        resultSet.getString(COLUMN_ID),
        resultSet.getString(COLUMN_NOMBRE),
        resultSet.getString(COLUMN_APELLIDO),
        resultSet.getDate(COLUMN_FECHA_NACIMIENTO).toLocalDate(),
        resultSet.getInt(COLUMN_SEMESTRE),
        resultSet.getString(COLUMN_EMAIL),
        resultSet.getString(COLUMN_GENERO),
        resultSet.getString(COLUMN_TELEFONO),
        resultSet.getString(COLUMN_PROGRAMA),
        resultSet.getString(COLUMN_UNIVERSIDAD));
  }
}

package com.jcaa.usersmanagement.infrastructure.adapter.persistence.repository;

import com.jcaa.usersmanagement.application.port.out.DeleteEstudiantePort;
import com.jcaa.usersmanagement.application.port.out.GetAllEstudiantesPort;
import com.jcaa.usersmanagement.application.port.out.GetEstudianteByIdPort;
import com.jcaa.usersmanagement.application.port.out.SaveEstudiantePort;
import com.jcaa.usersmanagement.application.port.out.UpdateEstudiantePort;
import com.jcaa.usersmanagement.domain.exception.EstudianteNotFoundException;
import com.jcaa.usersmanagement.domain.model.EstudianteModel;
import com.jcaa.usersmanagement.infrastructure.adapter.persistence.dto.EstudiantePersistenceDto;
import com.jcaa.usersmanagement.infrastructure.adapter.persistence.exception.PersistenceException;
import com.jcaa.usersmanagement.infrastructure.adapter.persistence.mapper.EstudiantePersistenceMapper;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import javax.sql.DataSource;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class EstudianteRepositoryMySQL
    implements SaveEstudiantePort,
        UpdateEstudiantePort,
        GetEstudianteByIdPort,
        GetAllEstudiantesPort,
        DeleteEstudiantePort {

  private static final String SQL_FIELDS =
      "id, nombre, apellido, fecha_nacimiento, semestre, email, genero, telefono, programa, "
          + "universidad";

  private static final String SQL_INSERT =
      "INSERT INTO estudiante ("
          + SQL_FIELDS
          + ", created_at, updated_at) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, NOW(), NOW())";

  private static final String SQL_UPDATE =
      "UPDATE estudiante SET nombre = ?, apellido = ?, fecha_nacimiento = ?, semestre = ?, "
          + "email = ?, genero = ?, telefono = ?, programa = ?, universidad = ?, "
          + "updated_at = NOW() WHERE id = ?";

  private static final String SQL_SELECT_BY_ID =
      "SELECT " + SQL_FIELDS + " FROM estudiante WHERE id = ? LIMIT 1";

  private static final String SQL_SELECT_ALL =
      "SELECT " + SQL_FIELDS + " FROM estudiante ORDER BY apellido ASC, nombre ASC";

  private static final String SQL_DELETE = "DELETE FROM estudiante WHERE id = ?";

  private final DataSource dataSource;

  @Override
  public EstudianteModel save(final EstudianteModel estudiante) {
    final EstudiantePersistenceDto dto = EstudiantePersistenceMapper.fromModelToDto(estudiante);
    executeSave(dto);
    return findByIdOrFail(estudiante.getId());
  }

  @Override
  public EstudianteModel update(final EstudianteModel estudiante) {
    final EstudiantePersistenceDto dto = EstudiantePersistenceMapper.fromModelToDto(estudiante);
    executeUpdate(dto);
    return findByIdOrFail(estudiante.getId());
  }

  @Override
  public Optional<EstudianteModel> getById(final String id) {
    try (final Connection connection = dataSource.getConnection();
         final PreparedStatement statement = connection.prepareStatement(SQL_SELECT_BY_ID)) {
      statement.setString(1, id);
      final ResultSet resultSet = statement.executeQuery();
      if (!resultSet.next()) {
        return Optional.empty();
      }
      return Optional.of(EstudiantePersistenceMapper.fromResultSetToModel(resultSet));
    } catch (final SQLException exception) {
      throw PersistenceException.becauseFindEstudianteByIdFailed(id, exception);
    }
  }

  @Override
  public List<EstudianteModel> getAll() {
    try (final Connection connection = dataSource.getConnection();
         final PreparedStatement statement = connection.prepareStatement(SQL_SELECT_ALL)) {
      final ResultSet resultSet = statement.executeQuery();
      return EstudiantePersistenceMapper.fromResultSetToModelList(resultSet);
    } catch (final SQLException exception) {
      throw PersistenceException.becauseFindAllEstudiantesFailed(exception);
    }
  }

  @Override
  public void delete(final String id) {
    try (final Connection connection = dataSource.getConnection();
         final PreparedStatement statement = connection.prepareStatement(SQL_DELETE)) {
      statement.setString(1, id);
      statement.executeUpdate();
    } catch (final SQLException exception) {
      throw PersistenceException.becauseDeleteEstudianteFailed(id, exception);
    }
  }

  private void executeSave(final EstudiantePersistenceDto dto) {
    try (final Connection connection = dataSource.getConnection();
         final PreparedStatement statement = connection.prepareStatement(SQL_INSERT)) {
      statement.setString(1, dto.id());
      fillMutableFields(statement, dto, 2);
      statement.executeUpdate();
    } catch (final SQLException exception) {
      throw PersistenceException.becauseSaveEstudianteFailed(dto.id(), exception);
    }
  }

  private void executeUpdate(final EstudiantePersistenceDto dto) {
    try (final Connection connection = dataSource.getConnection();
         final PreparedStatement statement = connection.prepareStatement(SQL_UPDATE)) {
      fillMutableFields(statement, dto, 1);
      statement.setString(10, dto.id());
      statement.executeUpdate();
    } catch (final SQLException exception) {
      throw PersistenceException.becauseUpdateEstudianteFailed(dto.id(), exception);
    }
  }

  private static void fillMutableFields(
      final PreparedStatement statement, final EstudiantePersistenceDto dto, final int startIndex)
      throws SQLException {
    statement.setString(startIndex, dto.nombre());
    statement.setString(startIndex + 1, dto.apellido());
    statement.setDate(startIndex + 2, Date.valueOf(dto.fechaNacimiento()));
    statement.setInt(startIndex + 3, dto.semestre());
    statement.setString(startIndex + 4, dto.email());
    statement.setString(startIndex + 5, dto.genero());
    statement.setString(startIndex + 6, dto.telefono());
    statement.setString(startIndex + 7, dto.programa());
    statement.setString(startIndex + 8, dto.universidad());
  }

  private EstudianteModel findByIdOrFail(final String id) {
    return getById(id).orElseThrow(() -> EstudianteNotFoundException.becauseIdWasNotFound(id));
  }
}

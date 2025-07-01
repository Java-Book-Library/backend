package io.github.crudapp.repository;

import io.github.crudapp.model.book.Book;
import io.github.crudapp.model.book.BookDTO;
import io.github.crudapp.model.user.User;
import io.github.crudapp.model.user.UserDTO;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import java.lang.reflect.Field;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static io.github.crudapp.repository.Helper.*;

@Repository
public class UserRepositoryImpl implements UserRepository {
    private final JdbcTemplate jdbc;

    public UserRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbc = jdbcTemplate;
    }

    public List<UserDTO> findAll() {
        String sql = "SELECT * FROM users";
        return jdbc.query(sql, (rs, rowNum) ->
            new UserDTO(
                rs.getLong("id"),
                rs.getString("name")
            )
        );
    }

    public UserDTO findById(Long id) {
        String sql = "SELECT * FROM users WHERE id = ?";
        return jdbc.queryForObject(sql, (rs, rowNum) ->
            new UserDTO(
                rs.getLong("id"),
                rs.getString("name")
            ),
            id
        );
    }

    public UserDTO save(User user) {
        GeneratedKeyHolder generatedKeyHolder = new GeneratedKeyHolder();

        String sql = "INSERT INTO books (name, password) VALUES (?, ?)";
        jdbc.update(conn -> {
            // Pre-compiling sql
            PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            // Set parameters
            ps.setString(1, user.getName());
            ps.setString(2, user.getPassword());
            return ps;
        }, generatedKeyHolder);

        Long id = generatedKeyHolder.getKey().longValue();

        UserDTO savedUser = new UserDTO(id, user.getName());
        return savedUser;
    }

    public void update(Long id, User update) {
        if (update == null) return;

        List<Field> fields = getNonNullFields(update);
        if (fields.isEmpty()) return; // Nothing to update

        String setClause = getSetClause(fields);
        String sql = "UPDATE users SET " + setClause + " WHERE id = ?";
        List<Object> params = collectParameters(fields, update);
        params.add(id);
        jdbc.update(sql, params.toArray());
    }

    public void deleteById(Long id) {
        String sql = "DELETE FROM users WHERE id = ?";
        jdbc.update(sql, id);
    }

    public void deleteAll() {
        String sql = "TRUNCATE TABLE users";
        jdbc.update(sql);
    }

}

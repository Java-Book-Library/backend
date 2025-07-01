package io.github.crudapp.repository;

import io.github.crudapp.model.book.Book;
import io.github.crudapp.model.book.BookDTO;
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
public class BookRepositoryImpl implements BookRepository {
    private final JdbcTemplate jdbc;

    public BookRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbc = jdbcTemplate;
    }

    public List<Book> findAll() {
        String sql = "SELECT * FROM books";
        return jdbc.query(sql, (rs, rowNum) ->
            new Book(
                rs.getLong("id"),
                rs.getString("title"),
                rs.getString("author"),
                rs.getDouble("price")
            )
        );
    }

    public Book findById(Long id) {
        String sql = "SELECT * FROM books WHERE id = ?";
        return jdbc.queryForObject(sql, (rs, rowNum) ->
            new Book(
                rs.getLong("id"),
                rs.getString("title"),
                rs.getString("author"),
                rs.getDouble("price")
            ),
            id
        );
    }

    public Book save(BookDTO book) {
        GeneratedKeyHolder generatedKeyHolder = new GeneratedKeyHolder();

        String sql = "INSERT INTO books (title, author, price) VALUES (?, ?, ?)";
        jdbc.update(conn -> {
            // Pre-compiling sql
            PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            // Set parameters
            ps.setString(1, book.getTitle());
            ps.setString(2, book.getAuthor());
            ps.setDouble(3, book.getPrice());
            return ps;
        }, generatedKeyHolder);

        Long id = generatedKeyHolder.getKey().longValue();

        Book savedBook = new Book(id, book.getTitle(), book.getAuthor(), book.getPrice());
        return savedBook;
    }

    public void update(Long id, BookDTO update) {
        if (update == null) return;

        List<Field> fields = getNonNullFields(update);
        if (fields.isEmpty()) return; // Nothing to update

        String setClause = getSetClause(fields);
        String sql = "UPDATE books SET " + setClause + " WHERE id = ?";
        List<Object> params = collectParameters(fields, update);
        params.add(id);
        jdbc.update(sql, params.toArray());
    }

    public void deleteById(Long id) {
        String sql = "DELETE FROM books WHERE id = ?";
        jdbc.update(sql, id);
    }

    public void deleteAll() {
        String sql = "TRUNCATE TABLE books";
        jdbc.update(sql);
    }

}

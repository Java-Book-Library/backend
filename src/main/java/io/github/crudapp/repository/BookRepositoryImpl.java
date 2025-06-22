package io.github.crudapp.repository;

import io.github.crudapp.model.Book;
import io.github.crudapp.model.BookDTO;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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

    public void save(Book book) {
        String sql = "INSERT INTO books (title, author, price) VALUES (?, ?, ?)";
        jdbc.update(sql, book.getTitle(), book.getAuthor(), book.getPrice());
    }

    private List<Field> getNonNullFields(BookDTO update) {
        Field[] fields = BookDTO.class.getSuperclass().getDeclaredFields();

        return Arrays.stream(fields)
                .filter(f -> {
                    f.setAccessible(true);
                    try {
                        return f.get(update) != null;
                    } catch (IllegalAccessException e) {
                        return false;
                    }
                })
                .toList();
    }

    private String getSetClause(List<Field> fields) {
        return fields.stream()
                .map(f -> f.getName() + " = ?")
                .collect(Collectors.joining(", "));
    }

    private List<Object> collectParameters(List<Field> nonNullFields, BookDTO update) {
        List<Object> params = new ArrayList<>();
        for (Field f : nonNullFields) {
            try {
                params.add(f.get(update));
            } catch (IllegalAccessException e) {
                System.err.println("Failed to access field: " + f.getName());
                e.printStackTrace();
            }
        }
        return params;
    }

    public void update(Long id, BookDTO update) {
        if (update == null) return;

        List<Field> fields = getNonNullFields(update);
        if (fields.isEmpty()) return; // Nothing to update

        String setClause = getSetClause(fields);
        String sql = "UPDATE books SET " + setClause + " WHERE id = ?";
        List<Object> params = collectParameters(fields, update);
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

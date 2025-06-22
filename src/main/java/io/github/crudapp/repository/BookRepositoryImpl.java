package io.github.crudapp.repository;

import io.github.crudapp.model.Book;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

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

    public void deleteById(Long id) {
        String sql = "DELETE FROM books WHERE id = ?";
        jdbc.update(sql, id);
    }

    public void deleteAll() {
        String sql = "TRUNCATE TABLE books";
        jdbc.update(sql);
    }

}

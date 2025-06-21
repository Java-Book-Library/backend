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
        return jdbc.query("SELECT * FROM books", (rs, rowNum) ->
            new Book(
                rs.getLong("id"),
                rs.getString("title"),
                rs.getString("author"),
                rs.getDouble("price")
            )
        );
    }

    public void save(Book book) {
        String sql = "INSERT INTO books (title, author, price) VALUES (?, ?, ?)";
        jdbc.update(sql, book.getTitle(), book.getAuthor(), book.getPrice());
    }

}

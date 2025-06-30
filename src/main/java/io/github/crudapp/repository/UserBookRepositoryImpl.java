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
import java.util.List;

import static io.github.crudapp.repository.Helper.*;

@Repository
public class UserBookRepositoryImpl implements UserBookRepository {
    private final JdbcTemplate jdbc;

    public UserBookRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbc = jdbcTemplate;
    }

    public List<Book> findBooksByUserId(Long id) {
        String sql = "SELECT * FROM books WHERE id = ?";
        return jdbc.query(sql, (rs, rowNum) ->
            new Book(
                    rs.getLong("id"),
                    rs.getString("title"),
                    rs.getString("author"),
                    rs.getDouble("price")
            ),
            id
        );
    }

    public List<UserDTO> findUsersByBookId(Long id) {
        String sql = "SELECT * FROM users WHERE id = ?";
        return jdbc.query(sql, (rs, rowNum) ->
            new UserDTO(
                    rs.getLong("id"),
                    rs.getString("name")
            ),
            id
        );
    }

    public boolean isBookBorrowedByUser(Long userId, Long bookId) {
        List<UserDTO> users = findUsersByBookId(bookId);
        return !users.isEmpty();
    }

    public void borrowBook(Long userId, Long bookId) {
        String sql = "INSERT INTO user_book (user_id, book_id) VALUES (?, ?)";
        jdbc.update(sql, userId, bookId);
    }

    public void returnBook(Long userId, Long bookId) {
        String sql = "DELETE FROM user_book WHERE user_id = ? AND book_id = ?";
        jdbc.update(sql, userId, bookId);
    }

}

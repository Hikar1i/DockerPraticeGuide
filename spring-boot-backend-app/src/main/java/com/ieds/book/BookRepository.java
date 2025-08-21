package com.ieds.book;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BookRepository {

    private final JdbcTemplate jdbcTemplate;

    public BookRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final RowMapper<Book> bookRowMapper = (rs, rowNum) -> {
        Book book = new Book();
        book.setId(rs.getLong("id"));
        book.setTitle(rs.getString("title"));
        book.setAuthor(rs.getString("author"));
        return book;
    };

    public List<Book> findAll() {
        return jdbcTemplate.query("SELECT id, title, author FROM book", bookRowMapper);
    }

    public int save(Book book) {
        return jdbcTemplate.update("INSERT INTO book(title, author) VALUES (?, ?)",
                book.getTitle(), book.getAuthor());
    }

    public int delete(Long id) {
        return jdbcTemplate.update("DELETE FROM book WHERE id = ?", id);
    }
}
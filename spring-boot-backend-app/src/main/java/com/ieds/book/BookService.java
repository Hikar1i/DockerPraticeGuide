package com.ieds.book;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class BookService {

    private final BookRepository bookRepository;
    private final RedisTemplate<String, Object> redisTemplate;

    private static final String CACHE_KEY = "books";

    public BookService(BookRepository bookRepository, RedisTemplate<String, Object> redisTemplate) {
        this.bookRepository = bookRepository;
        this.redisTemplate = redisTemplate;
    }

    public List<Book> findAll() {
        List<Book> books = (List<Book>) redisTemplate.opsForValue().get(CACHE_KEY);
        if (books != null) {
            return books;
        }
        books = bookRepository.findAll();
        redisTemplate.opsForValue().set(CACHE_KEY, books, 60, TimeUnit.SECONDS);
        return books;
    }

    public void save(Book book) {
        bookRepository.save(book);
        redisTemplate.delete(CACHE_KEY);
    }

    public void delete(Long id) {
        bookRepository.delete(id);
        redisTemplate.delete(CACHE_KEY);
    }
}

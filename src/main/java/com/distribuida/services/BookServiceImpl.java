package com.distribuida.services;

import com.distribuida.db.Book;
import com.distribuida.repository.BookRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;


import java.util.List;

@ApplicationScoped
public class BookServiceImpl implements BookService {

    @Inject
    private BookRepository bookRepository;

    @Override
    public void createBook(Book book) {
        this.bookRepository.createBook(book);

    }

    @Override
    public Book getBook(int id) {
        return this.bookRepository.getBook(id);
    }

    @Override
    public void updateBook(Book book) {
        this.bookRepository.updateBook(book);
    }

    @Override
    public void deleteBook(int id) {
        this.bookRepository.deleteBook(id);
    }

    @Override
    public List<Book> listBooks() {
        return this.bookRepository.listBooks();
    }
}

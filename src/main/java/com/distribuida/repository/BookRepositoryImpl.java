package com.distribuida.repository;

import com.distribuida.db.Book;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;

import java.util.List;

@ApplicationScoped
public class BookRepositoryImpl implements BookRepository {

    @Inject
    private EntityManager entityManager;


    @Override
    public void createBook(Book book) {
        entityManager.getTransaction().begin();
        this.entityManager.persist(book);
        entityManager.getTransaction().commit();
    }

    @Override
    public Book getBook(int id) {
        return this.entityManager.find(Book.class, id);
    }

    @Override
    public void updateBook(Book book) {
        this.entityManager.merge(book);
    }

    @Override
    public void deleteBook(int id) {
        Book book = getBook(id);
        if (book != null) {
            this.entityManager.remove(book);
        }
    }

    @Override
    public List<Book> listBooks() {


        return this.entityManager.createQuery("SELECT b FROM Book b", Book.class).getResultList();
    }
}

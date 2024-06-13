package com.distribuida.services;

import com.distribuida.db.Book;

import java.util.List;

public interface BookService {

    public void createBook(Book book);

    public Book getBook(int id);

    public void updateBook(Book book);

    public void deleteBook(int id);

    public List<Book> listBooks();
}

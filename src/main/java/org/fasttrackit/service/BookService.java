package org.fasttrackit.service;

import org.fasttrackit.domain.Book;
import org.fasttrackit.persistance.BookRepository;
import org.fasttrackit.transfer.CreateNewName;
import org.fasttrackit.transfer.UpdateName;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class BookService {

    private BookRepository bookRepository = new BookRepository();

    public void createName(CreateNewName createNewName) throws IOException, SQLException {
        System.out.println("Creating new name: "+ createNewName);

        bookRepository.createName(createNewName);
    }

    public void updateName(long id, UpdateName updateName) throws IOException, SQLException {
        System.out.println("Updating task "+ id + ": "+updateName);
        bookRepository.updateName(1, updateName);
    }

    public void deleteName(long id) throws IOException, SQLException {
        System.out.println("Deleting "+id);
        bookRepository.delete(id);
    }

    public List<Book> getNames() throws IOException, SQLException {
        System.out.println("Reading names ");
        return bookRepository.getNames();
    }
}

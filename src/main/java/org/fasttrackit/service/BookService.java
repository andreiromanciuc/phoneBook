package org.fasttrackit.service;

import org.fasttrackit.domain.Book;
import org.fasttrackit.persistance.ContactsRepository;
import org.fasttrackit.transfer.CreateNewName;
import org.fasttrackit.transfer.DeleteNames;
import org.fasttrackit.transfer.UpdateName;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class BookService {

    private ContactsRepository bookRepository = new ContactsRepository();

    public void createName(CreateNewName createNewName) throws IOException, SQLException, ClassNotFoundException {
        System.out.println("Creating new name: "+ createNewName);

        bookRepository.createName(createNewName);
    }

    public void updateName(long id, UpdateName updateName) throws IOException, SQLException, ClassNotFoundException {
        System.out.println("Updating task "+ id + ": "+updateName);
        bookRepository.updateName(id, updateName);
    }

    public void deleteName(long id) throws IOException, SQLException, ClassNotFoundException {
        System.out.println("Deleting "+id);
        bookRepository.delete(id);
    }

    public void deleteWhereAddressIsNull(DeleteNames deleteNames) throws IOException, SQLException, ClassNotFoundException {
        System.out.println("Deleting address ");
        bookRepository.deleteWhereAddressIsNull(deleteNames);
    }

    public List<Book> getNames() throws IOException, SQLException, ClassNotFoundException {
        System.out.println("Reading names ");
        return bookRepository.getNames();
    }

    public List<Book> getContactsByFirstName() throws IOException, SQLException, ClassNotFoundException {
        System.out.println("Reading contacts by firstName ");
        return bookRepository.getContactsByFirstName();
    }

    public List<Book> getContactsByLastName() throws IOException, SQLException, ClassNotFoundException {
        System.out.println("Reading contacts by lastName");
        return bookRepository.getContactsByLastName();
    }
}

package org.fasttrackit.service;

import org.fasttrackit.domain.Contact;
import org.fasttrackit.persistance.ContactsRepository;
import org.fasttrackit.transfer.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class BookService {

    private ContactsRepository bookRepository = new ContactsRepository();

    public void createContact(CreateContact createContact) throws IOException, SQLException, ClassNotFoundException {
        System.out.println("Creating new name: "+ createContact);

        bookRepository.createContact(createContact);
    }

    public void updateContact(long id, UpdateContact updateContact) throws IOException, SQLException, ClassNotFoundException {
        System.out.println("Updating task "+ id + ": "+ updateContact);
        bookRepository.updateContact(id, updateContact);
    }

    public void deleteContact(long id) throws IOException, SQLException, ClassNotFoundException {
        System.out.println("Deleting "+id);
        bookRepository.deleteContact(id);
    }

    public void deleteWhereAddressIsNull(DeleteContact deleteContact) throws IOException, SQLException, ClassNotFoundException {
        System.out.println("Deleting address ");
        bookRepository.deleteWhereAddressIsNull(deleteContact);
    }

    public List<Contact> getContacts() throws IOException, SQLException, ClassNotFoundException {
        System.out.println("Reading names ");
        return bookRepository.getContacts();
    }

    public List<Contact> getContactsByFirstName(GetByFirstName getByFirstName) throws IOException, SQLException, ClassNotFoundException {
        System.out.println("Reading contacts by firstName ");
        return bookRepository.getContactsByFirstName(getByFirstName);
    }

    public List<Contact> getContactsByLastName(GetByLastName getByLastName) throws IOException, SQLException, ClassNotFoundException {
        System.out.println("Reading contacts by lastName");
        return bookRepository.getContactsByLastName(getByLastName);
    }

}

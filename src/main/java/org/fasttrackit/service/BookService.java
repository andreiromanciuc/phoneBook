package org.fasttrackit.service;

import org.fasttrackit.domain.Contacts;
import org.fasttrackit.persistance.ContactsRepository;
import org.fasttrackit.transfer.CreateContact;
import org.fasttrackit.transfer.DeleteContact;
import org.fasttrackit.transfer.UpdateContact;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class BookService {

    private ContactsRepository bookRepository = new ContactsRepository();

    public void createContact(CreateContact createContact) throws IOException, SQLException, ClassNotFoundException {
        System.out.println("Creating new name: "+ createContact);

        bookRepository.createName(createContact);
    }

    public void updateContact(long id, UpdateContact updateContact) throws IOException, SQLException, ClassNotFoundException {
        System.out.println("Updating task "+ id + ": "+ updateContact);
        bookRepository.updateName(id, updateContact);
    }

    public void deleteContact(long id) throws IOException, SQLException, ClassNotFoundException {
        System.out.println("Deleting "+id);
        bookRepository.deleteContact(id);
    }

    public void deleteWhereAddressIsNull(DeleteContact deleteContact) throws IOException, SQLException, ClassNotFoundException {
        System.out.println("Deleting address ");
        bookRepository.deleteWhereAddressIsNull(deleteContact);
    }

    public List<Contacts> getContacts() throws IOException, SQLException, ClassNotFoundException {
        System.out.println("Reading names ");
        return bookRepository.getNames();
    }

    public List<Contacts> getContactsByFirstName() throws IOException, SQLException, ClassNotFoundException {
        System.out.println("Reading contacts by firstName ");
        return bookRepository.getContactsByFirstName();
    }

    public List<Contacts> getContactsByLastName() throws IOException, SQLException, ClassNotFoundException {
        System.out.println("Reading contacts by lastName");
        return bookRepository.getContactsByLastName();
    }
}

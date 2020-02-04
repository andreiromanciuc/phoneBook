package org.fasttrackit;


import org.fasttrackit.persistance.ContactsRepository;
import org.fasttrackit.transfer.DeleteContact;

import java.io.IOException;
import java.sql.SQLException;

public class App
{
    public static void main( String[] args ) throws IOException, SQLException, ClassNotFoundException {

        DeleteContact deleteContact = new DeleteContact();


        ContactsRepository bookRepository = new ContactsRepository();
        bookRepository.deleteWhereAddressIsNull(deleteContact);



    }
}

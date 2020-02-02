package org.fasttrackit;


import org.fasttrackit.persistance.ContactsRepository;
import org.fasttrackit.transfer.DeleteNames;

import java.io.IOException;
import java.sql.SQLException;

public class App
{
    public static void main( String[] args ) throws IOException, SQLException, ClassNotFoundException {

        DeleteNames deleteNames = new DeleteNames();


        ContactsRepository bookRepository = new ContactsRepository();
        bookRepository.deleteWhereAddressIsNull(deleteNames);



    }
}

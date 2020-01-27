package org.fasttrackit;


import org.fasttrackit.persistance.BookRepository;
import org.fasttrackit.transfer.DeleteNames;

import java.io.IOException;
import java.sql.SQLException;

public class App
{
    public static void main( String[] args ) throws IOException, SQLException {

        DeleteNames deleteNames = new DeleteNames();


        BookRepository bookRepository = new BookRepository();
        bookRepository.deleteWhereAddressIsNull(deleteNames);



    }
}

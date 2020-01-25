package org.fasttrackit;


import org.fasttrackit.domain.Book;
import org.fasttrackit.persistance.BookRepository;
import org.fasttrackit.transfer.CreateNewName;
import org.fasttrackit.transfer.UpdateName;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class App
{
    public static void main( String[] args ) throws IOException, SQLException {

        BookRepository bookRepository = new BookRepository();

        List<Book> getNames = bookRepository.getNames();
        System.out.println(getNames);


    }
}

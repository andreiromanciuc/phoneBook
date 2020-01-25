package org.fasttrackit;


import org.fasttrackit.persistance.BookRepository;
import org.fasttrackit.transfer.CreateNewName;

import java.io.IOException;
import java.sql.SQLException;

public class App
{
    public static void main( String[] args ) throws IOException, SQLException {
        CreateNewName createName = new CreateNewName();
        createName.setName("Andrei");
        createName.setPhone("+40752465671");
        createName.setAddress("str. Sesul de Sus, nr.57, s. Floresti, jud. Cluj, Romania");

        BookRepository bookRepository = new BookRepository();
        bookRepository.createName(createName);
    }
}

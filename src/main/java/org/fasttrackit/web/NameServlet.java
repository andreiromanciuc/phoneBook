package org.fasttrackit.web;

import org.fasttrackit.service.BookService;
import org.fasttrackit.transfer.CreateNewName;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet ("/names")
public class NameServlet extends HttpServlet {

    private BookService bookService = new BookService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}

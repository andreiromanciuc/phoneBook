package org.fasttrackit.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.fasttrackit.config.ObjectMapperConfiguration;
import org.fasttrackit.service.BookService;
import org.fasttrackit.transfer.CreateNewName;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet ("/names")
public class NameServlet extends HttpServlet {

    private BookService bookService = new BookService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
         CreateNewName createNewName = ObjectMapperConfiguration.objectMapper
                 .readValue(req.getReader(), CreateNewName.class);

        try {
            bookService.createName(createNewName);
        } catch (SQLException e) {
            resp.sendError(500, "Internal server error: "+ e.getMessage());
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try{
            bookService.getNames();
        } catch (SQLException e) {
            resp.sendError(500, "Internal server error: "+e.getMessage());
        }
    }
}

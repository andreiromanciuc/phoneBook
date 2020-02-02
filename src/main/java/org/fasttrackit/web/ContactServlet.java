package org.fasttrackit.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.fasttrackit.config.ObjectMapperConfiguration;
import org.fasttrackit.domain.Book;
import org.fasttrackit.service.BookService;
import org.fasttrackit.transfer.CreateNewName;
import org.fasttrackit.transfer.DeleteNames;
import org.fasttrackit.transfer.UpdateName;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet ("/names")
public class ContactServlet extends HttpServlet {

    private BookService bookService = new BookService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
         CreateNewName createNewName = ObjectMapperConfiguration.objectMapper
                 .readValue(req.getReader(), CreateNewName.class);

        try {
            bookService.createName(createNewName);
        } catch (SQLException | ClassNotFoundException e) {
            resp.sendError(500, "Internal server error: "+ e.getMessage());
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String firstName = req.getParameter("firstName");
        String lastName = req.getParameter("lastName");

        if (firstName!=null){
            try{
                List<Book> names =bookService.getContactsByFirstName();

                String response =ObjectMapperConfiguration.objectMapper.writeValueAsString(names);
                resp.getWriter().print(response);

            } catch (SQLException | ClassNotFoundException e) {
                resp.sendError(500, "Internal server error: "+e.getMessage());
            }
        } else if (lastName != null) {
            try{
                List<Book> names =bookService.getContactsByLastName();

                String response =ObjectMapperConfiguration.objectMapper.writeValueAsString(names);
                resp.getWriter().print(response);

            } catch (SQLException | ClassNotFoundException e) {
                resp.sendError(500, "Internal server error: "+e.getMessage());
            }
        } else try{
            List<Book> names =bookService.getNames();

            String response =ObjectMapperConfiguration.objectMapper.writeValueAsString(names);
            resp.getWriter().print(response);

        } catch (SQLException | ClassNotFoundException e) {
            resp.sendError(500, "Internal server error: "+e.getMessage());
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String id = req.getParameter("id");
        if (id != null) {
            try {
                bookService.deleteName(Long.parseLong(id));

            } catch (SQLException | ClassNotFoundException e) {
                resp.sendError(500, "Internal Server Error: " + e.getMessage());
            }
        } else try {
            bookService.deleteWhereAddressIsNull(null);
        } catch (SQLException | ClassNotFoundException e) {
            resp.sendError(500, "Internal Server Error: " + e.getMessage());
        }

    }



    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");

        UpdateName updateName = ObjectMapperConfiguration.objectMapper.readValue(req.getReader(), UpdateName.class);

        try {
            bookService.updateName(Long.parseLong(id), updateName);
        } catch (SQLException | ClassNotFoundException e) {
            resp.sendError(500, "Internal Server Error: "+e.getMessage());
        }

    }
}

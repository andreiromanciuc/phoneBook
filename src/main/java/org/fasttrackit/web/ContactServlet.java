package org.fasttrackit.web;

import org.fasttrackit.config.ObjectMapperConfiguration;
import org.fasttrackit.domain.Contacts;
import org.fasttrackit.service.BookService;
import org.fasttrackit.transfer.CreateContact;
import org.fasttrackit.transfer.UpdateContact;

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
         CreateContact createContact = ObjectMapperConfiguration.objectMapper
                 .readValue(req.getReader(), CreateContact.class);

        try {
            bookService.createContact(createContact);
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
                List<Contacts> contacts =bookService.getContactsByFirstName();

                String response =ObjectMapperConfiguration.objectMapper.writeValueAsString(contacts);
                resp.getWriter().print(response);

            } catch (SQLException | ClassNotFoundException e) {
                resp.sendError(500, "Internal server error: "+e.getMessage());
            }
        } else if (lastName != null) {
            try{
                List<Contacts> contacts =bookService.getContactsByLastName();

                String response =ObjectMapperConfiguration.objectMapper.writeValueAsString(contacts);
                resp.getWriter().print(response);

            } catch (SQLException | ClassNotFoundException e) {
                resp.sendError(500, "Internal server error: "+e.getMessage());
            }
        } else try{
            List<Contacts> contacts =bookService.getContacts();

            String response =ObjectMapperConfiguration.objectMapper.writeValueAsString(contacts);
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
                bookService.deleteContact(Long.parseLong(id));

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

        UpdateContact updateContact = ObjectMapperConfiguration.objectMapper.readValue(req.getReader(), UpdateContact.class);

        try {
            bookService.updateContact(Long.parseLong(id), updateContact);
        } catch (SQLException | ClassNotFoundException e) {
            resp.sendError(500, "Internal Server Error: "+e.getMessage());
        }

    }
}

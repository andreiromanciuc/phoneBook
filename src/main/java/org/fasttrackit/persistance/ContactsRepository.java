package org.fasttrackit.persistance;

import org.fasttrackit.domain.Contact;
import org.fasttrackit.transfer.*;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ContactsRepository {

    public void createContact(CreateContact createContact) throws IOException, SQLException, ClassNotFoundException {
        String sql = "INSERT INTO book (first_name, last_name, phone_numbers, address) VALUES (?,?,?,?)";

        try (Connection connection = DatabaseConfiguration.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql);) {

            preparedStatement.setString(1, createContact.getFirstName());
            preparedStatement.setString(2, createContact.getLastName());
            preparedStatement.setString(3, createContact.getPhone());
            preparedStatement.setString(4, createContact.getAddress());
            preparedStatement.executeUpdate();
        }
    }

    public void updateContact(long id, UpdateContact updateContact) throws SQLException, IOException, ClassNotFoundException {
        String slq = "UPDATE book SET first_name = ?, last_name = ?, phone_numbers = ?, address = ? WHERE id = ?";

        try (Connection connection = DatabaseConfiguration.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(slq)) {
            preparedStatement.setString(1, updateContact.getFirstName());
            preparedStatement.setString(2, updateContact.getLastName());
            preparedStatement.setString(3, updateContact.getPhone());
            preparedStatement.setString(4, updateContact.getAddress());
            preparedStatement.setLong(5, id);
            preparedStatement.executeUpdate();
        }
    }

    public void deleteContact(long id) throws SQLException, IOException, ClassNotFoundException {
        String sql = "DELETE FROM book WHERE id = ?";

        try (Connection connection = DatabaseConfiguration.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        }
    }

    public void deleteWhereAddressIsNull (DeleteContact deleteContact) throws IOException, SQLException, ClassNotFoundException {
        String sql = "DELETE FROM book WHERE address IS NULL";

       try (Connection connection = DatabaseConfiguration.getConnection()){
           PreparedStatement preparedStatement = connection.prepareStatement(sql);
           preparedStatement.executeUpdate();
       }

    }

    public List<Contact> getContacts() throws IOException, SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM book";

        try (Connection connection = DatabaseConfiguration.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {

            List<Contact> names = new ArrayList<>();
            while (resultSet.next()) {
                Contact contact = new Contact();
                contact.setId(resultSet.getLong("id"));
                contact.setFirstName(resultSet.getString("first_name"));
                contact.setLastName(resultSet.getString("last_name"));
                contact.setPhone(resultSet.getString("phone_numbers"));
                contact.setAddress(resultSet.getString("address"));
                names.add(contact);
            }
            return names;
        }
    }

    public List<Contact> getContactsByFirstName(GetByFirstName getByFirstName) throws IOException, SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM book WHERE first_name LIKE ?";

        try (Connection connection = DatabaseConfiguration.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery(sql)) {

            preparedStatement.setString(1, getByFirstName.getFirstName());
            preparedStatement.executeUpdate();

            List<Contact> contacts = new ArrayList<>();

            while (resultSet.next()) {
                Contact book = new Contact();
                book.setFirstName(resultSet.getString("first_name"));
                book.setLastName(resultSet.getString("last_name"));
                book.setPhone(resultSet.getString("phone_numbers"));
                book.setAddress(resultSet.getString("address"));
                contacts.add(book);
            }
            return contacts;
        }
    }

    public List<Contact> getContactsByLastName(GetByLastName getByLastName) throws SQLException, IOException, ClassNotFoundException {
        String sql = "SELECT * FROM book WHERE last_name LIKE ?";

        try (Connection connection = DatabaseConfiguration.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {

            List<Contact> contacts = new ArrayList<>();
            while (resultSet.next()) {
                Contact book = new Contact();
                book.setLastName(resultSet.getString("last_name"));
                contacts.add(book);
            }
            return contacts;
        }

    }
}

package org.fasttrackit.persistance;

import org.fasttrackit.domain.Contacts;
import org.fasttrackit.transfer.CreateContact;
import org.fasttrackit.transfer.DeleteContact;
import org.fasttrackit.transfer.UpdateContact;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ContactsRepository {

    public void createName(CreateContact createContact) throws IOException, SQLException, ClassNotFoundException {
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

    public void updateName(long id, UpdateContact updateContact) throws SQLException, IOException, ClassNotFoundException {
        String slq = "UPDATE book SET first_name = ?, last_name = ?, phone_numbers = ?, address = ? WHERE id = ?";

        try (Connection connection = DatabaseConfiguration.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(slq);) {
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
             PreparedStatement preparedStatement = connection.prepareStatement(sql);) {
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

    public List<Contacts> getNames() throws IOException, SQLException, ClassNotFoundException {
        String sql = "SELECT id, first_name, last_name, phone_numbers, address FROM book";

        try (Connection connection = DatabaseConfiguration.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {

            List<Contacts> names = new ArrayList<>();

            while (resultSet.next()) {
                Contacts contacts = new Contacts();
                contacts.setId(resultSet.getLong("id"));
                contacts.setFirstName(resultSet.getString("first_name"));
                contacts.setLastName(resultSet.getString("last_name"));
                contacts.setPhone(resultSet.getString("phone_numbers"));
                contacts.setAddress(resultSet.getString("address"));
                names.add(contacts);
            }
            return names;
        }
    }

    public List<Contacts> getContactsByFirstName() throws IOException, SQLException, ClassNotFoundException {
        String sql = "SELECT first_name FROM book";

        try (Connection connection = DatabaseConfiguration.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {


            List<Contacts> contacts = new ArrayList<>();
            while (resultSet.next()) {
                Contacts book = new Contacts();
                book.setFirstName(resultSet.getString("first_name"));
                contacts.add(book);
            }
            return contacts;

        }
    }

    public List<Contacts> getContactsByLastName() throws SQLException, IOException, ClassNotFoundException {
        String sql = "SELECT last_name FROM book";

        try (Connection connection = DatabaseConfiguration.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {

            List<Contacts> contacts = new ArrayList<>();
            while (resultSet.next()) {
                Contacts book = new Contacts();
                book.setLastName(resultSet.getString("last_name"));
                contacts.add(book);
            }
            return contacts;
        }

    }
}

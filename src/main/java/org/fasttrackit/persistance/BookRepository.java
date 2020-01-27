package org.fasttrackit.persistance;

import org.fasttrackit.domain.Book;
import org.fasttrackit.transfer.CreateNewName;
import org.fasttrackit.transfer.DeleteNames;
import org.fasttrackit.transfer.UpdateName;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookRepository {

    public void createName(CreateNewName createNewName) throws IOException, SQLException {
        String sql = "INSERT INTO book (first_name, last_name, phone_numbers, address) VALUES (?,?,?,?)";

        try (Connection connection = DatabaseConfiguration.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql);) {

            preparedStatement.setString(1, createNewName.getFirstName());
            preparedStatement.setString(2, createNewName.getLastName());
            preparedStatement.setString(3, createNewName.getPhone());
            preparedStatement.setString(4, createNewName.getAddress());
            preparedStatement.executeUpdate();
        }
    }

    public void updateName(long id, UpdateName updateName) throws SQLException, IOException {
        String slq = "UPDATE book SET first_name = ?, last_name = ?, phone_numbers = ?, address = ? WHERE id = ?";

        try (Connection connection = DatabaseConfiguration.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(slq);) {
            preparedStatement.setString(1, updateName.getFirstName());
            preparedStatement.setString(2, updateName.getLastName());
            preparedStatement.setString(3, updateName.getPhone());
            preparedStatement.setString(4, updateName.getAddress());
            preparedStatement.setLong(5, id);
            preparedStatement.executeUpdate();
        }
    }

    public void delete(long id) throws SQLException, IOException {
        String sql = "DELETE FROM book WHERE id = ?";

        try (Connection connection = DatabaseConfiguration.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql);) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        }
    }

    public void deleteWhereAddressIsNull (DeleteNames deleteNames) throws IOException, SQLException {
        String sql = "DELETE FROM book WHERE address IS NULL";

       try (Connection connection = DatabaseConfiguration.getConnection()){
           PreparedStatement preparedStatement = connection.prepareStatement(sql);
           preparedStatement.executeUpdate();
       }

    }

    public List<Book> getNames() throws IOException, SQLException {
        String sql = "SELECT id, first_name, last_name, phone_numbers, address FROM book";

        try (Connection connection = DatabaseConfiguration.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {

            List<Book> names = new ArrayList<>();

            while (resultSet.next()) {
                Book book = new Book();
                book.setId(resultSet.getLong("id"));
                book.setFirstName(resultSet.getString("first_name"));
                book.setLastName(resultSet.getString("last_name"));
                book.setPhone(resultSet.getString("phone_numbers"));
                book.setAddress(resultSet.getString("address"));
                names.add(book);
            }
            return names;
        }
    }

    public List<Book> getContactsByFirstName() throws IOException, SQLException {
        String sql = "SELECT first_name FROM book";

        try (Connection connection = DatabaseConfiguration.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql);) {

            List<Book> contacts = new ArrayList<>();
            while (resultSet.next()) {
                Book book = new Book();
                book.setFirstName(resultSet.getString("first_name"));
                contacts.add(book);
            }
            return contacts;
        }
    }

    public List<Book> getContactsByLastName() throws SQLException, IOException {
        String sql = "SELECT last_name FROM book";

        try (Connection connection = DatabaseConfiguration.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {

            List<Book> contacts = new ArrayList<>();
            while (resultSet.next()) {
                Book book = new Book();
                book.setLastName(resultSet.getString("last_name"));
                contacts.add(book);
            }
            return contacts;
        }

    }
}

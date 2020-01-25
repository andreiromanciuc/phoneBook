package org.fasttrackit.persistance;

import org.fasttrackit.domain.Book;
import org.fasttrackit.transfer.CreateNewName;
import org.fasttrackit.transfer.UpdateName;

import javax.swing.plaf.nimbus.State;
import javax.swing.text.html.HTMLDocument;
import javax.xml.crypto.Data;
import java.io.IOException;
import java.net.ConnectException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookRepository {

    public void createName(CreateNewName createNewName) throws IOException, SQLException {
        String sql = "INSERT INTO book (name, phone_numbers, address) VALUES (?,?,?)";

        try (Connection connection = DatabaseConfiguration.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql);) {

            preparedStatement.setString(1, createNewName.getName());
            preparedStatement.setString(2, createNewName.getPhone());
            preparedStatement.setString(3, createNewName.getAddress());
            preparedStatement.executeUpdate();
        }
    }

    public void updateName(long id, UpdateName updateName) throws SQLException, IOException {
        String slq = "UPDATE book SET phone_number = ?, address = ? WHERE id = ?";

        try (Connection connection = DatabaseConfiguration.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(slq);) {
            preparedStatement.setString(1, updateName.getPhone());
            preparedStatement.setString(2, updateName.getAddress());
            preparedStatement.setLong(3, id);
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

    public List<Book> getNames() throws IOException, SQLException {
        String sql = "SELECT id, name, phone-numbers, address FROM book";

        try (Connection connection = DatabaseConfiguration.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)){

            List<Book> names = new ArrayList<>();

            while (resultSet.next()) {
                Book book = new Book();
                book.setId(resultSet.getLong("id"));
                book.setName(resultSet.getString("name"));
                book.setPhone(resultSet.getString("phone_number"));
                book.setAddress(resultSet.getString("address"));
                names.add(book);
            }
            return names;
        }
    }
}

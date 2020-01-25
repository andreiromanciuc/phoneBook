package org.fasttrackit.persistance;

import org.fasttrackit.transfer.CreateNewName;
import org.fasttrackit.transfer.UpdateName;

import javax.swing.text.html.HTMLDocument;
import java.io.IOException;
import java.net.ConnectException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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
             PreparedStatement preparedStatement = connection.prepareStatement(slq);){
            preparedStatement.setString(1, updateName.getPhone());
            preparedStatement.setString(2, updateName.getAddress());
            preparedStatement.setLong(3, id);
            preparedStatement.executeUpdate();
        }
    }
}

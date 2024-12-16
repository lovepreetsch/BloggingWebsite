package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import beans.formBeans;

public class formDao {

    public int insertForm(formBeans beans) {
        String insertQuery = "INSERT "
        		           + "INTO "
        		           + "form (name, email, message) "
        		           + "VALUES (?, ?, ?)";

        // Using try-with-resources to ensure resources are closed properly
        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement ps = connection.prepareStatement(insertQuery)) {

            ps.setString(1, beans.getName());
            ps.setString(2, beans.getEmail());
            ps.setString(3, beans.getMessage());

            System.out.println("insertForm: " + ps);
            return ps.executeUpdate();

        } catch (SQLException ex) {
            System.err.println("insertForm: An exception occurred! " + ex.getMessage());
            ex.printStackTrace();
            return 0;
        }
    }
}


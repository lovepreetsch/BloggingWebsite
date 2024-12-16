package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import beans.SignupBeans;
import util.Encryption;

public class SignupDao {

    /**
     * Registers a new person in the database.
     * 
     * @param person The person object containing the data.
     * @return The number of rows affected (0 means failure, 1 means success).
     */
    public int registerPerson(SignupBeans signup) {
    	
    	String INSERT_QUERY = "INSERT INTO signup (firstname, lastname, email, contact, password, address, pincode) VALUES (?, ?, ?, ?, ?, ?, ?)";
        // Declare connection and statement
        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement ps = connection.prepareStatement(INSERT_QUERY)) {

            // Encrypt the password securely
            String pass = signup.getPassword();
            final String secretKey = "secretnote";
            String encryptedPass = Encryption.encrypt(pass, secretKey);

            int index = 1;
            // Set the parameters for the PreparedStatement
            ps.setString(index++, signup.getFirstName());
            ps.setString(index++, signup.getLastName());
            ps.setString(index++, signup.getEmail());
            ps.setString(index++, signup.getContact());
            ps.setString(index++, encryptedPass);
            ps.setString(index++, signup.getAddress());
            ps.setString(index++, signup.getPinCode());

            // Execute the insert statement
            return ps.executeUpdate(); // Return the number of rows affected

        } catch (SQLException e) {
            e.printStackTrace(); // For production, use proper logging here
            return 0; // Return 0 in case of failure
        }
    }

}


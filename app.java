import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class app {// Database credentials
    private static final String URL = "jdbc:mysql://localhost:3306/userdb";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    public static void main(String[] args) {
        try {
            // Load the JDBC driver (optional for newer versions)
            Class.forName("com.mysql.cj.jdbc.Driver");

            try ( // Establish connection
                    Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
                System.out.println("Connected to the database!");
                
                // CREATE: Insert data
                try (Statement statement = connection.createStatement()) {
                    // CREATE: Insert data
                    String insertQuery = "INSERT INTO users (name, email) VALUES ('John Doe', 'john@example.com')";
                    int rowsInserted = statement.executeUpdate(insertQuery);
                    System.out.println("Rows inserted: " + rowsInserted);
                    
                    // READ: Fetch data
                    String selectQuery = "SELECT * FROM users";
                    try (ResultSet resultSet = statement.executeQuery(selectQuery)) {
                        System.out.println("User records:");
                        while (resultSet.next()) {
                            int id = resultSet.getInt("id");
                            String name = resultSet.getString("name");
                            String email = resultSet.getString("email");
                            System.out.printf("ID: %d, Name: %s, Email: %s%n", id, name, email);
                        }   // UPDATE: Update data
                        String updateQuery = "UPDATE users SET email = 'john.doe@example.com' WHERE name = 'John Doe'";
                        int rowsUpdated = statement.executeUpdate(updateQuery);
                        System.out.println("Rows updated: " + rowsUpdated);
                        // DELETE: Delete data
                        String deleteQuery = "DELETE FROM users WHERE name = 'John Doe'";
                        int rowsDeleted = statement.executeUpdate(deleteQuery);
                        System.out.println("Rows deleted: " + rowsDeleted);
                        // Close resources
                    }
                }
            }
            System.out.println("Database connection closed!");

        } catch (ClassNotFoundException | SQLException e) {
        }
    }
}

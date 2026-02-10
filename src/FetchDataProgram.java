
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class FetchDataProgram {

    public static void main(String[] args) {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/canteen", "root", "kandarp2005");
        } catch (SQLException e) {
            System.out.println("Error connecting to the database: " + e.getMessage());
            return; // Stop execution if there is a connection error
        }

        String sql = "select items , cost FROM package";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
            	String item = resultSet.getString("items");
            	
            	int cost = resultSet.getInt("cost");
             
                System.out.println("\nITEMS :\n"+item);
                System.out.println("\nCOST :\n"+cost);
                System.out.println("___________________________________________________________________________________________________________________________________________");
            }
        } catch (SQLException e) {
            System.out.println("Error executing the query: " + e.getMessage());
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                System.out.println("Error closing the resources: " + e.getMessage());
            }
        }
    }
}
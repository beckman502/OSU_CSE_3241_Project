package selects;
import menu.GetDataMenuAction;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RentingCheckoutsReport extends GetDataMenuAction {

    public RentingCheckoutsReport(String description) {
        super(description);
    }

    public void execute(Connection connection, int userId) throws SQLException {
        String sql = "SELECT p.First_Name, p.Last_Name, COUNT(r.Serial_Number) AS Total_Rented " +
                "FROM Rentals r " +
                "INNER JOIN People p ON r.User_ID = p.User_ID " +
                "WHERE p.User_ID = ? " +
                "GROUP BY p.User_ID;";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, userId);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                String firstName = resultSet.getString("First_Name");
                String lastName = resultSet.getString("Last_Name");
                int totalRented = resultSet.getInt("Total_Rented");

                System.out.println("Member: " + firstName + " " + lastName);
                System.out.println("Total Items Rented: " + totalRented);
                System.out.println();
            }
        }
    }
}

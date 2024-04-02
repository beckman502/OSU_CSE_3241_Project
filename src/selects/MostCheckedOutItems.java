package selects;
import menu.GetDataMenuAction;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MostCheckedOutItems extends GetDataMenuAction {
    public MostCheckedOutItems(String description) {
        super(description);
    }

    public static void execute(Connection connection) throws SQLException {
        String sql = "SELECT p.First_Name, p.Last_Name, COUNT(r.Serial_Number) AS Total_Checked_Out " +
                "FROM People p " +
                "INNER JOIN Rentals r ON p.User_ID = r.Renter_ID " + // Assuming Renter_ID stores the member who rented the item
                "GROUP BY p.User_ID " +
                "ORDER BY Total_Checked_Out DESC " +
                "LIMIT 1;";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                String firstName = resultSet.getString("First_Name");
                String lastName = resultSet.getString("Last_Name");
                int totalCheckedOut = resultSet.getInt("Total_Checked_Out");

                System.out.println("Member with Most Items Checked Out:");
                System.out.println("Member: " + firstName + " " + lastName);
                System.out.println("Total Items Checked Out: " + totalCheckedOut);
                System.out.println();
            }
        }
    }

}

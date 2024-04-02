package selects;

import menu.GetDataMenuAction;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GetPopularItemReport extends GetDataMenuAction {
    public GetPopularItemReport(String description) {
        super(description);
    }

    public static void execute(Connection connection) throws SQLException {
        String sql = "SELECT i.Model_Name AS Item, SUM(r.Rental_Time) AS Total_Rental_Time, COUNT(r.Serial_Number) AS Total_Rented " +
                "FROM Rentals r " +
                "INNER JOIN Equipment e ON r.Serial_Number = e.Serial_Number " +
                "INNER JOIN Items i ON e.Serial_Number = i.Serial_Number " +
                "GROUP BY i.Item_ID " +
                "ORDER BY (Total_Rental_Time * Total_Rented) DESC, Total_Rented DESC " +  // Prioritize longer rentals and then total rented
                "LIMIT 1;";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                String itemName = resultSet.getString("Item");
                double totalRentalTime = resultSet.getDouble("Total_Rental_Time"); // Assuming Rental_Time is stored in hours (adjust if needed)
                int totalRented = resultSet.getInt("Total_Rented");

                System.out.println("Most Popular Item:");
                System.out.println("Item: " + itemName);
                System.out.println("Total Rental Time: " + totalRentalTime + " hours"); // Adjust unit display based on your data
                System.out.println("Total Times Rented: " + totalRented);
                System.out.println();
            }
        }
    }
}

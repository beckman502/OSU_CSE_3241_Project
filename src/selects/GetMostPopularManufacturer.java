package selects;

import menu.GetDataMenuAction;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
public class GetMostPopularManufacturer extends GetDataMenuAction {

    public GetMostPopularManufacturer(String description) {
        super(description);
    }

    public static void execute(Connection connection) throws SQLException {
        String sql = "SELECT e.Manufacturer, COUNT(r.Serial_Number) AS Total_Rented " +
                "FROM Rentals r " +
                "INNER JOIN Equipment e ON r.Serial_Number = e.Serial_Number " +
                "GROUP BY e.Manufacturer " +
                "ORDER BY Total_Rented DESC " +
                "LIMIT 1;";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                String manufacturer = resultSet.getString("Manufacturer");
                int totalRented = resultSet.getInt("Total_Rented");

                System.out.println("Most Popular Manufacturer:");
                System.out.println("Manufacturer: " + manufacturer);
                System.out.println("Total Units Rented: " + totalRented);
                System.out.println();
            }
        }
    }
}

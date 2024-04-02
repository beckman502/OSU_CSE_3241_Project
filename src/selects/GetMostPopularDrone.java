package selects;
import menu.GetDataMenuAction;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GetMostPopularDrone extends GetDataMenuAction {
    public GetMostPopularDrone(String description) {
        super(description);
    }

    public static void execute(Connection connection) throws SQLException {
        String sql = "SELECT i.Model_Name AS Drone, SUM(r.Distance_Flown) AS Total_Distance, COUNT(r.Delivery_ID) AS Total_Deliveries " +
                "FROM Rentals r " +
                "INNER JOIN Equipment e ON r.Serial_Number = e.Serial_Number " +
                "INNER JOIN Items i ON e.Serial_Number = i.Serial_Number " +
                "WHERE e.Equipment_Type = 'Drone' " +  // Filter for Drone Equipment type
                "GROUP BY i.Item_ID " +
                "ORDER BY Total_Distance DESC, Total_Deliveries DESC " +
                "LIMIT 1;";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                String droneModel = resultSet.getString("Drone");
                double totalDistance = resultSet.getDouble("Total_Distance");
                int totalDeliveries = resultSet.getInt("Total_Deliveries");

                System.out.println("Most Popular Drone:");
                System.out.println("Model: " + droneModel);
                System.out.println("Total Distance Flown: " + totalDistance + " miles"); // Assuming Distance_Flown is stored in miles
                System.out.println("Total Deliveries: " + totalDeliveries);
                System.out.println();
            }
        }
    }
}

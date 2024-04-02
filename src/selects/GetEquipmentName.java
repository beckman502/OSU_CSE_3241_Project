package selects;

import menu.GetDataMenuAction;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GetEquipmentName extends GetDataMenuAction {
    public GetEquipmentName(String description) {
        super(description);
    }

    public static void equipmentByTypeReport(Connection connection, String equipmentType, Integer year) throws SQLException {
        String sql = "SELECT e.Description " +
                "FROM Equipment e ";

        StringBuilder whereClause = new StringBuilder(); // Build WHERE clause dynamically

        if (equipmentType != null) {
            whereClause.append(" WHERE e.Equipment_Type = ? "); // Add condition for equipment type
        }

        if (year != null) {
            if (whereClause.length() > 0) {
                whereClause.append(" AND "); // Add AND if previous condition exists
            }
            whereClause.append(" e.Year_Released < ? "); // Add condition for year released
        }

        // Append WHERE clause to the main SQL statement (if any conditions were added)
        if (whereClause.length() > 0) {
            sql += whereClause.toString();
        }

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            int parameterIndex = 1;
            if (equipmentType != null) {
                statement.setString(parameterIndex++, equipmentType);
            }
            if (year != null) {
                statement.setInt(parameterIndex++, year);
            }

            ResultSet resultSet = statement.executeQuery();

            System.out.println("Equipment Description (" +
                    (equipmentType != null ? equipmentType : "All Types") + " - " +
                    (year != null ? "Released before " + year : "All Years") + "):");

            while (resultSet.next()) {
                String description = resultSet.getString("Description");
                System.out.println("- " + description);
            }

            if (!resultSet.isBeforeFirst()) {
                System.out.println("  No equipment found matching the criteria.");
            }
        }
    }
}

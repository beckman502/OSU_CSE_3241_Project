package updates;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import menu.ActionMenuItem;

public class PostCommunityMemberDataAction extends PostDataMenuAction {

    public PostCommunityMemberDataAction(String description) {
        super(description);
    }

    @Override
    public void execute(ActionMenuItem callingAction) {
        /*
         * Probably need this to check if the community member already exists
         * and if they do we do an update instead of an insert.
         */
        String sqlPeople = "INSERT INTO People (User_ID, Email, First_Name, Last_Name, Address, Phone_Number) "
                + "VALUES (?, ?, ?, ?, ? , ?);";
        String sqlCommunityMember = "INSERT INTO Community_Members (User_ID, Status, Join_Date, Warehouse_distance) "
                + " VALUES (?, ? , ? , ?);";
        try {
            PreparedStatement ppSPeople = ActionMenuItem.sqlHandler
                    .getPreparedStatement(sqlPeople);
            PreparedStatement ppSCommunityMembers = ActionMenuItem.sqlHandler
                    .getPreparedStatement(sqlCommunityMember);
            int id, warehouseDistance;
            String firstName, lastName, address, phoneNumber, email, status,
                    joinDate;
            System.out.println("Please enter a userID: ");
            id = Integer.parseInt(this.stdin.nextLine());
            ppSPeople.setInt(1, id);
            ppSCommunityMembers.setInt(1, id);

            System.out.println("Please enter a first name:");
            firstName = this.stdin.nextLine();
            ppSPeople.setString(2, firstName);
            System.out.println("Please enter a last name:");
            lastName = this.stdin.nextLine();
            System.out.println("Please enter an address:");
            address = this.stdin.nextLine();
            System.out.println("Please enter a phone number:");
            phoneNumber = this.stdin.nextLine();
            System.out.println("Please enter an email: ");
            email = this.stdin.nextLine();
            System.out.println("Please enter a status: ");
            status = this.stdin.nextLine();
            System.out.println("Please enter a join date: ");
            joinDate = this.stdin.nextLine();
            System.out.println("Please enter a distance from the warehouse: ");
            warehouseDistance = this.stdin.nextInt();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        this.goBack(callingAction);
    }
}
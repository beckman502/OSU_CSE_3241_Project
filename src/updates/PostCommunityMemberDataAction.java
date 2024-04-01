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
            System.out.println("Please enter an email: ");
            email = this.stdin.nextLine();
            ppSPeople.setString(2, email);
            System.out.println("Please enter a first name:");
            firstName = this.stdin.nextLine();
            ppSPeople.setString(3, firstName);
            System.out.println("Please enter a last name:");
            lastName = this.stdin.nextLine();
            ppSPeople.setString(4, lastName);
            System.out.println("Please enter an address:");
            address = this.stdin.nextLine();
            ppSPeople.setString(5, address);
            System.out.println("Please enter a phone number:");
            phoneNumber = this.stdin.nextLine();
            ppSPeople.setString(6, phoneNumber);

            System.out.println("Please enter a status: ");
            status = this.stdin.nextLine();
            ppSCommunityMembers.setString(2, status);
            System.out.println("Please enter a join date: ");
            joinDate = this.stdin.nextLine();
            ppSCommunityMembers.setString(3, joinDate);
            System.out.println("Please enter a distance from the warehouse: ");
            warehouseDistance = this.stdin.nextInt();
            ppSCommunityMembers.setInt(4, warehouseDistance);
//            ActionMenuItem.sqlHandler.sqlQuery(ppSPeople);
//            ActionMenuItem.sqlHandler.sqlQuery(ppSCommunityMembers);
            if (ppSPeople.executeUpdate() < 0
                    || ppSCommunityMembers.executeUpdate() < 0) {
                System.out.println("Uh oh, that didn't work quiet right :(");
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        this.goBack(callingAction);
    }
}
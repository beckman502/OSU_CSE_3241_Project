package updates;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import menu.ActionMenuItem;

public class PostRentalDataAction extends PostDataMenuAction {

    public PostRentalDataAction(String description) {
        super(description);
    }

    @Override
    public void execute(ActionMenuItem callingAction) {
        /*
         * Probably need this to check if the community member already exists
         * and if they do we do an update instead of an insert.
         */
        String sqlRental = "INSERT INTO Rental (Agreement_Number, Start_Date, End_Date, Total_Fee, Number_of_Days, User_ID, Serial_Number, Drone_Serial_Number) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?);";

        try {
            boolean valid = true;

            PreparedStatement ppSRental = ActionMenuItem.sqlHandler
                    .getPreparedStatement(sqlRental);
            int agreementNum, userId, numDays, equipSN, droneSN;
            String startDate, endDate;
            double totalFee;

            //Check if userID is valid, active
            System.out.println("Please enter a userID: ");
            userId = Integer.parseInt(this.stdin.nextLine());
            ppSRental.setInt(2, userId);

            //Check if start date is valid?
            System.out.println("Please enter a start date: ");
            startDate = this.stdin.nextLine();
            ppSRental.setString(1, startDate);

            //Check if end date is valid - after start date?
            System.out.println("Please enter an end date: ");
            endDate = this.stdin.nextLine();
            ppSRental.setString(2, endDate);

            //Check if number of days matches start and end date?
            System.out.println(
                    "Please enter the number of days of the agreement terms: ");
            numDays = Integer.parseInt(this.stdin.nextLine());
            ppSRental.setInt(3, numDays);

            //Check if equipment is available
            System.out.println(
                    "Please enter the serial number of the equipment being rented: ");
            equipSN = Integer.parseInt(this.stdin.nextLine());
            ppSRental.setInt(4, equipSN);

            //Get agreement number, total fee, drone number

            //Change equipment status, assign drone route, etc???

            //For now, allowed to "not do anything with the data" - ?
            if (valid) {
                System.out.println("Equipment rented.");
            }

            if (ppSRental.executeUpdate() < 0) {
                System.out.println("Uh oh, that didn't work quiet right :(");
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        this.goBack(callingAction);
    }
}
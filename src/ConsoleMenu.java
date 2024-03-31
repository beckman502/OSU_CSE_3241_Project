import java.util.ArrayList;
import java.util.Scanner;

/**
 * This is a Console Menu Object.
 *
 * @author William Comer
 *
 */
public class ConsoleMenu extends ActionMenuItem {

    private String menuHeader;
    private ArrayList<ActionMenuItem> menuItems;
    private Scanner inputStream;

    /**
     * Public no arg constructor.
     */
    public ConsoleMenu(String headerText) {
        this.menuHeader = headerText;
        this.menuItems = new ArrayList<ActionMenuItem>();
        this.inputStream = new Scanner(System.in);
    }

    @Override
    public String toString() {
        return this.menuHeader;
    }

    public void addMenuItem(ActionMenuItem item) {
        this.menuItems.add(item);
    }

    public void print() {
        System.out.println(this.menuHeader);
        for (int i = 0; i < this.menuItems.size(); i++) {
            System.out.println((i + 1) + ".) " + this.menuItems.get(i));
        }
    }

    @Override
    public void execute(ActionMenuItem callingAction) {

        if (callingAction != null && !this.menuItems.contains(callingAction)) {
            this.addMenuItem(callingAction);
        }
        this.print();
        int userChoice = -1;
        do {
            System.out
                    .println("Enter the number corresponding to your choice.");
            String userInput = this.inputStream.next();

            try {
                userChoice = Integer.parseInt(userInput);
                if (userChoice > this.menuItems.size() || userChoice < 1) {
                    System.out.println(
                            "Please only enter a number displayed next to an option above.");
                    userChoice = -1;
                }
            } catch (NumberFormatException e) {
                System.out.println(
                        "Please only enter a number displayed next to an option above.");
            }

        } while (userChoice < 1);

        this.menuItems.get(userChoice - 1).execute(this);

    }

    /**
     * This method instantiates a ConsoleMenu object.
     *
     * @param args
     */
    public static void main(String[] args) {

        /*
         * Create some fake people.
         */

        FakeDataStore.CommunityMemberTable.add(new CommunityMemberEntity(1,
                "William", "Comer", "293 East 17th", "513-252-3433",
                "comer.157@osu.edu", "Active", "05-12-15", 12));
        FakeDataStore.CommunityMemberTable.add(new CommunityMemberEntity(12,
                "Veer", "Hsk", "143 17th", "523-215-2133", "veer.157@osu.edu",
                "Active", "11-22-15", 2));
        FakeDataStore.CommunityMemberTable.add(new CommunityMemberEntity(6,
                "Audrey", "Blackshire", "59 59th Ave", "215-133-5468",
                "audrey@osu.edu", "Active", "12-12-12", 25));
        FakeDataStore.CommunityMemberTable.add(new CommunityMemberEntity(2,
                "Billy", "Jean", "250 W Norwich Ave", "513-252-0003",
                "boo.boo@boo.com", "Inactive", "05-12-24", 9));
        FakeDataStore.CommunityMemberTable.add(new CommunityMemberEntity(156135,
                "Jimmy", "Comer", "Buckeye Palace", "513-231-3433",
                "testing.out@buckeyes.com", "Active", "05-21-15", 156));

        ConsoleMenu menu = new ConsoleMenu("Main Menu");
        //sub menus:
        ConsoleMenu peopleMenu = new ConsoleMenu("People");
        //People Menu Actions
        ActionMenuItem registerPeople = new PostCommunityMemberDataAction(
                "Register Person");
        ActionMenuItem deactivatePeople = new PostDataMenuAction(
                "Deactivate Person");
        ActionMenuItem viewPeople = new GetCommunityMembersDataAction(
                "View Person");

        peopleMenu.addMenuItem(registerPeople);
        peopleMenu.addMenuItem(deactivatePeople);
        peopleMenu.addMenuItem(viewPeople);
        peopleMenu.addMenuItem(menu);

        ConsoleMenu itemsMenu = new ConsoleMenu("Items");
        //Items Menu Actions
        ConsoleMenu manageInventoryMenu = new ConsoleMenu("Manage Inventory");

        //View Items Menu
        ConsoleMenu viewInventoryMenu = new ConsoleMenu("View Inventory");

        ActionMenuItem viewAvailableItems = new GetDataMenuAction(
                "View Available Items");
        ActionMenuItem viewUnavailableItems = new GetDataMenuAction(
                "View Unavailable Items");
        ActionMenuItem searchForItem = new GetDataMenuAction("Search For Item");

        viewInventoryMenu.addMenuItem(viewAvailableItems);
        viewInventoryMenu.addMenuItem(viewUnavailableItems);
        viewInventoryMenu.addMenuItem(searchForItem);

        ActionMenuItem changeItemStatus = new PostDataMenuAction(
                "Change Item Status");

        ActionMenuItem addOrRemoveInventory = new PostDataMenuAction(
                "Add/Remove Inventory");

        manageInventoryMenu.addMenuItem(viewInventoryMenu);
        manageInventoryMenu.addMenuItem(changeItemStatus);
        manageInventoryMenu.addMenuItem(addOrRemoveInventory);

        itemsMenu.addMenuItem(manageInventoryMenu);

        ConsoleMenu rentMenu = new ConsoleMenu("Rent");

        ConsoleMenu rentItems = new ConsoleMenu("Rent Equipment");
        ConsoleMenu returnItems = new ConsoleMenu("Return Equipment");
        ConsoleMenu scheduleItemDelivery = new ConsoleMenu(
                "Schedule Drone Equipment Delivery");
        ConsoleMenu scheduleItemPickup = new ConsoleMenu(
                "Schedule Equipment Pickup");
        ConsoleMenu viewRentalDetails = new ConsoleMenu("View Rental Details");

        //Same Menu as in Items Menu
        rentItems.addMenuItem(viewAvailableItems);

        rentMenu.addMenuItem(rentItems);
        rentMenu.addMenuItem(scheduleItemDelivery);
        rentMenu.addMenuItem(scheduleItemPickup);
        rentMenu.addMenuItem(returnItems);
        rentMenu.addMenuItem(viewRentalDetails);

        ConsoleMenu orderMenu = new ConsoleMenu("Order");

        ActionMenuItem placeOrder = new PostDataMenuAction("Place Order");
        ActionMenuItem editOrder = new PostDataMenuAction("Edit Order");
        ActionMenuItem viewOrder = new GetDataMenuAction("View Order");
        ActionMenuItem changeOrderStatus = new PostDataMenuAction(
                "Change Order Status");

        orderMenu.addMenuItem(placeOrder);
        orderMenu.addMenuItem(editOrder);
        orderMenu.addMenuItem(viewOrder);
        orderMenu.addMenuItem(changeOrderStatus);

        ConsoleMenu reviewsMenu = new ConsoleMenu("Reviews");

        ActionMenuItem makeReviews = new PostDataMenuAction("Make Review");
        ActionMenuItem viewReviews = new GetDataMenuAction("View Reviews");

        reviewsMenu.addMenuItem(makeReviews);
        reviewsMenu.addMenuItem(viewReviews);

        ConsoleMenu reports = new ConsoleMenu("Useful Reports");

        menu.addMenuItem(peopleMenu);
        menu.addMenuItem(itemsMenu);
        menu.addMenuItem(rentMenu);
        menu.addMenuItem(orderMenu);
        menu.addMenuItem(reviewsMenu);
        menu.addMenuItem(reports);
        menu.execute(menu);

    }
}

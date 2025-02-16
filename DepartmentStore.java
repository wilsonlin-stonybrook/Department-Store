
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * The <code>DepartmentStore</code> class is used to test the methods of
 * the item list class, and it allows the user to manipulate
 * an itemList object.
 */
public class DepartmentStore {
    public static void main(String[] args) {
        ItemList itemList = new ItemList();
        Scanner input = new Scanner(System.in);
        String option;
        String name, rfid, originalLocation;
        while (true) {
            try {
                printMenu();
                System.out.println("Please select an option: ");
                option = input.next().toUpperCase();
                input.nextLine();
                switch (option) {
                    case "C":
                        itemList.cleanStore();
                        break;
                    case "I":
                        System.out.println("Enter the name: ");
                        name = input.nextLine();
                        System.out.println("Enter the RFID: ");
                        rfid = input.nextLine().toUpperCase();
                        System.out.println("Enter the original location: ");
                        originalLocation = input.nextLine().toLowerCase();
                        System.out.println("Enter the price: ");
                        double price = input.nextDouble();
                        itemList.insertInfo(name, rfid, price, originalLocation);
                        break;
                    case "L":
                        System.out.println("Enter the location: ");
                        String location = input.nextLine().toLowerCase();
                        itemList.printByLocation(location);
                        break;
                    case "M":
                        System.out.println("Enter the RFID: ");
                        rfid = input.nextLine().toUpperCase();
                        System.out.println("Enter the original location: ");
                        originalLocation = input.nextLine().toLowerCase();
                        System.out.println("Enter the new location: ");
                        String newLocation = input.nextLine().toLowerCase();
                        itemList.moveItem(rfid, originalLocation, newLocation);
                        break;
                    case "R":
                        System.out.println("Enter the RFID: ");
                        rfid = input.nextLine().toUpperCase();
                        itemList.printByRFID(rfid);
                        break;
                    case "O":
                        System.out.println("Enter the cart number: ");
                        String cartNumber = input.nextLine().toLowerCase();
                        double total = itemList.checkOut(cartNumber);
                        System.out.printf("%-25s%.02f","The total cost for all merchandise in cart " + cartNumber.substring(1)
                                + " was $", total);
                        System.out.println();
                        break;
                    case "P":
                        itemList.printAll();
                        break;
                    case "U":
                        itemList.removeAllPurchased();
                        break;
                    case "Q":
                        System.out.println("Program terminating normally...");
                        System.exit(0);
                    default:
                        System.out.println("Invalid Choice.");
                }
            }
            catch (IllegalArgumentException | InputMismatchException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    /**
     * A method to print a menu for the user to choose from.
     */
    private static void printMenu() {
        System.out.println("C - Clean store");
        System.out.println("I - Insert an item into the list");
        System.out.println("L - List by location");
        System.out.println("M - Move an item in the store");
        System.out.println("O - Checkout");
        System.out.println("R - Print by RFID tag number");
        System.out.println("P - Print all items in store");
        System.out.println("U - Update inventory system");
        System.out.println("Q - Exit the program.");
    }
}

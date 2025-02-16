
/**
 * The <code>ItemList</code> class represents a doubly linked list of items.
 */
public class ItemList {
    private ItemInfoNode head;
    private ItemInfoNode tail;

    /**
     * Default constructor which initializes this object to an empty list of items
     * and sets head and tail to null.
     */
    public ItemList() {
        this.head = null;
        this.tail = null;
    }

    /**
     * A method to insert items into the list.
     * @param name
     * The name of the item to insert.
     * @param rfidTag
     * The rfidTag number of the item to insert.
     * @param price
     * The price of the item to insert.
     * @param initPosition
     * The initial position of th item to insert.
     */
    public void insertInfo(String name, String rfidTag, double price, String initPosition) throws IllegalArgumentException {
        // The Big-O notation of this method is O(n) because you may have to traverse
        // through the entire list to insert a new item.
        if (name.isEmpty() || name.length() > 20) {
            throw new IllegalArgumentException("Name cannot be empty or greater than 20 characters.");
        }
        if (!rfidTag.matches("[0-9A-Fa-f]{9}")) {
            throw new IllegalArgumentException("RFID must be a 9-character hexadecimal string including 0 - 9 or A - F.");
        }
        if (!initPosition.matches("s\\d{5}")) {
            throw new IllegalArgumentException("Original location must start with a s and include 5 digits after it.");
        }
        if (price < 0) {
            throw new IllegalArgumentException("Price must be positive.");
        }
        ItemInfo newItem = new ItemInfo(name, price, rfidTag, initPosition);
        ItemInfoNode newNode = new ItemInfoNode();
        newNode.setData(newItem);
        if (head == null) {
            head = newNode;
            tail = newNode;
            return;
        }
        ItemInfoNode current = head;
        ItemInfoNode previous = null;
        while (current != null && newItem.compareTo(current.getData()) > 0) {
            previous = current;
            current = current.getNext();
        }
        if (previous == null) {
            newNode.setNext(head);
            head = newNode;
        }
        else {
            newNode.setNext(current);
            previous.setNext(newNode);
        }
        if (newNode.getNext() == null) {
            tail = newNode;
        }
    }

    /**
     * A method to remove all purchased items from the list that is listed as "out" and
     * displays a list of all the items removed.
     */
    public void removeAllPurchased() {
        // The big O notation of this method is O(n) because you have to traverse
        // through the entire list to check each node to see what node to remove.

        System.out.println("The following items(s) have been removed from the system: ");
        System.out.println(toString());
        ItemInfoNode current = head;
        while (current != null) {
            ItemInfoNode next = current.getNext();
            if (current.getData().getCurrentLocation().equalsIgnoreCase("out")) {
                removeNode(current);
                System.out.println(current.getData());
            }
            current = next;
        }
    }

    /**
     * A helper method to remove an item from the list.
     * @param node
     * The node in the list to remove.
     */
    public void removeNode(ItemInfoNode node) {
        if (node.getPrev() != null) {
            node.getPrev().setNext(node.getNext());
        }
        else {
            head = node.getNext();
        }
        if (node.getNext() != null) {
            node.getNext().setPrev(node.getPrev());
        }
        else {
            tail = node.getPrev();
        }
    }

    /**
     * A method to move the item from a source location to a dest location.
     * @param rfidTag
     * The rfidTag number of the item to be moved.
     * @param source
     * The source location of the item to be moved.
     * @param dest
     * The destination location of the item to be moved.
     * @return
     * Return whether the item was found.
     * @throws IllegalArgumentException
     * Indicates <code>source</code> cannot be out.
     */
    public boolean moveItem(String rfidTag, String source, String dest) throws IllegalArgumentException {
        // The Big-O notation of this method is O(n) because you may have to traverse
        // through the entire ist to find the item.
        if (!rfidTag.matches("[0-9A-Fa-f]{9}")) {
            throw new IllegalArgumentException("RFID must be a 9-character hexadecimal string including 0 - 9 or A - F.");
        }
        if (!source.matches("s\\d{5}")
                && !source.matches("c\\d{3}")) {
            throw new IllegalArgumentException("Current location must be a shelf position, or a cart number.");
        }
        if (!dest.matches("s\\d{5}")
                && !dest.matches("c\\d{3}")
                && !dest.equalsIgnoreCase("out")) {
            throw new IllegalArgumentException("New location must be a shelf position, a cart number, or out.");
        }
        ItemInfoNode current = head;
        while (current != null) {
            if (current.getData().getRfidTagNumber().equalsIgnoreCase(rfidTag)
            && current.getData().getCurrentLocation().equalsIgnoreCase(source)) {
                if (current.getData().getCurrentLocation().equalsIgnoreCase("out")) {
                    throw new IllegalArgumentException("Source destination cannot be out");
                }
                current.getData().setCurrentLocation(dest);
                return true;
            }
            current = current.getNext();
        }
        return false;
    }

    /**
     * A method to print a neatly formatted list of all items in the list.
     */
    public void printAll() {
        // The Big-O notation of this method is O(n) because you have to
        // traverse through the entire list to print each node's data.
        System.out.println(toString());
        ItemInfoNode current = head;
        while (current != null) {
            System.out.println(current.getData());
            current = current.getNext();
        }
    }

    /**
     * Prints a neatly formatted list of all items in a specified location.
     * @param location
     * The location of the items to be printed in the table.
     */
    public void printByLocation(String location) {
        // The Big-O notation of this method is O(n) because you have
        // to traverse through the entire list to check each node's location
        // and then print the nodes with the same location.
        if (!location.matches("s\\d{5}")
                && !location.matches("c\\d{3}")
                && !location.equalsIgnoreCase("out")) {
            throw new IllegalArgumentException("Current location must be a shelf position, a cart number, or out.");
        }
        System.out.println(toString());
        ItemInfoNode current = head;
        while (current != null) {
            if (current.getData().getCurrentLocation().equalsIgnoreCase(location)) {
                System.out.println(current.getData());
            }
            current = current.getNext();
        }
    }

    /**
     * A method to take every item that is in the store and on the wrong shelf and place it where
     * it belongs.
     */
    public void cleanStore() {
        // The Big-O notation of this method is O(n) because you need to traverse through the entire list
        // to generate the items in a tabular list.
        System.out.println("The following items(s) have been moved back to their original locations: ");
        System.out.println(toString());
        ItemInfoNode current = head;
        while (current != null) {
            ItemInfo info = current.getData();
            if (!info.getCurrentLocation().equalsIgnoreCase("out") &&
                    !info.getCurrentLocation().equalsIgnoreCase(info.getOriginalLocation()) &&
                    !info.getCurrentLocation().startsWith("c")) {
                System.out.printf("%-16s%-17s%-16s%-15s%.2f", info.getName(), info.getRfidTagNumber(), info.getCurrentLocation(), info.getOriginalLocation(), info.getPrice());
                System.out.println();
                info.setCurrentLocation(info.getOriginalLocation());
            }
            current = current.getNext();
        }
    }

    /**
     * A method that goes through a t and checks out each item.
     * @param cartNumber
     * The cart number to check out.
     * @return
     * Returns the total price of all items in that cart.
     */
    public double checkOut(String cartNumber) {
        // The Big-O notation of this method is O(n) because you have to traverse through the
        // entire list to check and update the total.
        if (!cartNumber.matches("c\\d{3}")) {
            throw new IllegalArgumentException("Location must be a cart number.");
        }
        System.out.println(toString());
        double total = 0;
        ItemInfoNode current = head;
        while (current != null) {
            if (current.getData().getCurrentLocation().equalsIgnoreCase(cartNumber)) {
                total += current.getData().getPrice();
                System.out.println(current.getData());
                current.getData().setCurrentLocation("out");
            }
            current = current.getNext();
        }
        return total;
    }

    /**
     * A method to print all items that have the same rfid number.
     * @param rfid
     * The rfid of the items.
     */
    public void printByRFID(String rfid) {
        if (!rfid.matches("[0-9A-Fa-f]{9}")) {
            throw new IllegalArgumentException("RFID must be a 9-character hexadecimal string including 0 - 9 or A - F.");
        }
        System.out.println(toString());
        ItemInfoNode current = head;
        while (current != null) {
            if (current.getData().getRfidTagNumber().equalsIgnoreCase(rfid)) {
                System.out.printf("%-16s%-17s%-16s%-15s%.2f", current.getData().getName(), current.getData().getRfidTagNumber(), current.getData().getCurrentLocation(),
                        current.getData().getOriginalLocation(), current.getData().getPrice());
                System.out.println();
            }
            current = current.getNext();
        }
    }

    /**
     * A method to print a neat table.
     * @return
     * Returns a table to show all the items.
     */
    public String toString() {
        String string = "";
        string = String.format("%-16s%-16s%-16s%-16s%-2s", "", "", "Original", "Current", "");
        string += "\n" + String.format("%-18s%-14s%-16s%-16s%-2s", "Item Name", "RFID", "Location", "Location", "Price\n");
        string += String.format("%-16s%-16s%-16s%-16s%-2s", "---------", "---------", "---------", "---------", "------");
        return string;
    }
}

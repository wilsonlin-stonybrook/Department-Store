
/**
 * The <code>ItemInfo</code> class is used for the creation of a item object.
 * It represents the items that are being stored in the item list.
 */
public class ItemInfo {
    private String name;
    private String rfidTagNumber;
    private String originalLocation;
    private String currentLocation;
    private double price;

    /**
     * Parameterized constructor for creating an item info object with
     * specific name, price, rfidTagNumber, and originalLocation.
     * @param name
     * The name of the item.
     * @param price
     * The price of the item.
     * @param rfidTagNumber
     * The rfidTagNumber of the item.
     * @param originalLocation
     * The original location of the item.
     */
    public ItemInfo(String name, double price, String rfidTagNumber, String originalLocation) {
        this.name = name;
        this.price = price;
        this.rfidTagNumber = rfidTagNumber;
        this.originalLocation = originalLocation;
        this.currentLocation = originalLocation;
    }

    /**
     * Gets the name of the item.
     * @return
     * Returns the name of the item.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the item.
     * @param name
     * The name to set for the item.
     * @throws IllegalArgumentException
     * Indicates that <code>name</code> cannot be empty or greater than 20 characters.
     */
    public void setName(String name) {
        if (name.isEmpty() || name.length() > 20) {
            throw new IllegalArgumentException("Name cannot be empty or greater than 20 characters.");
        }
        this.name = name;
    }

    /**
     * Gets the rfid number of the item.
     * @return
     * Returns the rfid number of the item.
     */
    public String getRfidTagNumber() {
        return rfidTagNumber;
    }

    /**
     * Sets the rfid number of the item.
     * @param rfidTagNumber
     * The rfid number to set for the item.
     * @throws IllegalArgumentException
     * Indicates that <code>rfidTagNumber</code> must be a 9 character hexadecimal string
     * including 0-9 or A-F.
     */
    public void setRfidTagNumber(String rfidTagNumber) {
        if (!rfidTagNumber.matches("[0-9A-Fa-f]{9}")) {
            throw new IllegalArgumentException("RFID must be a 9-character hexadecimal string including 0 - 9 or A - F.");
        }
        this.rfidTagNumber = rfidTagNumber;
    }

    /**
     * Gets the original location of the item.
     * @return
     * Returns the original location of the item.
     */
    public String getOriginalLocation() {
        return originalLocation;
    }

    /**
     * Sets the original location of the item.
     * @param originalLocation
     * The original location to set for the item.
     * @throws IllegalArgumentException
     * Indicates that <code>originalLocation</code> must start with a s
     * and include 5 digits after it.
     */
    public void setOriginalLocation(String originalLocation) {
        if (!originalLocation.matches("s\\d{5}")) {
            throw new IllegalArgumentException("Original location must start with a s and include 5 digits after it.");
        }
        this.originalLocation = originalLocation;
    }

    /**
     * Gets the current location of the item.
     * @return
     * Returns the current location of the item.
     */
    public String getCurrentLocation() {
        return currentLocation;
    }

    /**
     * Sets the current location of the item.
     * @param currentLocation
     * The current location to set for the item.
     * @throws IllegalArgumentException
     * Indicates that <code>currentLocation</code> must be a shelf position, a cart number,
     * or out.
     */
    public void setCurrentLocation(String currentLocation) {
        if (!currentLocation.matches("s\\d{5}")
                && !currentLocation.matches("c\\d{3}")
                && !currentLocation.equalsIgnoreCase("out")) {
            throw new IllegalArgumentException("Current location must be a shelf position, a cart number, or out.");
        }
        this.currentLocation = currentLocation;
    }

    /**
     * Gets the price of the item.
     * @return
     * Returns the price of the item.
     */
    public double getPrice() {
        return price;
    }

    /**
     * Sets the price of the item.
     * @param price
     * The price to set for the item.
     * @throws IllegalArgumentException
     * Indicates that <code>price</code> must be positive.
     */
    public void setPrice(double price) {
        if (price < 0) {
            throw new IllegalArgumentException("Price must be positive.");
        }
        this.price = price;
    }

    /**
     * Compare to method to compare rfid numbers.
     * @param data
     * The data to compare the rfid number to.
     * @return
     * Returns 1 if the rfid is greater than the other rfid, 0 if they are equal, and
     * -1 of the rfid is less than the other rfid.
     */
    public int compareTo(ItemInfo data) {
        return this.getRfidTagNumber().compareTo(data.getRfidTagNumber());
    }

    /**
     * Returns a string representation of the item.
     * @return
     * Returns a formatted string containing the item's name, rfid number, original location,
     * current location, and price.
     */
    public String toString() {
        return String.format("%-16s%-17s%-16s%-15s%.2f", this.getName(), this.getRfidTagNumber(), this.getOriginalLocation(), this.getCurrentLocation(), this.getPrice());
    }
}

/**
 * Wilson Lin
 * 115091711
 * wilson.lin.2@stonybrook.edu
 * Hw 2
 * CSE214.R04 Summer 2024
 */

/**
 * The <code>ItemInfoNode</code> class is used for the creation of an item node object.
 * It represents a node in a doubly linked list containing the item info.
 */
public class ItemInfoNode {
    private ItemInfo data;
    private ItemInfoNode prev;
    private ItemInfoNode next;

    /**
     * Default constructor for creating a ItemInfoNode with default values.
     * Sets data, prev, and next to null.
     */
    public ItemInfoNode() {
        this.data = null;
        this.prev = null;
        this.next = null;
    }

    /**
     * Gets the item data of the node.
     * @return
     * Returns the item data of the node.
     */
    public ItemInfo getData() {
        return data;
    }

    /**
     * Sets the item data of the node.
     * @param data
     * The item data to set.
     */
    public void setData(ItemInfo data) {
        this.data = data;
    }

    /**
     * Gets the previous item data of the node.
     * @return
     * Returns the previous item data of the node.
     */
    public ItemInfoNode getPrev() {
        return prev;
    }

    /**
     * Sets the previous item data of the node.
     * @param prev
     * The previous item data to set.
     */
    public void setPrev(ItemInfoNode prev) {
        this.prev = prev;
    }

    /**
     * Gets the next item data of the node.
     * @return
     * Returns the next item data of the node.
     */
    public ItemInfoNode getNext() {
        return next;
    }

    /**
     * Sets the next item data of the node.
     * @param next
     * The next data to set.
     */
    public void setNext(ItemInfoNode next) {
        this.next = next;
    }
}

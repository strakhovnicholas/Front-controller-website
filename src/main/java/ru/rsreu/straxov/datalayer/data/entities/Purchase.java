package ru.rsreu.straxov.datalayer.data.entities;

/**
 * Represents a purchase made by a user in the system.
 * A purchase is associated with a user, a lot, and a bid.
 * This class stores the IDs related to these entities.
 */
public class Purchase {

    /** The ID of the user who made the purchase */
    private int userId;

    /** The ID of the lot being purchased */
    private int lotId;

    /** The ID of the bid associated with the purchase */
    private int bidId;

    /**
     * Constructs a new Purchase with the specified user ID, lot ID, and bid ID.
     *
     * @param userId the ID of the user making the purchase
     * @param lotId the ID of the lot being purchased
     * @param bidId the ID of the bid associated with the purchase
     */
    public Purchase(int userId, int lotId, int bidId) {
        this.userId = userId;
        this.lotId = lotId;
        this.bidId = bidId;
    }

    /**
     * Returns the user ID associated with this purchase.
     *
     * @return the user ID
     */
    public int getUserId() {
        return userId;
    }

    /**
     * Sets the user ID for this purchase.
     *
     * @param userId the user ID to set
     */
    public void setUserId(int userId) {
        this.userId = userId;
    }

    /**
     * Returns the lot ID associated with this purchase.
     *
     * @return the lot ID
     */
    public int getLotId() {
        return lotId;
    }

    /**
     * Sets the lot ID for this purchase.
     *
     * @param lotId the lot ID to set
     */
    public void setLotId(int lotId) {
        this.lotId = lotId;
    }

    /**
     * Returns the bid ID associated with this purchase.
     *
     * @return the bid ID
     */
    public int getBidId() {
        return bidId;
    }

    /**
     * Sets the bid ID for this purchase.
     *
     * @param bidId the bid ID to set
     */
    public void setBidId(int bidId) {
        this.bidId = bidId;
    }
}

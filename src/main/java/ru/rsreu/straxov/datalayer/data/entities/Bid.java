package ru.rsreu.straxov.datalayer.data.entities;

import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * The `Bid` class represents a bid in an auction system.
 * It contains information about the bid, such as the lot it is associated with,
 * the user who placed the bid, the bid amount, and the timestamp of the bid.
 *
 * <p>Additionally, the class includes details about the auctionist managing the bid
 * and the step value for price increments in the auction.
 */
public class Bid {
    /**
     * Static ID used for operations on bids.
     */
    private static int bidOperationId;

    /**
     * ID of the lot this bid is associated with.
     */
    private int lotId;

    /**
     * ID of the user who placed the bid.
     */
    private int userId;

    /**
     * The amount of the bid.
     */
    private BigDecimal bidAmount;

    /**
     * The timestamp when the bid was placed.
     */
    private Timestamp bidTime;

    /**
     * Unique ID of the bid.
     */
    private int bidId;

    /**
     * The auctionist ID managing the bid.
     */
    private int auctionistId;

    /**
     * The price step increment for the bid in the auction.
     */
    private BigDecimal bidPriceStep;

    /**
     * Default constructor for creating an empty `Bid` instance.
     */
    public Bid() {}

    /**
     * Parameterized constructor for creating a `Bid` instance with all fields.
     *
     * @param bidId         the unique ID of the bid
     * @param lotId         the ID of the lot this bid is associated with
     * @param userId        the ID of the user who placed the bid
     * @param bidAmount     the amount of the bid
     * @param bidTime       the timestamp when the bid was placed
     * @param auctionistId  the ID of the auctionist managing the bid
     * @param bidPriceStep  the price step increment for the bid in the auction
     */
    public Bid(int bidId, int lotId, int userId, BigDecimal bidAmount, Timestamp bidTime, int auctionistId, BigDecimal bidPriceStep) {
        this.lotId = lotId;
        this.userId = userId;
        this.bidAmount = bidAmount;
        this.bidTime = bidTime;
        this.bidId = bidId;
        this.auctionistId = auctionistId;
        this.bidPriceStep = bidPriceStep;
    }

    // Getters and Setters

    /**
     * Gets the static bid operation ID.
     *
     * @return the bid operation ID
     */
    public static int getBidOperationId() {
        return bidOperationId;
    }

    /**
     * Sets the static bid operation ID.
     *
     * @param bidOperationId the new bid operation ID
     */
    public static void setBidOperationId(int bidOperationId) {
        Bid.bidOperationId = bidOperationId;
    }

    /**
     * Gets the ID of the lot associated with the bid.
     *
     * @return the lot ID
     */
    public int getLotId() {
        return lotId;
    }

    /**
     * Sets the ID of the lot associated with the bid.
     *
     * @param lotId the new lot ID
     */
    public void setLotId(int lotId) {
        this.lotId = lotId;
    }

    /**
     * Gets the ID of the user who placed the bid.
     *
     * @return the user ID
     */
    public int getUserId() {
        return userId;
    }

    /**
     * Sets the ID of the user who placed the bid.
     *
     * @param userId the new user ID
     */
    public void setUserId(int userId) {
        this.userId = userId;
    }

    /**
     * Gets the amount of the bid.
     *
     * @return the bid amount
     */
    public BigDecimal getBidAmount() {
        return bidAmount;
    }

    /**
     * Sets the amount of the bid.
     *
     * @param bidAmount the new bid amount
     */
    public void setBidAmount(BigDecimal bidAmount) {
        this.bidAmount = bidAmount;
    }

    /**
     * Gets the timestamp of the bid.
     *
     * @return the bid timestamp
     */
    public Timestamp getBidTime() {
        return bidTime;
    }

    /**
     * Sets the timestamp of the bid.
     *
     * @param bidTime the new bid timestamp
     */
    public void setBidTime(Timestamp bidTime) {
        this.bidTime = bidTime;
    }

    /**
     * Gets the unique ID of the bid.
     *
     * @return the bid ID
     */
    public int getBidId() {
        return bidId;
    }

    /**
     * Gets the auctionist ID managing the bid.
     *
     * @return the auctionist ID
     */
    public int getAuctionistId() {
        return auctionistId;
    }

    /**
     * Sets the auctionist ID managing the bid.
     *
     * @param auctionistId the new auctionist ID
     */
    public void setAuctionistId(int auctionistId) {
        this.auctionistId = auctionistId;
    }

    /**
     * Gets the price step increment for the bid.
     *
     * @return the bid price step
     */
    public BigDecimal getBidPriceStep() {
        return bidPriceStep;
    }

    /**
     * Sets the price step increment for the bid.
     *
     * @param bidPriceStep the new bid price step
     */
    public void setBidPriceStep(BigDecimal bidPriceStep) {
        this.bidPriceStep = bidPriceStep;
    }
}

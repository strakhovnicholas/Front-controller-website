package ru.rsreu.straxov.datalayer.data.entities;

import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * The `Lot` class represents an auction lot with details such as title, description,
 * starting price, current price, end time, and the user managing the lot.
 *
 * <p>It implements the `Comparable` interface, allowing comparison between lots,
 * which can be customized in the `compareTo` method.
 */
public class Lot implements Comparable<Lot> {

    /**
     * Unique identifier for the lot.
     */
    private int lotId;

    /**
     * The title or name of the lot.
     */
    private String title;

    /**
     * A detailed description of the lot.
     */
    private String description;

    /**
     * The starting price of the lot.
     */
    private BigDecimal lotStartPrice;

    /**
     * The timestamp indicating the auction end time for the lot.
     */
    private Timestamp lotEndTime;

    /**
     * The current highest price for the lot.
     */
    private BigDecimal lotCurrentPrice;

    /**
     * The ID of the user who created or manages the lot.
     */
    private int userId;

    /**
     * The step increment for the lot's price during bidding.
     */
    private BigDecimal lotStep;

    /**
     * The step increment for the lot's price set by the seller.
     */
    private BigDecimal priceStep;

    /**
     * Default constructor for creating an empty `Lot` instance.
     */
    public Lot() {}

    /**
     * Parameterized constructor to create a `Lot` instance with all primary attributes.
     *
     * @param lotId          the unique identifier for the lot
     * @param title          the title or name of the lot
     * @param description    a detailed description of the lot
     * @param lotStartPrice  the starting price of the lot
     * @param lotEndTime     the timestamp indicating the auction end time
     * @param lotCurrentPrice the current highest price of the lot
     * @param userId         the ID of the user managing the lot
     */
    public Lot(int lotId, String title, String description, BigDecimal lotStartPrice, Timestamp lotEndTime, BigDecimal lotCurrentPrice, int userId) {
        this.lotId = lotId;
        this.title = title;
        this.description = description;
        this.lotStartPrice = lotStartPrice;
        this.lotEndTime = lotEndTime;
        this.lotCurrentPrice = lotCurrentPrice;
        this.userId = userId;
    }

    /**
     * Parameterized constructor to create a `Lot` instance with basic attributes.
     *
     * @param lotId         the unique identifier for the lot
     * @param title         the title or name of the lot
     * @param description   a detailed description of the lot
     * @param lotStartPrice the starting price of the lot
     * @param userId        the ID of the user managing the lot
     */
    public Lot(int lotId, String title, String description, BigDecimal lotStartPrice, int userId) {
        this.lotId = lotId;
        this.title = title;
        this.description = description;
        this.lotStartPrice = lotStartPrice;
        this.lotCurrentPrice = lotStartPrice;
        this.userId = userId;
    }

    /**
     * Parameterized constructor for creating a `Lot` with title, description, and starting price.
     *
     * @param title         the title or name of the lot
     * @param description   a detailed description of the lot
     * @param lotStartPrice the starting price of the lot
     */
    public Lot(String title, String description, BigDecimal lotStartPrice) {
        this.title = title;
        this.description = description;
        this.lotStartPrice = lotStartPrice;
        this.lotCurrentPrice = lotStartPrice;
    }

    /**
     * Parameterized constructor for creating a `Lot` with end time, title, and current price.
     *
     * @param lotEndTime    the timestamp indicating the auction end time
     * @param title         the title or name of the lot
     * @param description   a detailed description of the lot
     * @param lotCurrentPrice the current highest price of the lot
     */
    public Lot(Timestamp lotEndTime, String title, String description, BigDecimal lotCurrentPrice) {
        this.lotEndTime = lotEndTime;
        this.title = title;
        this.description = description;
        this.lotCurrentPrice = lotCurrentPrice;
    }

    /**
     * Parameterized constructor for creating a `Lot` with title, description, and end time.
     *
     * @param title        the title or name of the lot
     * @param description  a detailed description of the lot
     * @param lotEndTime   the timestamp indicating the auction end time
     */
    public Lot(String title, String description, Timestamp lotEndTime) {
        this.title = title;
        this.description = description;
        this.lotEndTime = lotEndTime;
    }

    // Getters and Setters

    /**
     * Gets the lot ID.
     *
     * @return the lot ID
     */
    public int getLotId() {
        return lotId;
    }

    /**
     * Gets the title of the lot.
     *
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets the title of the lot.
     *
     * @param title the new title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Gets the description of the lot.
     *
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the description of the lot.
     *
     * @param description the new description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Gets the starting price of the lot.
     *
     * @return the starting price
     */
    public BigDecimal getLotStartPrice() {
        return lotStartPrice;
    }

    /**
     * Sets the starting price of the lot.
     *
     * @param lotStartPrice the new starting price
     */
    public void setLotStartPrice(BigDecimal lotStartPrice) {
        this.lotStartPrice = lotStartPrice;
        this.lotCurrentPrice = lotStartPrice;
    }

    /**
     * Gets the end time of the lot.
     *
     * @return the end time
     */
    public Timestamp getLotEndTime() {
        return lotEndTime;
    }

    /**
     * Sets the end time of the lot.
     *
     * @param lotEndTime the new end time
     */
    public void setLotEndTime(Timestamp lotEndTime) {
        this.lotEndTime = lotEndTime;
    }

    /**
     * Gets the current price of the lot.
     *
     * @return the current price
     */
    public BigDecimal getLotCurrentPrice() {
        return lotCurrentPrice;
    }

    /**
     * Sets the current price of the lot.
     *
     * @param lotCurrentPrice the new current price
     */
    public void setLotCurrentPrice(BigDecimal lotCurrentPrice) {
        this.lotCurrentPrice = lotCurrentPrice;
    }

    /**
     * Gets the user ID managing the lot.
     *
     * @return the user ID
     */
    public int getUserId() {
        return userId;
    }

    /**
     * Sets the user ID managing the lot.
     *
     * @param userId the new user ID
     */
    public void setUserId(int userId) {
        this.userId = userId;
    }

    /**
     * Gets the price step increment for the lot.
     *
     * @return the price step increment
     */
    public BigDecimal getPriceStep() {
        return priceStep;
    }

    /**
     * Sets the price step increment for the lot.
     *
     * @param priceStep the new price step increment
     */
    public void setPriceStep(BigDecimal priceStep) {
        this.priceStep = priceStep;
    }

    /**
     * Compares this lot with another lot.
     *
     * @param o the other lot
     * @return the comparison result
     */
    @Override
    public int compareTo(Lot o) {
        return 0; // Modify this logic for specific sorting needs
    }
}

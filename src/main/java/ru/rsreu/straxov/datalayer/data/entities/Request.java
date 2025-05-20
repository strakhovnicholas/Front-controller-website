package ru.rsreu.straxov.datalayer.data.entities;

import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * Represents a request in the system, which includes details about a lot request,
 * including title, description, pricing, and timing information.
 */
public class Request {

    /** The unique identifier for this request */
    private int requestId;

    /** The title of the request */
    private String title;

    /** The description of the request */
    private String description;

    /** The starting price of the request */
    private BigDecimal startPrice;

    /** The ID of the user who created the request */
    private int userId;

    /** The current status of the request */
    private String status;

    /** The price step for the request (e.g., bid increments) */
    private BigDecimal priceStep;

    /** The end date and time of the request */
    private Timestamp endDate;

    /** The date and time when the request was created */
    private Timestamp createdDate;

    /**
     * Constructs a new Request with the specified details.
     *
     * @param requestId the unique identifier for the request
     * @param title the title of the request
     * @param description the description of the request
     * @param startPrice the starting price of the request
     * @param userId the ID of the user who created the request
     * @param status the current status of the request
     */
    public Request(int requestId, String title, String description, BigDecimal startPrice, int userId, String status) {
        this.title = title;
        this.description = description;
        this.startPrice = startPrice;
        this.userId = userId;
        this.requestId = requestId;
        this.status = status;
    }

    /**
     * Returns the unique identifier of the request.
     *
     * @return the request ID
     */
    public int getRequestId() {
        return requestId;
    }

    /**
     * Sets the unique identifier of the request.
     *
     * @param requestId the request ID to set
     */
    public void setRequestId(int requestId) {
        this.requestId = requestId;
    }

    /**
     * Returns the title of the request.
     *
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets the title of the request.
     *
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Returns the description of the request.
     *
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the description of the request.
     *
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Returns the starting price of the request.
     *
     * @return the starting price
     */
    public BigDecimal getStartPrice() {
        return startPrice;
    }

    /**
     * Sets the starting price of the request.
     *
     * @param startPrice the starting price to set
     */
    public void setStartPrice(BigDecimal startPrice) {
        this.startPrice = startPrice;
    }

    /**
     * Returns the user ID associated with the request.
     *
     * @return the user ID
     */
    public int getUserId() {
        return userId;
    }

    /**
     * Sets the user ID associated with the request.
     *
     * @param userId the user ID to set
     */
    public void setUserId(int userId) {
        this.userId = userId;
    }

    /**
     * Returns the status of the request.
     *
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * Sets the status of the request.
     *
     * @param status the status to set
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * Returns the price step for the request.
     *
     * @return the price step
     */
    public BigDecimal getPriceStep() {
        return priceStep;
    }

    /**
     * Sets the price step for the request.
     *
     * @param priceStep the price step to set
     */
    public void setPriceStep(BigDecimal priceStep) {
        this.priceStep = priceStep;
    }

    /**
     * Returns the end date and time of the request.
     *
     * @return the end date
     */
    public Timestamp getEndDate() {
        return endDate;
    }

    /**
     * Sets the end date and time for the request.
     *
     * @param endDate the end date to set
     */
    public void setEndDate(Timestamp endDate) {
        this.endDate = endDate;
    }

    /**
     * Returns the creation date and time of the request.
     *
     * @return the creation date
     */
    public Timestamp getCreatedDate() {
        return createdDate;
    }

    /**
     * Sets the creation date and time for the request.
     *
     * @param createdDate the creation date to set
     */
    public void setCreatedDate(Timestamp createdDate) {
        this.createdDate = createdDate;
    }
}

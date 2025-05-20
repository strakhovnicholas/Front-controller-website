package ru.rsreu.straxov.datalayer.data.daointerfaces;

import ru.rsreu.straxov.datalayer.data.entities.Lot;
import ru.rsreu.straxov.datalayer.data.entities.Request;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

/**
 * Interface for performing data access operations related to requests in the system.
 * Provides methods for retrieving active requests and updating the status of specific requests.
 */
public interface RequestDAO {

    /**
     * Returns a list of all active requests in the system.
     *
     * @return a list of active requests
     */
    public List<Request> getActiveRequests();

    /**
     * Sets the status of a specific request, along with its price step and end date.
     * This method updates the request with new information such as status, price step, and end date.
     *
     * @param requestId the ID of the request whose status is to be updated
     * @param priceStep the new price step for the request
     * @param endDate the new end date for the request
     * @param status the new status for the request
     */
    public void setRequestStatus(int requestId, BigDecimal priceStep, Timestamp endDate, String status);
}

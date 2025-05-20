package ru.rsreu.straxov.datalayer.data.daointerfaces;

import ru.rsreu.straxov.datalayer.data.entities.Purchase;

/**
 * Interface for performing data access operations related to purchases in the system.
 * Provides methods for finalizing bids and completing the purchase process.
 */
public interface PurchaseDAO {

    /**
     * Finalizes a bid and records the completion of a purchase for a specific user, lot, and bid.
     * This method is used to mark the end of a bid and confirm the purchase associated with it.
     *
     * @param userId the ID of the user completing the purchase
     * @param lotId the ID of the lot associated with the purchase
     * @param bidId the ID of the bid being finalized
     */
    public void finishBid(int userId, int lotId, int bidId);
}

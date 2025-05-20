package ru.rsreu.straxov.datalayer.data.daointerfaces;

import ru.rsreu.straxov.datalayer.data.entities.Bid;
import ru.rsreu.straxov.datalayer.data.entities.Lot;
import ru.rsreu.straxov.datalayer.data.entities.User;

import java.util.List;
import java.util.Set;

/**
 * Interface for performing data access operations related to bids in the system.
 * Provides methods for retrieving bids associated with users, lots, and inactive bids.
 */
public interface BidDAO {

    /**
     * Returns a set of bids placed by a specific user.
     *
     * @param user the user whose bids are to be retrieved
     * @return a set of bids placed by the given user
     */
    public Set<Bid> getAllBidsOfCertainUser(User user);

    /**
     * Returns a set of bids placed on a specific lot.
     *
     * @param lot the lot whose bids history is to be retrieved
     * @return a set of bids placed on the given lot
     */
    public Set<Bid> getBidsHistoryOfCertainLot(Lot lot);

    /**
     * Returns a list of all bids placed by a user identified by their user ID.
     *
     * @param userIdGiven the ID of the user whose bids are to be retrieved
     * @return a list of bids placed by the user with the specified user ID
     */
    public List<Bid> getAllBids(int userIdGiven);

    /**
     * Returns a list of all inactive bids.
     *
     * @return a list of inactive bids
     */
    public List<Bid> getInactiveBids();
}

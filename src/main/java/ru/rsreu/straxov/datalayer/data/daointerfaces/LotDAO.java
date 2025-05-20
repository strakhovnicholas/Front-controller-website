package ru.rsreu.straxov.datalayer.data.daointerfaces;

import ru.rsreu.straxov.datalayer.data.entities.Lot;
import ru.rsreu.straxov.datalayer.data.entities.User;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;
import java.util.Set;

/**
 * Interface for performing data access operations related to lots in the system.
 * Provides methods for retrieving, updating, creating, and removing lots, as well as managing lot prices and end times.
 */
public interface LotDAO {

    /**
     * Returns a list of lots owned by a specific user, identified by their user ID.
     *
     * @param userId the ID of the user whose lots are to be retrieved
     * @return a list of lots owned by the user
     */
    public List<Lot> getUserLots(int userId);

    /**
     * Returns a set of lots purchased by a specific user.
     *
     * @param user the user whose purchased lots are to be retrieved
     * @return a set of lots purchased by the user
     */
    public Set<Lot> getUserPurchasedLots(User user);

    /**
     * Creates a new lot and adds it to the database.
     *
     * @param lot the lot to be created
     */
    public void createNewLotInLots(Lot lot);

    /**
     * Removes a lot from the database by its lot ID.
     *
     * @param lotId the ID of the lot to be removed
     */
    public void removeLot(int lotId);

    /**
     * Updates the information of a specific lot, including its title, description, and end time.
     *
     * @param lotId       the ID of the lot to be updated
     * @param title       the new title for the lot
     * @param description the new description for the lot
     */
    public void updateLotInformation(int lotId, String title, String description);

    /**
     * Displays information for a specific lot based on its ID.
     *
     * @param lotId the ID of the lot whose information is to be displayed
     * @return the lot with the specified ID
     */
    public Lot showLotInformation(int lotId);

    /**
     * Increases the price of a specific lot based on a bid and updates the lot price.
     *
     * @param bid the amount by which the price is raised
     * @param lotId the ID of the lot whose price is to be increased
     * @param userId the ID of the user placing the bid
     */
    public void raiseLotPrice(BigDecimal bid, int lotId, int userId);

    /**
     * Sets the end time for all lots in the system.
     *
     * @param lotEndTime the new end time for all lots
     */
    public void setEndDataLots(Timestamp lotEndTime);

    /**
     * Sets the end time for a specific lot based on its lot ID.
     *
     * @param lotEndTime the new end time for the specific lot
     * @param lotId the ID of the lot whose end time is to be set
     */
    public void setEndDataCertainLot(Timestamp lotEndTime, int lotId);

    /**
     * Returns a list of active lots in the system.
     *
     * @return a list of active lots
     */
    public List<Lot> getActiveLots();

    /**
     * Returns a list of lots that are not owned by a specific user, identified by their user ID.
     *
     * @param userId the ID of the user whose non-owned lots are to be retrieved
     * @return a list of lots not owned by the user
     */
    public List<Lot> getNotUserLots(int userId);

    /**
     * Returns the price step for a specific lot, which is the minimum increment allowed for bidding.
     *
     * @param lotId the ID of the lot whose price step is to be retrieved
     * @return the price step for the specified lot
     */
    public BigDecimal getLotPriceStep(int lotId);

    /**
     * Returns a list of lot IDs for lots purchased by a specific user, identified by their user ID.
     *
     * @param userId the ID of the user whose purchased lots are to be retrieved
     * @return a list of lot IDs for the purchased lots
     */
    public List<Integer> getPurchasedLots(int userId);

    /**
     * Returns a list of lots based on a list of lot IDs.
     *
     * @param lotId a list of lot IDs for the lots to be retrieved
     * @return a list of lots corresponding to the specified lot IDs
     */
    public List<Lot> getPurchasedLotsByLotId(List<Integer> lotId);
}

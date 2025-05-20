package ru.rsreu.straxov.datalayer.data.boundary;

import ru.rsreu.straxov.datalayer.data.entities.Bid;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * Utility class to convert ResultSets into Bid objects or lists of Bid objects.
 */
public class BidResultSetProcessor {

    /**
     * Converts a ResultSet to a list of Bid objects.
     *
     * @param resultSet the ResultSet containing bid data
     * @return a list of Bid objects
     * @throws SQLException if there is an error processing the ResultSet
     */
    public static List<Bid> processBids(ResultSet resultSet) throws SQLException {
        List<Bid> bids = new ArrayList<>();

        while (resultSet.next()) {
            int bidId = resultSet.getInt("BID_ID");
            int lotId = resultSet.getInt("LOT_ID");
            int userId = resultSet.getInt("USER_ID");
            BigDecimal bidAmount = resultSet.getBigDecimal("BID_AMOUNT");
            Timestamp bidTime = resultSet.getTimestamp("BID_TIME");
            int auctionistId = resultSet.getInt("CURRENT_ID");
            BigDecimal bidPriceStep = resultSet.getBigDecimal("PRICE_STEP");

            bids.add(new Bid(bidId, lotId, userId, bidAmount, bidTime, auctionistId, bidPriceStep));
        }

        return bids;
    }
}

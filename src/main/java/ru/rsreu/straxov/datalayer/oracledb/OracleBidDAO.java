package ru.rsreu.straxov.datalayer.oracledb;

import com.prutzkow.resourcer.ProjectResourcer;
import com.prutzkow.resourcer.Resourcer;
import ru.rsreu.straxov.datalayer.data.boundary.BidResultSetProcessor;
import ru.rsreu.straxov.datalayer.data.daointerfaces.BidDAO;
import ru.rsreu.straxov.datalayer.data.entities.Bid;
import ru.rsreu.straxov.datalayer.data.entities.Lot;
import ru.rsreu.straxov.datalayer.data.entities.User;

import java.math.BigDecimal;
import java.sql.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

public class OracleBidDAO implements BidDAO {
    private Connection connection;
    protected static Resourcer resourcer = ProjectResourcer.getInstance();
    private List<Bid> bids = new ArrayList<Bid>();

    public OracleBidDAO(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Set<Bid> getAllBidsOfCertainUser(User user) {
        return Collections.emptySet();
    }

    @Override
    public Set<Bid> getBidsHistoryOfCertainLot(Lot lot) {
        return Collections.emptySet();
    }

    @Override
    public List<Bid> getAllBids(int userIdGiven) {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = connection.prepareStatement(
                    resourcer.getString("message.query.getAllBids"),
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);

            preparedStatement.setInt(1, userIdGiven);
            resultSet = preparedStatement.executeQuery();

            return BidResultSetProcessor.processBids(resultSet);

        } catch (SQLException e) {
            e.printStackTrace();
            return Collections.emptyList();
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (resultSet != null) {
                    resultSet.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public List<Bid> getInactiveBids() {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = connection.prepareStatement(
                    resourcer.getString("message.query.getExpiredBidsModer"),
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);

            resultSet = preparedStatement.executeQuery();

            return BidResultSetProcessor.processBids(resultSet);

        } catch (SQLException e) {
            e.printStackTrace();
            return Collections.emptyList();
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (resultSet != null) {
                    resultSet.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}

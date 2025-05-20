package ru.rsreu.straxov.datalayer.oracledb;

import com.prutzkow.resourcer.ProjectResourcer;
import com.prutzkow.resourcer.Resourcer;

import ru.rsreu.straxov.datalayer.data.boundary.LotResultSetProcessor;
import ru.rsreu.straxov.datalayer.data.daointerfaces.LotDAO;
import ru.rsreu.straxov.datalayer.data.entities.Lot;
import ru.rsreu.straxov.datalayer.data.entities.User;

import java.math.BigDecimal;
import java.sql.*;

import java.util.*;

public class OracleLotDAO implements LotDAO {

    private Connection connection;
    protected static Resourcer resourcer = ProjectResourcer.getInstance();

    public OracleLotDAO(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<Lot> getUserLots(int userId) {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Lot> userPostedLots = new ArrayList<Lot>();

        try {
            preparedStatement = connection.prepareStatement(
                    resourcer.getString("message.query.getUserPostedLots"));

            preparedStatement.setInt(1, userId);

            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Lot lot = LotResultSetProcessor.userLots(resultSet,userId);
                userPostedLots.add(lot);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                preparedStatement.close();
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return userPostedLots;
    }

    @Override
    public Set<Lot> getUserPurchasedLots(User user) {
        return Collections.emptySet();
    }

    @Override
    public void createNewLotInLots(Lot lot) {
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connection.prepareStatement(
                    resourcer.getString("message.query.insertNewLot")
            );

            preparedStatement.setString(1, lot.getTitle());
            preparedStatement.setString(2, lot.getDescription());
            preparedStatement.setBigDecimal(3, lot.getLotStartPrice());
            preparedStatement.setInt(4, lot.getUserId());

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (preparedStatement != null) preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }


    @Override
    public void removeLot(int lotId) {
        try {
            // Удаляем ставки, связанные с лотом
            executeUpdateLots(resourcer.getString("message.query.removeUserPostedLotBids"),lotId);

            // Удаляем сам лот
            executeUpdateLots(resourcer.getString("message.query.removeUserPostedLotLots"),lotId);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateLotInformation(int lotId, String title, String description) {
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(
                    resourcer.getString("message.query.updateLotInformation"), ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);

            preparedStatement.setString(1,title);
            preparedStatement.setString(2,description);
            preparedStatement.setInt(3,lotId);

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public Lot showLotInformation(int lotId) {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Lot newLot = null;
        try {
            preparedStatement = connection.prepareStatement(
                    resourcer.getString("message.query.showLotInfo"), ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);

            preparedStatement.setInt(1,lotId);

            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return LotResultSetProcessor.processForShow(resultSet);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                preparedStatement.close();
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    public void raiseLotPrice(BigDecimal bid,int lotId, int userId) {
        try {
            executeUpdateLots(resourcer.getString("message.query.bet"),bid,lotId);

            executeUpdateLots(resourcer.getString("message.query.updateLotCurrentPrice"),bid,lotId);

            executeUpdateLots(resourcer.getString("message.query.updateBidPurchaser"),userId, lotId);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void setEndDataLots(Timestamp lotEndTime) {
        try {
            // Удаляем ставки, связанные с лотом
            executeUpdateLots(resourcer.getString("message.query.setNewEndTimeAllBids"),lotEndTime);

            // Удаляем сам лот
            executeUpdateLots(resourcer.getString("message.query.setNewEndTimeAllLots"),lotEndTime);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void setEndDataCertainLot(Timestamp lotEndTime,int lotId) {
        try {

            executeUpdateLots(resourcer.getString("message.query.setNewEndTimeCertainLotLots"),lotEndTime,lotId);

            executeUpdateLots(resourcer.getString("message.query.setNewEndTimeCertainLotBids"),lotEndTime,lotId);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Lot> getActiveLots() {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Lot> activeLots = new ArrayList<Lot>();

        try {
            preparedStatement = connection.prepareStatement(
                    resourcer.getString("message.query.getActiveModerLots"), ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);

            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Lot lot = LotResultSetProcessor.activeLots(resultSet);
                activeLots.add(lot);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                preparedStatement.close();
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return activeLots;
    }

    @Override
    public List<Lot> getNotUserLots(int userId) {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Lot> otherPostedLots = new ArrayList<Lot>();

        try {
            preparedStatement = connection.prepareStatement(
                    resourcer.getString("message.query.getNotUserPostedLots"), ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);

            preparedStatement.setInt(1, userId);

            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Lot lot = LotResultSetProcessor.userLots(resultSet,userId);
                otherPostedLots.add(lot);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                preparedStatement.close();
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return otherPostedLots;
    }

    @Override
    public BigDecimal getLotPriceStep(int lotId) {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            preparedStatement = connection.prepareStatement(
                    resourcer.getString("message.query.getLotPriceStep"), ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);

            preparedStatement.setInt(1, lotId);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                return resultSet.getBigDecimal("PRICE_STEP");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                preparedStatement.close();
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return BigDecimal.ZERO;
    }

    @Override
    public List<Integer> getPurchasedLots(int userId) {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Integer> lotsId = new ArrayList<Integer>();

        try {
            preparedStatement = connection.prepareStatement(
                    resourcer.getString("message.query.getUserPurchasedLots"), ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);

            preparedStatement.setInt(1, userId);
            resultSet = preparedStatement.executeQuery();

            lotsId = LotResultSetProcessor.getLotsId(resultSet);

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                preparedStatement.close();
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return lotsId;
    }

    @Override
    public List<Lot> getPurchasedLotsByLotId(List<Integer> lotIds) {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Lot> purchasedLots = new ArrayList<>();

        String queryTemplate = resourcer.getString("message.query.getUserPurchasedLotsByLotId");
        String placeholders = String.join(", ", Collections.nCopies(lotIds.size(), "?"));
        String sqlQuery = queryTemplate.replace("{lotIds}", placeholders);

        try {
            preparedStatement = connection.prepareStatement(sqlQuery, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);

            for (int i = 0; i < lotIds.size(); i++) {
                preparedStatement.setInt(i + 1, lotIds.get(i));
            }

            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Lot lot = LotResultSetProcessor.purchasedLots(resultSet);
                purchasedLots.add(lot);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (preparedStatement != null) preparedStatement.close();
                if (resultSet != null) resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return purchasedLots;
    }


    private void executeUpdateLots(String query, Object... params) {
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(query, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);

            // Установка параметров в запрос
            for (int i = 0; i < params.length; i++) {
                preparedStatement.setObject(i + 1, params[i]);
            }

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }


}

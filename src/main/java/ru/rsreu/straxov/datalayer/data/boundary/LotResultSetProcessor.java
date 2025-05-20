package ru.rsreu.straxov.datalayer.data.boundary;

import ru.rsreu.straxov.datalayer.data.entities.Lot;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class LotResultSetProcessor {
    public static Lot userLots(ResultSet resultSet,int userId) throws SQLException {
        int lotId = resultSet.getInt("LOT_ID");
        String lotTitle = resultSet.getString("TITLE");
        String description = resultSet.getString("LOT_DESCRIPTION");
        BigDecimal startPrice = resultSet.getBigDecimal("START_PRICE");
        Timestamp endTime = resultSet.getTimestamp("END_TIME");
        BigDecimal endPrice = resultSet.getBigDecimal("CURRENT_PRICE");

        System.out.println(endPrice);
        return new Lot(lotId, lotTitle, description, startPrice, endTime, endPrice, userId);
    }

    public static Lot processForShow(ResultSet resultSet) throws SQLException {
        Timestamp endTime = resultSet.getTimestamp("END_TIME");
        String title = resultSet.getString("TITLE");
        String description = resultSet.getString("LOT_DESCRIPTION");
        BigDecimal currentPrice = resultSet.getBigDecimal("CURRENT_PRICE");

        return new Lot(endTime, title, description, currentPrice);
    }

    public static Lot activeLots(ResultSet resultSet) throws SQLException {
        int lotId = resultSet.getInt("LOT_ID");
        String lotTitle = resultSet.getString("TITLE");
        String description = resultSet.getString("LOT_DESCRIPTION");
        BigDecimal startPrice = resultSet.getBigDecimal("START_PRICE");
        Timestamp endTime = resultSet.getTimestamp("END_TIME");
        BigDecimal endPrice = resultSet.getBigDecimal("CURRENT_PRICE");
        int userId = resultSet.getInt("USER_ID");
        return new Lot(lotId, lotTitle, description, startPrice, endTime, endPrice, userId);
    }

    public static List<Integer> getLotsId(ResultSet resultSet) throws SQLException {
        List<Integer> lotsId = new ArrayList<>();

        while (resultSet.next()) {
            int lotId = resultSet.getInt("LOT_ID");
            lotsId.add(lotId);
        }
        return lotsId;
    }

    public static Lot purchasedLots(ResultSet resultSet) throws SQLException {
        return activeLots(resultSet);
    }

}
package ru.rsreu.straxov.datalayer.data.boundary;

import ru.rsreu.straxov.datalayer.data.entities.Request;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RequestResultSetProcessor {
    public static Request activeRequests(ResultSet resultSet) throws SQLException {
        int requestId = resultSet.getInt("REQUEST_ID");
        String lotTitle = resultSet.getString("TITLE");
        String description = resultSet.getString("LOT_DESCRIPTION");
        BigDecimal startPrice = resultSet.getBigDecimal("START_PRICE");
        int userId = resultSet.getInt("USER_ID");
        String status = resultSet.getString("REQUEST_STATUS");

        return new Request(requestId, lotTitle, description, startPrice, userId, status);
    }
}

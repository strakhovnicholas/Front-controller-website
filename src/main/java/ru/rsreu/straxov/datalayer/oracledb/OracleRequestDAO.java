package ru.rsreu.straxov.datalayer.oracledb;

import com.prutzkow.resourcer.ProjectResourcer;
import com.prutzkow.resourcer.Resourcer;
import ru.rsreu.straxov.datalayer.data.boundary.RequestResultSetProcessor;
import ru.rsreu.straxov.datalayer.data.daointerfaces.RequestDAO;
import ru.rsreu.straxov.datalayer.data.entities.Lot;
import ru.rsreu.straxov.datalayer.data.entities.Request;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class OracleRequestDAO implements RequestDAO {
    private Connection connection;
    protected static Resourcer resourcer = ProjectResourcer.getInstance();

    public OracleRequestDAO(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<Request> getActiveRequests() {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Request> activeRequests = new ArrayList<Request>();

        try {
            preparedStatement = connection.prepareStatement(
                    resourcer.getString("message.query.getActiveRequests"), ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);


            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                // Используем процессор для создания объекта Request из текущей строки
                Request request = RequestResultSetProcessor.activeRequests(resultSet);
                activeRequests.add(request);
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
        return activeRequests;
    }

    @Override
    public void setRequestStatus(int requestId, BigDecimal priceStep, Timestamp endDate, String status) {
        CallableStatement callableStatement = null;
        try {

            callableStatement = connection.prepareCall("{call ApproveLotRequest (?,?,?,?)}");
            // Установка параметров процедуры
            callableStatement.setInt(1, requestId);
            callableStatement.setBigDecimal(2, priceStep);
            callableStatement.setTimestamp(3, endDate);
            callableStatement.setString(4, status);

            // Выполнение процедуры
            callableStatement.execute();

        } catch (SQLException e) {
            e.printStackTrace(); // Обработка возможных ошибок SQL
        } finally {
            // Закрытие ресурсов после использования
            try { if (callableStatement != null) callableStatement.close(); } catch (SQLException se) { se.printStackTrace(); }
        }
    }
}

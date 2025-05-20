package ru.rsreu.straxov.datalayer.oracledb;

import com.prutzkow.resourcer.ProjectResourcer;
import com.prutzkow.resourcer.Resourcer;
import ru.rsreu.straxov.datalayer.data.daointerfaces.PurchaseDAO;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;


public class OraclePurchaseDAO implements PurchaseDAO {
    private Connection connection;
    protected static Resourcer resourcer = ProjectResourcer.getInstance();

    public OraclePurchaseDAO(Connection connection) {
        this.connection = connection;
    }

    public void finishBid(int userId, int lotId, int bidId) {
        CallableStatement callableStatement = null;
        try {
            // Подготовка вызова процедуры
            callableStatement = connection.prepareCall(resourcer.getString("message.query.callProcedureApproveLotRequest"));
            callableStatement.setInt(1, userId);
            callableStatement.setInt(2, lotId);
            callableStatement.setInt(3, bidId);

            // Выполнение процедуры
            callableStatement.execute();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

package ru.rsreu.straxov.datalayer.oracledb;

import com.prutzkow.resourcer.ProjectResourcer;
import com.prutzkow.resourcer.Resourcer;
import ru.rsreu.straxov.datalayer.data.daointerfaces.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Locale;


public class OracleDBDAOFactory extends DAOFactory {
    private static volatile OracleDBDAOFactory instance;
    protected static Resourcer resourcer = ProjectResourcer.getInstance();
    private static Connection connection;

    private OracleDBDAOFactory() {
    }

    public static OracleDBDAOFactory getInstance() throws ClassNotFoundException, SQLException {
        OracleDBDAOFactory factory = instance;
        if (instance == null) {
            synchronized (OracleDBDAOFactory.class) {
                instance = factory = new OracleDBDAOFactory();
                factory.connected();
            }
        }
        return factory;
    }

    private void connected() throws ClassNotFoundException, SQLException {
        Locale.setDefault(Locale.ENGLISH);
        Class.forName("oracle.jdbc.driver.OracleDriver");
        String url = "jdbc:oracle:thin:@localhost:1521:orcl";
        String user = "klaus";
        String password = "12345";
        connection = DriverManager.getConnection(url, user, password);
        System.out.println("Connected to oracle DB!");
    }

    public static String closeConnection() throws SQLException {
        connection.close();
        return resourcer.getString("message.connection.off");
    }


    @Override
    public BidDAO getBidDAO() {
        return new OracleBidDAO(connection);
    }

    @Override
    public LotDAO getLotDAO() {
        return new OracleLotDAO(connection);
    }

    @Override
    public UserDAO getUserDAO() {
        return new OracleUserDAO(connection);
    }

    @Override
    public PurchaseDAO getPurchaseDAO() {
        return new OraclePurchaseDAO(connection);
    }

    @Override
    public RequestDAO getRequestDAO() {
        return new OracleRequestDAO(connection);
    }
}

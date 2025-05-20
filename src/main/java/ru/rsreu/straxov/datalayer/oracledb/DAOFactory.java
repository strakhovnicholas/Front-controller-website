package ru.rsreu.straxov.datalayer.oracledb;

import ru.rsreu.straxov.datalayer.data.system.DBType;
import ru.rsreu.straxov.datalayer.data.daointerfaces.*;

public abstract class DAOFactory {
    public static DAOFactory getInstance(DBType dbType) {
        DAOFactory result = dbType.getDAOFactory();
        return result;
    }

    public abstract BidDAO getBidDAO();

    public abstract LotDAO getLotDAO();

    public abstract UserDAO getUserDAO();

    public abstract RequestDAO getRequestDAO();
    public abstract PurchaseDAO getPurchaseDAO();
}

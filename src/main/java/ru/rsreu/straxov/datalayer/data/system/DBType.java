package ru.rsreu.straxov.datalayer.data.system;

import ru.rsreu.straxov.datalayer.oracledb.DAOFactory;
import ru.rsreu.straxov.datalayer.oracledb.OracleDBDAOFactory;

import java.sql.SQLException;

public enum DBType {
    ORACLE {
        @Override
        public DAOFactory getDAOFactory() {
            DAOFactory oracleDBDAOFactory = null;
            try {
                oracleDBDAOFactory = OracleDBDAOFactory.getInstance();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return oracleDBDAOFactory;
        }
    };

    public static DBType getTypeByName(String dbType) {
        try {
            return DBType.valueOf(dbType.toUpperCase());
        } catch (Exception e) {
            throw new DBTypeException();
        }
    }

    public abstract DAOFactory getDAOFactory();

}


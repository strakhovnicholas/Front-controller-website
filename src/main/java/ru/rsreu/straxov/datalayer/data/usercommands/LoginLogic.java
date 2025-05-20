package ru.rsreu.straxov.datalayer.data.usercommands;

import ru.rsreu.straxov.datalayer.oracledb.DAOFactory;
import ru.rsreu.straxov.datalayer.data.system.DBType;
import ru.rsreu.straxov.datalayer.data.daointerfaces.UserDAO;
import ru.rsreu.straxov.datalayer.data.entities.User;

public class LoginLogic {

    public static User checkLogin(String enterLogin, String enterPass) {
        DAOFactory factory = DAOFactory.getInstance(DBType.ORACLE);
        UserDAO userDAO = factory.getUserDAO();
        return userDAO.findUser(enterLogin, enterPass);
    }

    public static Boolean isBlocked(int userId) {
        DAOFactory factory = DAOFactory.getInstance(DBType.ORACLE);
        UserDAO userDAO = factory.getUserDAO();
        return userDAO.isUserBlocked(userId);
    }
}
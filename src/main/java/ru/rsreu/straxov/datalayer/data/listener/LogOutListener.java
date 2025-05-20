package ru.rsreu.straxov.datalayer.data.listener;

import javax.servlet.http.HttpSessionListener;
import ru.rsreu.straxov.datalayer.data.system.DBType;
import ru.rsreu.straxov.datalayer.data.daointerfaces.UserDAO;
import ru.rsreu.straxov.datalayer.oracledb.DAOFactory;

import javax.servlet.http.HttpSessionEvent;

public class LogOutListener implements HttpSessionListener {
    private static final String LOGGED_IN_USER_ID = "loggedInUserId";
    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        Integer userId = (Integer) se.getSession().getAttribute(LOGGED_IN_USER_ID);

        if (userId != null) {
            updateAuthorizationStatus(userId);
        }
    }

    private void updateAuthorizationStatus(int userId) {
        DAOFactory factory = DAOFactory.getInstance(DBType.ORACLE);
        UserDAO userDAO = factory.getUserDAO();
        userDAO.updateAuthorizationStatus(userId, false);
    }
}


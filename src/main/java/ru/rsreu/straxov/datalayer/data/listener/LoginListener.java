package ru.rsreu.straxov.datalayer.data.listener;

import ru.rsreu.straxov.datalayer.data.system.DBType;
import ru.rsreu.straxov.datalayer.data.daointerfaces.UserDAO;
import ru.rsreu.straxov.datalayer.oracledb.DAOFactory;

import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;


public class LoginListener implements HttpSessionAttributeListener {
    private static final String LOGGED_IN_USER_ID = "loggedInUserId";
    @Override
    public void attributeAdded(HttpSessionBindingEvent event) {
        if (LOGGED_IN_USER_ID.equals(event.getName())) {
            Integer userId = (Integer) event.getValue();
            if (userId != null) {
                updateAuthorizationStatus(userId);
            }
        }
    }

    private void updateAuthorizationStatus(int userId) {
        DAOFactory factory = DAOFactory.getInstance(DBType.ORACLE);
        UserDAO userDAO = factory.getUserDAO();
        userDAO.updateAuthorizationStatus(userId, true);
    }
}

package ru.rsreu.straxov.datalayer.data.admincommands;

import ru.rsreu.straxov.datalayer.data.system.DBType;
import ru.rsreu.straxov.datalayer.data.daointerfaces.UserDAO;
import ru.rsreu.straxov.datalayer.data.entities.Page;
import ru.rsreu.straxov.datalayer.data.enums.TypeEnum;
import ru.rsreu.straxov.datalayer.data.system.ActionCommand;
import ru.rsreu.straxov.datalayer.oracledb.DAOFactory;

import javax.servlet.http.HttpServletRequest;

public class DeleteUserCommand implements ActionCommand {

    private static final String USER_ID = "userId";
    private static final String ERROR_MESSAGE = "errorMessage";


    @Override
    public Page execute(HttpServletRequest request) {
        String path = null;
        int currentAdminId = (int) request.getSession().getAttribute(USER_ID);
        int userIdToDelete  = Integer.parseInt(request.getParameter(USER_ID));

        if (currentAdminId == userIdToDelete) {
            request.getSession().setAttribute(ERROR_MESSAGE, resourcer.getString("message.attention"));
            path = resourcer.getString("message.showUsers");
            return new Page(path, TypeEnum.FORWARD);
        }

        DAOFactory factory = DAOFactory.getInstance(DBType.ORACLE);

        UserDAO userDAO = factory.getUserDAO();

        userDAO.deleteUser(userIdToDelete);

        path = resourcer.getString("message.showUsers");

        return new Page(path, TypeEnum.SENDREDIRECT);
    }
}

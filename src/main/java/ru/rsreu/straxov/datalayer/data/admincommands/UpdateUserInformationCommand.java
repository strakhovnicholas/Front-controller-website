package ru.rsreu.straxov.datalayer.data.admincommands;

import ru.rsreu.straxov.datalayer.data.system.DBType;
import ru.rsreu.straxov.datalayer.data.daointerfaces.UserDAO;
import ru.rsreu.straxov.datalayer.data.entities.Page;
import ru.rsreu.straxov.datalayer.data.enums.TypeEnum;
import ru.rsreu.straxov.datalayer.data.system.ActionCommand;
import ru.rsreu.straxov.datalayer.oracledb.DAOFactory;

import javax.servlet.http.HttpServletRequest;

public class UpdateUserInformationCommand implements ActionCommand {

    private static final String USER_ID = "userId";
    private static final String EDIT_USER_LOGIN = "editUserLogin";
    private static final String EDIT_USER_PASSWORD = "editUserPassword";
    private static final String EDIT_USER_ROLE_ID = "editUserRoleId";

    @Override
    public Page execute(HttpServletRequest request) {
        String path = null;

        int userId = Integer.parseInt(request.getParameter(USER_ID));
        // Извлечение из запроса логина и пароля
        String login = request.getParameter(EDIT_USER_LOGIN);
        String password = request.getParameter(EDIT_USER_PASSWORD);

        int roleId = Integer.parseInt(request.getParameter(EDIT_USER_ROLE_ID));

        //LotDAO lotDAO1 = (LotDAO) request.getServletContext().getAttribute("LotDAO");
        DAOFactory factory = DAOFactory.getInstance(DBType.ORACLE);

        UserDAO userDAO = factory.getUserDAO();

        userDAO.updateUser(userId,login,password,roleId);

        path = resourcer.getString("message.showUsers");
        return new Page(path, TypeEnum.SENDREDIRECT);
    }
}

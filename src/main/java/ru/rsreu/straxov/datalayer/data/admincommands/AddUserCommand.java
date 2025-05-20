package ru.rsreu.straxov.datalayer.data.admincommands;

import ru.rsreu.straxov.datalayer.data.system.DBType;
import ru.rsreu.straxov.datalayer.data.daointerfaces.UserDAO;
import ru.rsreu.straxov.datalayer.data.entities.Page;
import ru.rsreu.straxov.datalayer.data.entities.User;
import ru.rsreu.straxov.datalayer.data.enums.TypeEnum;
import ru.rsreu.straxov.datalayer.data.system.ActionCommand;
import ru.rsreu.straxov.datalayer.data.system.ConfigurationManager;
import ru.rsreu.straxov.datalayer.oracledb.DAOFactory;

import javax.servlet.http.HttpServletRequest;


public class AddUserCommand implements ActionCommand {

    private static final String LOGIN_NEW_USER = "loginNewUser";
    private static final String PASSWORD_NEW_USER = "passwordNewUser";
    private static final String ROLE_NEW_USER = "roleIdNewUser";
    private static final String NEW_USER = "newUser";

    @Override
    public Page execute(HttpServletRequest request) {
        String path = null;

        // Получение данных из формы
        String login = request.getParameter(LOGIN_NEW_USER);
        String password = request.getParameter(PASSWORD_NEW_USER);
        int roleId = Integer.parseInt(request.getParameter(ROLE_NEW_USER));

        User newUser = new User(login, password, roleId);

        DAOFactory factory = DAOFactory.getInstance(DBType.ORACLE);

        UserDAO userDAO = factory.getUserDAO();

        userDAO.createUser(newUser);

        request.getSession().setAttribute(NEW_USER, newUser);

        path = ConfigurationManager.getProperty("path.page.admin");
        return new Page(path, TypeEnum.SENDREDIRECT);
    }
}

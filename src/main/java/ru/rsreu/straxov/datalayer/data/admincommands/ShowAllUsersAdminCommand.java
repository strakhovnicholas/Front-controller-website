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
import java.util.List;

public class ShowAllUsersAdminCommand implements ActionCommand {
    private static final String ALL_USERS = "allUsers";
    @Override
    public Page execute(HttpServletRequest request) {
        String path = null;
        DAOFactory factory = DAOFactory.getInstance(DBType.ORACLE);

        UserDAO userDAO = factory.getUserDAO();

        List<User> allUsers= userDAO.getAllUsersAndRolesAdmin();
        request.setAttribute(ALL_USERS, allUsers);

        path = ConfigurationManager.getProperty("path.page.admin.users");
        return new Page(path, TypeEnum.FORWARD);
    }
}

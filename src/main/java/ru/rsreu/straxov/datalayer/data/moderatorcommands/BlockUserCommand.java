package ru.rsreu.straxov.datalayer.data.moderatorcommands;

import ru.rsreu.straxov.datalayer.data.system.DBType;
import ru.rsreu.straxov.datalayer.data.daointerfaces.UserDAO;
import ru.rsreu.straxov.datalayer.data.entities.Page;
import ru.rsreu.straxov.datalayer.data.enums.TypeEnum;
import ru.rsreu.straxov.datalayer.data.system.ActionCommand;
import ru.rsreu.straxov.datalayer.oracledb.DAOFactory;

import javax.servlet.http.HttpServletRequest;

public class BlockUserCommand implements ActionCommand {
    private static final String USER_ID = "userId";
    private static final String CURRENT_STATUS = "currentStatus";

    @Override
    public Page execute(HttpServletRequest request) {
        String path = null;
        DAOFactory factory = DAOFactory.getInstance(DBType.ORACLE);

        int userIdToBlock  = Integer.parseInt(request.getParameter(USER_ID));
        boolean status = Boolean.parseBoolean(request.getParameter(CURRENT_STATUS));

        UserDAO userDAO = factory.getUserDAO();

        userDAO.updateBlockStatus(userIdToBlock,status);

        path = resourcer.getString("message.showUsersModer");
        return new Page(path, TypeEnum.SENDREDIRECT);
    }
}

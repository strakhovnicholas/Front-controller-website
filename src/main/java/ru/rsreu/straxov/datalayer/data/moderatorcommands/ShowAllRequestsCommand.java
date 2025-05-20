package ru.rsreu.straxov.datalayer.data.moderatorcommands;

import ru.rsreu.straxov.datalayer.data.system.DBType;
import ru.rsreu.straxov.datalayer.data.daointerfaces.RequestDAO;
import ru.rsreu.straxov.datalayer.data.entities.Page;
import ru.rsreu.straxov.datalayer.data.entities.Request;
import ru.rsreu.straxov.datalayer.data.enums.TypeEnum;
import ru.rsreu.straxov.datalayer.data.system.ActionCommand;
import ru.rsreu.straxov.datalayer.data.system.ConfigurationManager;
import ru.rsreu.straxov.datalayer.oracledb.DAOFactory;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class ShowAllRequestsCommand implements ActionCommand {
    private static final String ACTIVE_REQUESTS = "activeRequests";
    @Override
    public Page execute(HttpServletRequest request) {
        String path = null;
        DAOFactory factory = DAOFactory.getInstance(DBType.ORACLE);

        RequestDAO requestDAO = factory.getRequestDAO();
        List <Request> requests = requestDAO.getActiveRequests();
        request.setAttribute(ACTIVE_REQUESTS, requests);

        path = ConfigurationManager.getProperty("path.page.moderator.requests");
        return new Page(path, TypeEnum.FORWARD);
    }
}

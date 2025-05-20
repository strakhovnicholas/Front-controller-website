package ru.rsreu.straxov.datalayer.data.usercommands;

import ru.rsreu.straxov.datalayer.oracledb.DAOFactory;
import ru.rsreu.straxov.datalayer.data.system.DBType;
import ru.rsreu.straxov.datalayer.data.system.ConfigurationManager;
import ru.rsreu.straxov.datalayer.data.daointerfaces.LotDAO;
import ru.rsreu.straxov.datalayer.data.entities.Lot;
import ru.rsreu.straxov.datalayer.data.entities.Page;
import ru.rsreu.straxov.datalayer.data.enums.TypeEnum;
import ru.rsreu.straxov.datalayer.data.system.ActionCommand;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class ShowUserPostedLotsCommand implements ActionCommand {
    private static final String USER_ID = "userId";
    private static final String USER_LOTS = "userLots";
    @Override
    public Page execute(HttpServletRequest request) {
        String path = null;
        DAOFactory factory = DAOFactory.getInstance(DBType.ORACLE);

        LotDAO lotDAO = factory.getLotDAO();

        List<Lot> userLots = lotDAO.getUserLots((int) request.getSession().getAttribute(USER_ID));

        for (Lot lot : userLots) {
            System.out.println(lot.getLotCurrentPrice());
        }
        request.setAttribute(USER_LOTS, userLots);

        path = ConfigurationManager.getProperty("path.page.user.posted");
        return new Page(path, TypeEnum.FORWARD);
    }
}

package ru.rsreu.straxov.datalayer.data.usercommands;

import ru.rsreu.straxov.datalayer.data.system.DBType;
import ru.rsreu.straxov.datalayer.data.daointerfaces.LotDAO;
import ru.rsreu.straxov.datalayer.data.entities.Lot;
import ru.rsreu.straxov.datalayer.data.entities.Page;
import ru.rsreu.straxov.datalayer.data.enums.TypeEnum;
import ru.rsreu.straxov.datalayer.data.system.ActionCommand;
import ru.rsreu.straxov.datalayer.data.system.ConfigurationManager;
import ru.rsreu.straxov.datalayer.oracledb.DAOFactory;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class ShowPurchasedLotsCommand implements ActionCommand {
    private static final String USER_ID = "userId";
    private static final String USER_PURCHASED_LOTS = "purchasedLots";
    @Override
    public Page execute(HttpServletRequest request) {
        String path = null;
        DAOFactory factory = DAOFactory.getInstance(DBType.ORACLE);

        LotDAO lotDAO = factory.getLotDAO();

        List<Integer> lotsIds = lotDAO.getPurchasedLots((int) request.getSession().getAttribute(USER_ID));
        List<Lot> purchasedLots = lotDAO.getPurchasedLotsByLotId(lotsIds);
        request.setAttribute(USER_PURCHASED_LOTS, purchasedLots);

        path = ConfigurationManager.getProperty("path.page.purchasedLotsUser");
        return new Page(path, TypeEnum.FORWARD);
    }
}

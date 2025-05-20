package ru.rsreu.straxov.datalayer.data.usercommands;

import ru.rsreu.straxov.datalayer.data.system.DBType;
import ru.rsreu.straxov.datalayer.data.daointerfaces.BidDAO;
import ru.rsreu.straxov.datalayer.data.entities.Bid;
import ru.rsreu.straxov.datalayer.data.entities.Page;
import ru.rsreu.straxov.datalayer.data.enums.TypeEnum;
import ru.rsreu.straxov.datalayer.data.system.ActionCommand;
import ru.rsreu.straxov.datalayer.data.system.ConfigurationManager;
import ru.rsreu.straxov.datalayer.oracledb.DAOFactory;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class ShowLotsForPurchaseCommand implements ActionCommand {
    private static final String USER_ID = "userId";
    private static final String BIDS = "bids";
    @Override
    public Page execute(HttpServletRequest request) {
        String path = null;
        DAOFactory factory = DAOFactory.getInstance(DBType.ORACLE);

        BidDAO bidDAO = factory.getBidDAO();

        List <Bid> bids = bidDAO.getAllBids((int) request.getSession().getAttribute(USER_ID));
        request.setAttribute(BIDS, bids);

        path = ConfigurationManager.getProperty("path.page.user.purchase");
        return new Page(path, TypeEnum.FORWARD);
    }
}

package ru.rsreu.straxov.datalayer.data.moderatorcommands;

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

public class ShowExpiredBidsCommand implements ActionCommand {
    private static final String INACTIVE_BIDS = "inactiveBids";
    @Override
    public Page execute(HttpServletRequest request) {
        String path = null;
        DAOFactory factory = DAOFactory.getInstance(DBType.ORACLE);

        BidDAO bidDAO = factory.getBidDAO();

        List<Bid> inactiveBids = bidDAO.getInactiveBids();
        request.setAttribute(INACTIVE_BIDS, inactiveBids);

        path = ConfigurationManager.getProperty("path.page.expiredBids");
        return new Page(path, TypeEnum.FORWARD);
    }
}

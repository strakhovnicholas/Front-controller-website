package ru.rsreu.straxov.datalayer.data.moderatorcommands;

import ru.rsreu.straxov.datalayer.data.system.DBType;
import ru.rsreu.straxov.datalayer.data.daointerfaces.PurchaseDAO;
import ru.rsreu.straxov.datalayer.data.entities.Page;
import ru.rsreu.straxov.datalayer.data.enums.TypeEnum;
import ru.rsreu.straxov.datalayer.data.system.ActionCommand;
import ru.rsreu.straxov.datalayer.oracledb.DAOFactory;

import javax.servlet.http.HttpServletRequest;

public class CloseBidCommand implements ActionCommand {
    @Override
    public Page execute(HttpServletRequest request) {
        String path = null;
        // Получение параметров из запроса
        int lotId = Integer.parseInt(request.getParameter("lotId"));
        int bidId = Integer.parseInt(request.getParameter("bidId"));
        int auctionistId = Integer.parseInt(request.getParameter("auctionistId"));

        DAOFactory factory = DAOFactory.getInstance(DBType.ORACLE);

        PurchaseDAO purchaseDAO = factory.getPurchaseDAO();
        // Записываем данные в таблицу UserPurchases
        purchaseDAO.finishBid(auctionistId,lotId, bidId);

        path = resourcer.getString("message.showExpiredLots");
        return new Page(path, TypeEnum.SENDREDIRECT);
    }
}

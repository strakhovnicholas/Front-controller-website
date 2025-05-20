package ru.rsreu.straxov.datalayer.data.usercommands;

import ru.rsreu.straxov.datalayer.data.system.DBType;
import ru.rsreu.straxov.datalayer.data.daointerfaces.LotDAO;
import ru.rsreu.straxov.datalayer.data.entities.Lot;
import ru.rsreu.straxov.datalayer.data.entities.Page;
import ru.rsreu.straxov.datalayer.data.enums.TypeEnum;
import ru.rsreu.straxov.datalayer.data.system.ActionCommand;
import ru.rsreu.straxov.datalayer.oracledb.DAOFactory;

import javax.servlet.http.HttpServletRequest;

public class ShowLotInformationCommand implements ActionCommand {
    private static final String LOT_ID = "lotId";
    private static final String LOT_INFO = "lotInfo";
    private static final String SHOW_LOT_MODAL = "showLotModal";
    @Override
    public Page execute(HttpServletRequest request) {
        String path = null;

        int lotId = Integer.parseInt(request.getParameter(LOT_ID));

        DAOFactory factory = DAOFactory.getInstance(DBType.ORACLE);

        LotDAO lotDAO = factory.getLotDAO();

        Lot lot = lotDAO.showLotInformation(lotId);

        request.getSession().setAttribute(LOT_INFO, lot);
        request.getSession().setAttribute(SHOW_LOT_MODAL, true);

        path = resourcer.getString("message.showLotsForPurchase");
        return new Page(path, TypeEnum.FORWARD);
    }
}

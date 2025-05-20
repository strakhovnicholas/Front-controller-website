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
import java.math.BigDecimal;

public class AddLotCommand implements ActionCommand {

    private static final String PARAM_LOT_TITLE = "title";
    private static final String PARAM_LOT_DESCRIPTION = "description";
    private static final String PARAM_LOT_START_PRICE = "lotStartPrice";
    private static final String USER_ID = "userId";
    private static final String NEW_LOT = "newLot";

    @Override
    public Page execute(HttpServletRequest request) {
        String path = null;

        // Получение данных из формы
        String title = request.getParameter(PARAM_LOT_TITLE);
        String description = request.getParameter(PARAM_LOT_DESCRIPTION);
        BigDecimal lotStartPrice = new BigDecimal(request.getParameter(PARAM_LOT_START_PRICE));

        Lot newLot = new Lot(title, description, lotStartPrice);

        newLot.setUserId((int) request.getSession().getAttribute(USER_ID));

        DAOFactory factory = DAOFactory.getInstance(DBType.ORACLE);

        LotDAO lotDAO = factory.getLotDAO();

        lotDAO.createNewLotInLots(newLot);

        request.getSession().setAttribute(NEW_LOT, newLot);

        path = ConfigurationManager.getProperty("path.page.user");
        return new Page(path, TypeEnum.SENDREDIRECT);
    }
}

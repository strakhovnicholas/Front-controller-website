package ru.rsreu.straxov.datalayer.data.usercommands;

import ru.rsreu.straxov.datalayer.oracledb.DAOFactory;
import ru.rsreu.straxov.datalayer.data.system.DBType;
import ru.rsreu.straxov.datalayer.data.daointerfaces.LotDAO;
import ru.rsreu.straxov.datalayer.data.entities.Lot;
import ru.rsreu.straxov.datalayer.data.entities.Page;
import ru.rsreu.straxov.datalayer.data.enums.TypeEnum;
import ru.rsreu.straxov.datalayer.data.system.ActionCommand;

import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.time.LocalDateTime;

public class UpdatePostedLotCommand implements ActionCommand {
    private static final String PARAM_LOT_TITLE = "editTitle";
    private static final String PARAM_LOT_DESCRIPTION = "editDescription";
    private static final String PARAM_LOT_END_TIME = "editLotEndTime";
    private static final String USER_ID = "userId";
    private static final String LOT_ID = "lotId";
    private static final String EDITED_LOT = "editedLot";

    @Override
    public Page execute(HttpServletRequest request) {
        String path = null;

        // Извлечение из запроса логина и пароля
        String editTitle = request.getParameter(PARAM_LOT_TITLE);
        System.out.println("editTitle" + " " + editTitle);
        String editDescription = request.getParameter(PARAM_LOT_DESCRIPTION);
        System.out.println("editDescription" + " " + editDescription);

        String editLotEndTime = request.getParameter(PARAM_LOT_END_TIME);
        Timestamp lotEndTime = Timestamp.valueOf(LocalDateTime.now());

        Lot newLot = new Lot(editTitle, editDescription, lotEndTime);

        newLot.setUserId((int) request.getSession().getAttribute(USER_ID));

        //LotDAO lotDAO1 = (LotDAO) request.getServletContext().getAttribute("LotDAO");
        DAOFactory factory = DAOFactory.getInstance(DBType.ORACLE);

        LotDAO lotDAO = factory.getLotDAO();

        lotDAO.updateLotInformation(Integer.parseInt(request.getParameter(LOT_ID)), editTitle, editDescription);

        request.setAttribute(EDITED_LOT, newLot);

        path = resourcer.getString("message.showUserPostedLots");
        return new Page(path, TypeEnum.SENDREDIRECT);
    }
}

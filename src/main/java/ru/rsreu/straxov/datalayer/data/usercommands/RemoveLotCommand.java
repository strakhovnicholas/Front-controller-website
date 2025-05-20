package ru.rsreu.straxov.datalayer.data.usercommands;

import ru.rsreu.straxov.datalayer.oracledb.DAOFactory;
import ru.rsreu.straxov.datalayer.data.system.DBType;
import ru.rsreu.straxov.datalayer.data.daointerfaces.LotDAO;
import ru.rsreu.straxov.datalayer.data.entities.Page;
import ru.rsreu.straxov.datalayer.data.enums.TypeEnum;
import ru.rsreu.straxov.datalayer.data.system.ActionCommand;

import javax.servlet.http.HttpServletRequest;

public class RemoveLotCommand implements ActionCommand {
    private static final String LOT_ID = "lotId";
    @Override
    public Page execute(HttpServletRequest request) {
        String path = null;

        // Извлечение из запроса логина и пароля
        int lotId = Integer.parseInt(request.getParameter(LOT_ID));

        DAOFactory factory = DAOFactory.getInstance(DBType.ORACLE);

        LotDAO lotDAO = factory.getLotDAO();

        lotDAO.removeLot(lotId);

        path = resourcer.getString("message.showUserPostedLots");

        return new Page(path, TypeEnum.SENDREDIRECT);
    }
}

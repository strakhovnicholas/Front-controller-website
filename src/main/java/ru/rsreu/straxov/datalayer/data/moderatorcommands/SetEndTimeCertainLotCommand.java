package ru.rsreu.straxov.datalayer.data.moderatorcommands;

import ru.rsreu.straxov.datalayer.data.system.DBType;
import ru.rsreu.straxov.datalayer.data.daointerfaces.LotDAO;
import ru.rsreu.straxov.datalayer.data.entities.Page;
import ru.rsreu.straxov.datalayer.data.enums.TypeEnum;
import ru.rsreu.straxov.datalayer.data.system.ActionCommand;
import ru.rsreu.straxov.datalayer.oracledb.DAOFactory;

import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class SetEndTimeCertainLotCommand implements ActionCommand {
    private static final String LOT_ID = "lotId";
    private static final String END_DATE_LOT = "endDateLot";
    @Override
    public Page execute(HttpServletRequest request) {
        String path;

        int lotId = Integer.parseInt(request.getParameter(LOT_ID));
        String lotEndTimeParam = request.getParameter(END_DATE_LOT);

        LocalDateTime localDateTime = LocalDate.parse(lotEndTimeParam).atStartOfDay();

        Timestamp lotEndTime = Timestamp.valueOf(localDateTime);

        DAOFactory factory = DAOFactory.getInstance(DBType.ORACLE);
        LotDAO lotDAO = factory.getLotDAO();

        lotDAO.setEndDataCertainLot(lotEndTime, lotId);

        path = resourcer.getString("message.showLotsModer");
        return new Page(path, TypeEnum.SENDREDIRECT);
    }
}

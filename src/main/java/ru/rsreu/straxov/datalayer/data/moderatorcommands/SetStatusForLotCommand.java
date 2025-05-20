package ru.rsreu.straxov.datalayer.data.moderatorcommands;

import ru.rsreu.straxov.datalayer.data.system.DBType;
import ru.rsreu.straxov.datalayer.data.daointerfaces.RequestDAO;
import ru.rsreu.straxov.datalayer.data.entities.Page;
import ru.rsreu.straxov.datalayer.data.enums.TypeEnum;
import ru.rsreu.straxov.datalayer.data.system.ActionCommand;
import ru.rsreu.straxov.datalayer.oracledb.DAOFactory;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDate;

public class SetStatusForLotCommand implements ActionCommand {
    private static final String STATUS = "status";
    private static final String REQUEST_ID = "requestId";
    private static final String PRICE_STEP = "priceStep";
    private static final String END_DATE = "endDate";
    @Override
    public Page execute(HttpServletRequest request) {
        String path = null;
        DAOFactory factory = DAOFactory.getInstance(DBType.ORACLE);

        String status = request.getParameter(STATUS);
        int requestId = Integer.parseInt(request.getParameter(REQUEST_ID));

        RequestDAO requestDAO = factory.getRequestDAO();
        if ("Rejected".equalsIgnoreCase(status)) {
            requestDAO.setRequestStatus(requestId, null, null, status);
        } else {
            BigDecimal priceStep = new BigDecimal(request.getParameter(PRICE_STEP));
            String endDate = request.getParameter(END_DATE);
            LocalDate localDate = LocalDate.parse(endDate);
            Timestamp lotEndDate = Timestamp.valueOf(localDate.atStartOfDay());

            requestDAO.setRequestStatus(requestId, priceStep, lotEndDate, status);
        }

        path = resourcer.getString("message.showUserRequests");
        return new Page(path, TypeEnum.SENDREDIRECT);
    }
}

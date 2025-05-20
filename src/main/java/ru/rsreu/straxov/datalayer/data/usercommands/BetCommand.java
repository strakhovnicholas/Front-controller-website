    package ru.rsreu.straxov.datalayer.data.usercommands;

    import ru.rsreu.straxov.datalayer.data.system.DBType;
    import ru.rsreu.straxov.datalayer.data.daointerfaces.LotDAO;
    import ru.rsreu.straxov.datalayer.data.entities.Page;
    import ru.rsreu.straxov.datalayer.data.enums.TypeEnum;
    import ru.rsreu.straxov.datalayer.data.system.ActionCommand;
    import ru.rsreu.straxov.datalayer.oracledb.DAOFactory;

    import javax.servlet.http.HttpServletRequest;
    import java.math.BigDecimal;

    public class BetCommand implements ActionCommand {
        private static final String BET_AMOUNT = "betAmount";
        private static final String LOT_ID = "lotId";
        private static final String USER_ID = "userId";

        @Override
        public Page execute(HttpServletRequest request) {
            String path = null;

            BigDecimal bet = new BigDecimal(request.getParameter(BET_AMOUNT));
            int lotId = Integer.parseInt(request.getParameter(LOT_ID));
            int userId = (int) request.getSession().getAttribute(USER_ID);


            DAOFactory factory = DAOFactory.getInstance(DBType.ORACLE);

            LotDAO lotDAO = factory.getLotDAO();

            lotDAO.raiseLotPrice(bet,lotId,userId);

            path = resourcer.getString("message.showLotsForPurchase");
            return new Page(path, TypeEnum.SENDREDIRECT);
        }
    }

package ru.rsreu.straxov.datalayer.data.listener;

import ru.rsreu.straxov.datalayer.data.system.DBType;
import ru.rsreu.straxov.datalayer.data.daointerfaces.BidDAO;
import ru.rsreu.straxov.datalayer.data.daointerfaces.LotDAO;
import ru.rsreu.straxov.datalayer.data.daointerfaces.RequestDAO;
import ru.rsreu.straxov.datalayer.data.daointerfaces.UserDAO;
import ru.rsreu.straxov.datalayer.oracledb.DAOFactory;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class ContextListener implements ServletContextListener {
    public void contextInitialized(ServletContextEvent sce) {
        DAOFactory daoFactory = DAOFactory.getInstance(DBType.ORACLE);
        LotDAO lotDAO = daoFactory.getLotDAO();
        BidDAO bidDAO = daoFactory.getBidDAO();
        UserDAO userDAO = daoFactory.getUserDAO();
        RequestDAO requestDAO = daoFactory.getRequestDAO();
        sce.getServletContext().setAttribute("daoFactory", daoFactory.getUserDAO());
        sce.getServletContext().setAttribute("lotDAO", lotDAO);
        sce.getServletContext().setAttribute("bidDAO", bidDAO);
        sce.getServletContext().setAttribute("userDAO", userDAO);
        sce.getServletContext().setAttribute("requestDAO", requestDAO);
    }
}

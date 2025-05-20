package ru.rsreu.straxov.datalayer.data.filter;

import ru.rsreu.straxov.datalayer.data.daointerfaces.UserDAO;
import ru.rsreu.straxov.datalayer.data.enums.CommandEnum;
import ru.rsreu.straxov.datalayer.data.system.ActionCommand;
import ru.rsreu.straxov.datalayer.data.system.ActionFactory;
import ru.rsreu.straxov.datalayer.data.system.DBType;
import ru.rsreu.straxov.datalayer.oracledb.DAOFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.*;

public class AccessFilter implements Filter {
    private final Map<Integer, List<CommandEnum>> roleCommandsMap = new HashMap<>();
    private static final String ROLE = "role";
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

        roleCommandsMap.put(1, new ArrayList<>()); // Администратор
        roleCommandsMap.put(2, new ArrayList<>()); // Модератор
        roleCommandsMap.put(3, new ArrayList<>()); // Пользователь


        populateRoleCommands();
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;
        HttpSession session = httpRequest.getSession();

        ActionFactory client = new ActionFactory();
        ActionCommand command = client.defineCommand(httpRequest);


        Integer role = (Integer) session.getAttribute(ROLE);
        int role2 = 0;

        DAOFactory factory = DAOFactory.getInstance(DBType.ORACLE);

        UserDAO userDAO = factory.getUserDAO();
        try
        {
             role2 = userDAO.getUserRole((int)session.getAttribute("userId"));
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        if (role2 == 0) {
            filterChain.doFilter(httpRequest, httpResponse);
            return;
        }

        // Проверка, доступна ли команда и страница для текущей роли
        if (!isAccessAllowed(role2, command)) {
            String contextPath = httpRequest.getContextPath();
            System.out.println("Сработал фильтр AccessFilter");
            httpResponse.sendRedirect(contextPath + "/");
            return;
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }

    private boolean isAccessAllowed(Integer role, ActionCommand command) {
        // Проверка команд
        List<CommandEnum> allowedCommands = roleCommandsMap.get(role);

        return allowedCommands != null && allowedCommands.stream()
                .anyMatch(enumCommand -> enumCommand.getCurrentCommand().getClass().equals(command.getClass()));
    }

    private void populateRoleCommands() {
        List<CommandEnum> adminCommands = Arrays.asList(
                CommandEnum.ADDUSER, CommandEnum.SHOWUSERS,
                CommandEnum.EDITUSER, CommandEnum.DELETEUSER
        );

        List<CommandEnum> moderatorCommands = Arrays.asList(
                CommandEnum.SHOWUSERSMODER, CommandEnum.BLOCKUSER,
                CommandEnum.SHOWLOTSMODER, CommandEnum.REMOVELOTMODER,
                CommandEnum.SETENDDATEFORALLLOTS, CommandEnum.SETENDDATEFORCERTAINLOT,
                CommandEnum.SHOWUSERREQUEST, CommandEnum.SETSTATUSFORLOT,CommandEnum.SHOWEXPIREDLOTS,CommandEnum.CLOSEBID
        );

        List<CommandEnum> userCommands = Arrays.asList(
                CommandEnum.ADDLOT, CommandEnum.SHOWUSERPOSTEDLOTS,
                CommandEnum.REMOVELOT, CommandEnum.EDITLOT,
                CommandEnum.SHOWLOTSFORPURCHASE, CommandEnum.SHOWLOTINFO,
                CommandEnum.BET,CommandEnum.SHOWPURCHASEDLOTSUSER
        );

        roleCommandsMap.get(1).addAll(adminCommands);
        roleCommandsMap.get(2).addAll(moderatorCommands);
        roleCommandsMap.get(3).addAll(userCommands);

        List<CommandEnum> commonCommands = Arrays.asList(CommandEnum.LOGIN, CommandEnum.LOGOUT);
        roleCommandsMap.forEach((role, commands) -> commands.addAll(commonCommands));
    }
}

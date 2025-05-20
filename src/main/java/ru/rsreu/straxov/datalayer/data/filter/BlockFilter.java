package ru.rsreu.straxov.datalayer.data.filter;

import ru.rsreu.straxov.datalayer.data.system.ActionCommand;
import ru.rsreu.straxov.datalayer.data.system.ActionFactory;
import ru.rsreu.straxov.datalayer.data.system.ConfigurationManager;
import ru.rsreu.straxov.datalayer.data.usercommands.LoginLogic;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class BlockFilter implements Filter {
    private static final String USER_ID = "userId";
    private static final String ROLE = "role";
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        HttpSession session = httpRequest.getSession(false);
        HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;

        Integer userId = (Integer) session.getAttribute(USER_ID);

        Integer role = (Integer) session.getAttribute(ROLE);

        ActionFactory client = new ActionFactory();
        ActionCommand command = client.defineCommand(httpRequest);

        if (role == null) {
            filterChain.doFilter(httpRequest, httpResponse);
            return;
        }

        if (LoginLogic.isBlocked(userId))
        {
            session.invalidate();
            System.out.println("Сработал фильтр BlockFilter");
            RequestDispatcher dispatcher = httpRequest.getRequestDispatcher(ConfigurationManager.getProperty("path.page.blocked"));
            dispatcher.forward(servletRequest, servletResponse);
            return;
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
    }
}

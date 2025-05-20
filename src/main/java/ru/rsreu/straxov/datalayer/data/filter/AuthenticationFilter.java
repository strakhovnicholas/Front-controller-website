package ru.rsreu.straxov.datalayer.data.filter;

import ru.rsreu.straxov.datalayer.data.enums.CommandEnum;
import ru.rsreu.straxov.datalayer.data.system.ActionCommand;
import ru.rsreu.straxov.datalayer.data.system.ActionFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.*;

public class AuthenticationFilter implements Filter {

    private final Map<Integer, List<CommandEnum>> roleCommandsMap = new HashMap<>();

    private static final String ROLE = "role";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }


    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpSession session = httpRequest.getSession(false);
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        ActionFactory client = new ActionFactory();
        ActionCommand command = client.defineCommand(httpRequest);

        if (command == CommandEnum.LOGIN.getCurrentCommand()) {
            chain.doFilter(request, response);
            return;
        }

        if (session == null || session.getAttribute(ROLE) == null) {
            String contextPath = httpRequest.getContextPath();
            System.out.println("Сработал фильтр AuthenticationFilter");
            httpResponse.sendRedirect(contextPath + "/");
            return;
        }
        chain.doFilter(request, response);

    }

    @Override
    public void destroy() {
    }

}

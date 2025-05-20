package ru.rsreu.straxov.controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ru.rsreu.straxov.datalayer.data.entities.Page;
import ru.rsreu.straxov.datalayer.data.system.ActionCommand;
import ru.rsreu.straxov.datalayer.data.system.ActionFactory;
import ru.rsreu.straxov.datalayer.data.system.ConfigurationManager;
import ru.rsreu.straxov.datalayer.data.system.MessageManager;

import java.io.IOException;

/**
 * FrontController class acts as the main servlet for processing user requests and
 * managing the application flow based on commands. It delegates requests to the
 * appropriate command handlers and determines the navigation logic.
 *
 * <p>The controller uses the `ActionFactory` to define the command to be executed,
 * executes it, and determines whether to forward or redirect the response based
 * on the result.
 *
 * <p>This class supports both `GET` and `POST` HTTP methods.
 *
 * <p>Key responsibilities include:
 * <ul>
 *     <li>Receiving and processing incoming HTTP requests</li>
 *     <li>Identifying the appropriate action command</li>
 *     <li>Managing navigation (forwarding or redirecting)</li>
 * </ul>
 */
public class FrontController extends HttpServlet {

    /**
     * Handles HTTP GET requests by delegating to the {@link #processRequest} method.
     *
     * @param request  the {@link HttpServletRequest} object that contains the request
     *                 the client made of the servlet
     * @param response the {@link HttpServletResponse} object that contains the
     *                 response the servlet returns to the client
     * @throws ServletException if an input or output error is detected when the servlet
     *                          handles the GET request
     * @throws IOException      if the request could not be handled
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles HTTP POST requests by delegating to the {@link #processRequest} method.
     * Sets the character encoding and content type for the request and response to UTF-8.
     *
     * @param request  the {@link HttpServletRequest} object that contains the request
     *                 the client made of the servlet
     * @param response the {@link HttpServletResponse} object that contains the
     *                 response the servlet returns to the client
     * @throws ServletException if an input or output error is detected when the servlet
     *                          handles the POST request
     * @throws IOException      if the request could not be handled
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
    }

    /**
     * Processes both GET and POST requests. Determines the appropriate action command
     * using {@link ActionFactory}, executes the command, and navigates to the appropriate
     * page based on the result.
     *
     * <p>If the page is null, redirects to the application's index page and sets an error message.
     * Otherwise, the method forwards or redirects to the determined page based on its type.
     *
     * @param request  the {@link HttpServletRequest} object that contains the request
     *                 the client made of the servlet
     * @param response the {@link HttpServletResponse} object that contains the
     *                 response the servlet returns to the client
     * @throws ServletException if an input or output error is detected when the servlet
     *                          handles the request
     * @throws IOException      if the request could not be handled
     */
    private void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        Page page;
        ActionFactory client = new ActionFactory();
        ActionCommand command = client.defineCommand(request);
        page = command.execute(request);

        if (page == null) {
            String path = ConfigurationManager.getProperty("path.page.index");
            request.getSession().setAttribute("nullPage", MessageManager.getProperty("message.nullpage"));
            response.sendRedirect(request.getContextPath() + path);
            return;
        }

        switch (page.getType()) {
            case FORWARD:
                RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(page.getPath());
                dispatcher.forward(request, response);
                break;
            case SENDREDIRECT:
                response.sendRedirect(request.getContextPath() + page.getPath());
                break;
        }
    }
}

package ru.rsreu.straxov.datalayer.data.common;

import ru.rsreu.straxov.datalayer.data.entities.Page;
import ru.rsreu.straxov.datalayer.data.entities.User;
import ru.rsreu.straxov.datalayer.data.enums.JspChoiceByEnteredUser;
import ru.rsreu.straxov.datalayer.data.enums.TypeEnum;
import ru.rsreu.straxov.datalayer.data.system.ActionCommand;
import ru.rsreu.straxov.datalayer.data.system.ConfigurationManager;
import ru.rsreu.straxov.datalayer.data.system.MessageManager;
import ru.rsreu.straxov.datalayer.data.usercommands.LoginLogic;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


public class LoginCommand implements ActionCommand {
    private static final String PARAM_NAME_LOGIN = "login";
    private static final String PARAM_NAME_PASSWORD = "password";
    private static final String USER_ID = "userId";
    private static final String USER = "user";
    private static final String ROLE = "role";
    private static final String LOGGED_IN_USER_ID = "loggedInUserId";

    private static final String ERROR_BLOCK = "errorBlock";
    private static final String ERROR_LOGIN_PASS = "errorLoginPass";

    @Override
    public Page execute(HttpServletRequest request) {
        String path = null;

        String login = request.getParameter(PARAM_NAME_LOGIN);
        String pass = request.getParameter(PARAM_NAME_PASSWORD);
        request.getSession().invalidate();
        if (LoginLogic.checkLogin(login, pass) != null) {
            User authorizedUser = LoginLogic.checkLogin(login, pass);

            if (LoginLogic.isBlocked(authorizedUser.getUserId()))
            {
                request.setAttribute(ERROR_BLOCK,
                        MessageManager.getProperty("message.blocked"));
                path = ConfigurationManager.getProperty("path.page.login");
                return new Page(path, TypeEnum.FORWARD);
            }

            HttpSession session = request.getSession(true);
            session.setAttribute(USER, login);
            session.setAttribute(USER_ID, authorizedUser.getUserId());
            session.setAttribute(ROLE, authorizedUser.getUserRoleId());
            session.setAttribute(LOGGED_IN_USER_ID, authorizedUser.getUserId());

            try {
                JspChoiceByEnteredUser pageEnum = JspChoiceByEnteredUser.getPageByUserId(authorizedUser.getUserRoleId());
                path = pageEnum.getProperty();
            } catch (IllegalArgumentException e) {
                path = ConfigurationManager.getProperty("path.page.error");
            }
        } else {
            request.setAttribute(ERROR_LOGIN_PASS,
                    MessageManager.getProperty("message.loginError"));
            path = ConfigurationManager.getProperty("path.page.login");
            return new Page(path, TypeEnum.FORWARD);
        }

        return new Page(path, TypeEnum.SENDREDIRECT);
    }
}
package ru.rsreu.straxov.datalayer.data.common;

import ru.rsreu.straxov.datalayer.data.system.ActionCommand;
import ru.rsreu.straxov.datalayer.data.system.ConfigurationManager;
import ru.rsreu.straxov.datalayer.data.entities.Page;
import ru.rsreu.straxov.datalayer.data.enums.TypeEnum;

import javax.servlet.http.HttpServletRequest;

public class LogoutCommand implements ActionCommand {
    @Override
    public Page execute(HttpServletRequest request) {
        String path = ConfigurationManager.getProperty("path.page.index");
        request.getSession().invalidate();
        return new Page(path, TypeEnum.SENDREDIRECT);
    }
}

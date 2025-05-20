package ru.rsreu.straxov.datalayer.data.system;

import ru.rsreu.straxov.datalayer.data.enums.CommandEnum;
import ru.rsreu.straxov.datalayer.data.common.EmptyCommand;

import javax.servlet.http.HttpServletRequest;

public class ActionFactory {
    private static final String COMMAND = "command";
    private static final String WRONG_ACTION = "wrongAction";
    public ActionCommand defineCommand(HttpServletRequest request) {
        ActionCommand current = new EmptyCommand();
        // извлечение имени команды из запроса
        String action = request.getParameter(COMMAND);
        if (action == null || action.isEmpty()) {
            // если команда не задана в текущем запросе
            return current;
        }
        // получение объекта, соответствующего команде
        try {
            CommandEnum currentEnum =
                    CommandEnum.valueOf(action.toUpperCase());
            current = currentEnum.getCurrentCommand();
        } catch (IllegalArgumentException e) {
            request.setAttribute(WRONG_ACTION, action
                    + MessageManager.getProperty("message.wrongAction"));
        }
        return current;
    }
}


package ru.rsreu.straxov.datalayer.data.system;

import com.prutzkow.resourcer.ProjectResourcer;
import com.prutzkow.resourcer.Resourcer;
import ru.rsreu.straxov.datalayer.data.entities.Page;

import javax.servlet.http.HttpServletRequest;

public interface ActionCommand {
    static final Resourcer resourcer = ProjectResourcer.getInstance();
    Page execute(HttpServletRequest request);
}

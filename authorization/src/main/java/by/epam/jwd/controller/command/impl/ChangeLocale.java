package by.epam.jwd.controller.command.impl;

import javax.servlet.http.HttpServletRequest;

import by.epam.jwd.controller.PageContainer;
import by.epam.jwd.controller.command.Command;
import javax.servlet.jsp.jstl.core.Config;


public class ChangeLocale implements Command {

    @Override
    public String execute(HttpServletRequest request) {

        String newLocalization = request.getParameter("localization");

        request.getSession(true).setAttribute("localization", newLocalization);
        Config.set(request.getSession(), Config.FMT_LOCALE, newLocalization);

        return PageContainer.INDEX_PAGE;
    }
}
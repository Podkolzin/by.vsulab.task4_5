package by.vgulab.epam.controller;

import by.vgulab.epam.util.MainServiceFactoryImpl;
import by.vgulab.epam.util.ServiceFactory;

import javax.servlet.http.HttpServlet;

abstract public class BaseServlet extends HttpServlet {

    public ServiceFactory getFactory() {
        return new MainServiceFactoryImpl();
    }


}

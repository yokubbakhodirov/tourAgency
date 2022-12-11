package com.company.tourAgency.controller;

import com.company.tourAgency.command.Command;
import com.company.tourAgency.command.CommandType;
import com.company.tourAgency.exception.CommandException;
import com.company.tourAgency.controller.navigation.Router;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

import static com.company.tourAgency.controller.navigation.AttributeParameterKey.REQUEST_PARAMETER_COMMAND;

@WebServlet(name = "controller", urlPatterns = {"/controller"})
public class Controller extends HttpServlet {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public void init() throws ServletException {
        logger.info("Servlet initialized");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processCommand(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processCommand(request, response);
    }

    private void processCommand(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String commandStr = request.getParameter(REQUEST_PARAMETER_COMMAND);
        System.out.println("commandStr = " + commandStr);
        Command command = CommandType.define(commandStr);

        try {
            Router router = command.execute(request);
            String page = router.getPage();

            switch (router.getType()) {
                case FORWARD -> request.getRequestDispatcher(page).forward(request,response);
                case REDIRECT -> response.sendRedirect(request.getContextPath() + page);
                default -> {
                    logger.error("Invalid routing type");
                    response.sendError(500);
                }
            }
        } catch (CommandException e) {
            logger.error("Command execution exception: " + commandStr, e);
            throw new ServletException(e);
        }
    }

    @Override
    public void destroy() {
        logger.info("Servlet destroyed");
    }
}

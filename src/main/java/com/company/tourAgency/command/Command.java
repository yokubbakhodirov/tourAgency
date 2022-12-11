package com.company.tourAgency.command;

import com.company.tourAgency.exception.CommandException;
import com.company.tourAgency.controller.navigation.Router;
import jakarta.servlet.http.HttpServletRequest;

public interface Command {
    Router execute(HttpServletRequest request) throws CommandException;
}

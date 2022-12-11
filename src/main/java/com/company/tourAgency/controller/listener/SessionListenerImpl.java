package com.company.tourAgency.controller.listener;

import com.company.tourAgency.entity.User;
import com.company.tourAgency.entity.enums.UserRole;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static com.company.tourAgency.controller.navigation.AttributeParameterKey.*;
import static com.company.tourAgency.controller.navigation.PageURL.DEFAULT;

@WebListener
public class SessionListenerImpl implements HttpSessionListener {
    private static final Logger logger = LogManager.getLogger();
    private static final String DEFAULT_LOCALIZATION = "en";

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        HttpSession session = se.getSession();

        session.setAttribute(SESSION_ATTRIBUTE_USER, User.builder().role(UserRole.GUEST).build());
        session.setAttribute(SESSION_ATTRIBUTE_LOCALIZATION, DEFAULT_LOCALIZATION);
        session.setAttribute(SESSION_ATTRIBUTE_CURRENT_PAGE, DEFAULT);
        logger.info("Session created: {}", session.getId());
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        logger.info("Session destroyed: {}", se.getSession().getId());
    }

}

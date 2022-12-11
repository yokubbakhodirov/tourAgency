package com.company.tourAgency.utils.mail;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import java.util.Properties;

/**
 * Mail session factory. Creates sessions based on passed properties
 */
public class MailSessionFactory {
    private static final String NAME_PROPERTY = "mail.smtp.user";
    private static final String PASSWORD_PROPERTY = "mail.smtp.password";

    private final Properties sessionProperties;
    private final String name;
    private final String password;

    MailSessionFactory(Properties sessionProperties) {
        this.sessionProperties = sessionProperties;
        name = sessionProperties.getProperty(NAME_PROPERTY);
        password = sessionProperties.getProperty(PASSWORD_PROPERTY);
    }

    Session createSession() {
        return Session.getDefaultInstance(sessionProperties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(name, password);
            }
        });
    }
}

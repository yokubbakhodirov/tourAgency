package com.company.tourAgency.controller.navigation;


import static com.company.tourAgency.controller.navigation.PageURL.*;
import static com.company.tourAgency.controller.navigation.Router.Type.*;

/**
 * Router. Contains page and router type.
 */
public class Router {
    private String page;
    private Type type;

    public enum Type {
        FORWARD, REDIRECT
    }

    public Router(String page) {
        this.page = (page != null ? page : DEFAULT);
    }

    public Router(String page, Type type) {
        this.page = (page != null ? page : DEFAULT);
        this.type = (type != null ? type : FORWARD);
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = (page != null ? page : DEFAULT);
    }

    public Type getType() {
        return type;
    }

    public void setForward() {
        this.type = FORWARD;
    }

    public void setRedirect() {
        this.type = REDIRECT;
    }
}

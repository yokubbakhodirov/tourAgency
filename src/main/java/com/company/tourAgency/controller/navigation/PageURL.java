package com.company.tourAgency.controller.navigation;

/**
 * Page urls. Contains paths to pages.
 */
public interface PageURL {
    String DEFAULT = "/view/page/home.jsp";
    String HOME = "/view/page/home.jsp";
    String SIGN_UP = "/view/page/signup.jsp";
    String SIGN_IN = "/view/page/signin.jsp";
    String ABOUT_US = "/view/page/about.jsp";
    String MY_TOURS = "/view/page/mytours.jsp";
    String TOURS = "/view/page/tours.jsp";
    String REST = "/view/page/rest.jsp";
    String EXCURSION = "/view/page/excursion.jsp";
    String SHOPPING = "/view/page/shopping.jsp";
    String BUY_PAGE = "/view/page/buy.jsp";
    String ADD_TOURS = "/view/page/addtours.jsp";
    String ADMIN = "/view/page/admin.jsp";
    String SEND_KEY = "/view/page/sendkey.jsp";
    String CONFIRM_KEY = "/view/page/confirm_key.jsp";
    String CHANGE_PASSWORD = "/view/page/changepassword.jsp";

    String BASE_URL = "/controller?command=%s";

}

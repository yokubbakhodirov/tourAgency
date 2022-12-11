package com.company.tourAgency.command;

import com.company.tourAgency.command.impl.aboutUs.AboutUsCommand;
import com.company.tourAgency.command.impl.*;
import com.company.tourAgency.command.impl.admin.AdminCommand;
import com.company.tourAgency.command.impl.buy.FinishBuyCommand;
import com.company.tourAgency.command.impl.buy.StartBuyCommand;
import com.company.tourAgency.command.impl.changePassword.*;
import com.company.tourAgency.command.impl.localization.ChangeLocalization;
import com.company.tourAgency.command.impl.order.DeleteOrderCommand;
import com.company.tourAgency.command.impl.tour.*;
import com.company.tourAgency.command.impl.tour.addTours.AddTours;
import com.company.tourAgency.command.impl.tour.addTours.FinishAddTours;
import com.company.tourAgency.command.impl.user.*;

public enum CommandType {
    DEFAULT(new DefaultCommand()),
    HOME(new HomeCommand()),
    SIGN_IN(new StartSignInCommand()),
    SIGN_UP(new StartSignUpCommand()),
    FINISH_SIGN_IN(new FinishSignInCommand()),
    FINISH_SIGN_UP(new FinishSignUpCommand()),
    ABOUT_US(new AboutUsCommand()),
    MY_TOURS(new MyToursCommand()),
    TOURS(new ToursCommand()),
    REST(new RestCommand()),
    EXCURSION(new ExcursionCommand()),
    SHOPPING(new ShoppingCommand()),

    BUY(new StartBuyCommand()),
    FINISH_BUY(new FinishBuyCommand()),
    ADD_TOURS(new AddTours()),
    FINISH_ADD_TOURS(new FinishAddTours()),
    ADMIN(new AdminCommand()),
    SEND_KEY(new StartSendKeyCommand()),
    FINISH_SEND_KEY(new FinishSendKeyCommand()),
    CONFIRM_KEY(new StartConfirmKeyCommand()),
    FINISH_CONFIRM_KEY(new FinishConfirmKeyCommand()),
    FINISH_CHANGE_PASSWORD(new FinishChangePasswordCommand()),
    CHANGE_PASSWORD(new StartChangePasswordCommand()),
    LOGOUT(new LogOutCommand()),
    DELETE_USER(new DeleteUserCommand()),
    DELETE_ORDER(new DeleteOrderCommand()),
    CHANGE_LOCALIZATION(new ChangeLocalization()),
    DELETE_TOUR(new DeleteTourCommand());

    private Command command;

    CommandType(Command command) {
        this.command = command;
    }

    public static Command define(String commandStr) {
        CommandType commandType;
        try {
         commandType = commandStr != null ? CommandType.valueOf(commandStr.toUpperCase()) : DEFAULT;
        } catch (IllegalArgumentException e) {
            commandType = DEFAULT;
        }

        return commandType.command;
    }

    public static CommandType defineCommandType(String commandStr) {
        CommandType commandType;
        try {
            commandType = commandStr != null ? CommandType.valueOf(commandStr.toUpperCase()) : DEFAULT;
        } catch (IllegalArgumentException e) {
            commandType = DEFAULT;
        }
        return commandType;
    }
}

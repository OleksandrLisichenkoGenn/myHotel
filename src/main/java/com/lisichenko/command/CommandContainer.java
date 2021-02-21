package com.lisichenko.command;

import java.util.HashMap;
import java.util.Map;

/**
 * Container for all the commands which will used for
 * call the specific work with DataBase and
 * returns some path for client.
 */
public class CommandContainer {
    private static CommandContainer commandContainer;
    private final Map<String, Command> container = new HashMap<>();

    public CommandContainer() {
        container.put("createAccount", new CreateAccountCommand());
        container.put("getRooms", new GetRoomsCommand());
        container.put("login", new LoginCommand());
        container.put("logout", new LogoutCommand());
        container.put("orderRawRoom", new CreateRawOrderCommand());
        container.put("orderCurrentRoom", new CreateOrderWithSpecificRoomCommand());
        container.put("getOrdersAdmin", new GetOrdersForAdminCommand());
        container.put("updateOrder", new UpdateOrderCommand());
        container.put("getOrdersCustomer", new GetOrdersForCustomerCommand());
        container.put("payOrder", new PayOrderCommand());
        container.put("setLocale", new SetLocaleCommand());
        container.put("aboutUs", new GetAboutUsPageCommand());
        container.put("register", new GetRegisterPageCommand());
    }

    public static CommandContainer getInstance() {
        if (commandContainer == null) {
            commandContainer = new CommandContainer();
        }
        return commandContainer;
    }

    public Command getCommand(String command) {
        return container.get(command);
    }
}

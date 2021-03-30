package ru.vpavlova.tm.command;

import ru.vpavlova.tm.api.service.ServiceLocator;
import ru.vpavlova.tm.enumerated.Role;

public abstract class AbstractCommand {

    protected ServiceLocator serviceLocator;

    public AbstractCommand() {
    }

    public void setServiceLocator(ServiceLocator serviceLocator) {
        this.serviceLocator = serviceLocator;
    }

    public abstract String arg();

    public abstract String name();

    public abstract String description();

    public abstract void execute();

    public Role[] roles() {
        return null;
    }

}

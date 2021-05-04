package ru.vpavlova.tm.command;

import lombok.NoArgsConstructor;
import org.jetbrains.annotations.Nullable;
import ru.vpavlova.tm.api.service.ServiceLocator;
import ru.vpavlova.tm.enumerated.Role;

@NoArgsConstructor
public abstract class AbstractCommand {

    @Nullable
    protected ServiceLocator serviceLocator;

    public void setServiceLocator(@Nullable ServiceLocator serviceLocator) {
        this.serviceLocator = serviceLocator;
    }

    @Nullable
    public abstract String arg();

    @Nullable
    public abstract String name();

    @Nullable
    public abstract String description();

    @Nullable
    public abstract void execute();

    @Nullable
    public Role[] roles() {
        return null;
    }

}

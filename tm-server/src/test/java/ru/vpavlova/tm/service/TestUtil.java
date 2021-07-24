package ru.vpavlova.tm.service;

import org.jetbrains.annotations.NotNull;
import ru.vpavlova.tm.api.IPropertyService;
import ru.vpavlova.tm.api.service.IConnectionService;
import ru.vpavlova.tm.api.service.dto.IUserService;
import ru.vpavlova.tm.enumerated.Role;
import ru.vpavlova.tm.service.dto.UserService;

public class TestUtil {

    @NotNull
    static final IPropertyService propertyService = new PropertyService();

    @NotNull
    static final IConnectionService connectionService = new ConnectionService(propertyService);

    @NotNull
    static final IUserService userService = new UserService(propertyService, connectionService);

    public static void initUser() {
        if (!userService.findByLogin("test").isPresent()) {
            userService.create("test", "test", "test@test.ru");
        }
        if (!userService.findByLogin("test2").isPresent()) {
            userService.create("test2", "test", "test@test.ru");
        }
        if (!userService.findByLogin("admin").isPresent()) {
            userService.create("admin", "admin", Role.ADMIN);
        }
    }

}

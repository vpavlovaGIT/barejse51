package ru.vpavlova.tm.endpoint;

import lombok.SneakyThrows;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.vpavlova.tm.api.endpoint.IAdminEndpoint;
import ru.vpavlova.tm.api.service.ServiceLocator;
import ru.vpavlova.tm.entity.Session;
import ru.vpavlova.tm.entity.User;
import ru.vpavlova.tm.enumerated.Role;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import java.util.List;

@WebService
public class AdminEndpoint extends AbstractEndpoint implements IAdminEndpoint {

    public AdminEndpoint(@NotNull ServiceLocator serviceLocator) {
        super(serviceLocator);
    }

    @Override
    @WebMethod
    @SneakyThrows
    public void addUser(
            @WebParam (name = "user", partName = "user") @NotNull User user,
            @WebParam (name = "session", partName = "session") @NotNull Session session
    ) {
        serviceLocator.getSessionService().validateAdmin(session, Role.ADMIN);
        serviceLocator.getUserService().add(user);
    }

    @Override
    @WebMethod
    @SneakyThrows
    public void removeUser(
            @WebParam (name = "user", partName = "user") @NotNull User user,
            @WebParam (name = "session", partName = "session") @NotNull Session session
    ) {
        serviceLocator.getSessionService().validateAdmin(session, Role.ADMIN);
        serviceLocator.getUserService().remove(user);
    }

    @Override
    @WebMethod
    @SneakyThrows
    public @NotNull User createUser(
            @WebParam (name = "login", partName = "login") @NotNull String login,
            @WebParam (name = "password", partName = "password") @NotNull String password,
            @WebParam (name = "session", partName = "session") @NotNull Session session
    ) {
        serviceLocator.getSessionService().validateAdmin(session, Role.ADMIN);
        return serviceLocator.getUserService().create(login, password);
    }

    @Override
    @WebMethod
    @SneakyThrows
    public void createUserWithEmail(
            @WebParam (name = "login", partName = "login") @NotNull String login,
            @WebParam (name = "password", partName = "password") @NotNull String password,
            @WebParam (name = "email", partName = "email") @NotNull String email,
            @WebParam (name = "session", partName = "session") @NotNull Session session
    ) {
        serviceLocator.getSessionService().validateAdmin(session, Role.ADMIN);
        serviceLocator.getUserService().create(login, password, email);
    }

    @Override
    @WebMethod
    @SneakyThrows
    public void setUserPassword(
            @WebParam (name = "userId", partName = "userId") @NotNull String userId,
            @WebParam (name = "password", partName = "password") @NotNull String password,
            @WebParam (name = "session", partName = "session") @NotNull Session session
    ) {
        serviceLocator.getSessionService().validateAdmin(session, Role.ADMIN);
        serviceLocator.getUserService().setPassword(userId, password);
    }

    @Override
    @WebMethod
    @SneakyThrows
    public void updateUser(
            @WebParam (name = "userId", partName = "userId") @NotNull String userId,
            @WebParam (name = "firstName", partName = "firstName") @Nullable String firstName,
            @WebParam (name = "lastName", partName = "lastName") @Nullable String lastName,
            @WebParam (name = "middleName", partName = "middleName") @Nullable String middleName,
            @WebParam (name = "session", partName = "session") @NotNull Session session
    ) {
        serviceLocator.getSessionService().validateAdmin(session, Role.ADMIN);
        serviceLocator.getUserService().updateUser(userId, firstName, lastName, middleName);
    }

    @Override
    @WebMethod
    @SneakyThrows
    public void lockUserByLogin(
            @WebParam (name = "login", partName = "login") @NotNull String login,
            @WebParam (name = "session", partName = "session") @NotNull Session session
    ) {
        serviceLocator.getSessionService().validateAdmin(session, Role.ADMIN);
        serviceLocator.getUserService().lockUserByLogin(login);
    }

    @Override
    @WebMethod
    @SneakyThrows
    public void unlockUserByLogin(
            @WebParam (name = "login", partName = "login") @NotNull String login,
            @WebParam (name = "session", partName = "session") @NotNull Session session
    ) {
        serviceLocator.getSessionService().validateAdmin(session, Role.ADMIN);
        serviceLocator.getUserService().unlockUserByLogin(login);
    }

    @Nullable
    @Override
    @WebMethod
    @SneakyThrows
    public List<User> findAllUsers(
            @WebParam (name = "session", partName = "session") @NotNull Session session
    ) {
        serviceLocator.getSessionService().validateAdmin(session, Role.ADMIN);
        return serviceLocator.getUserService().findAll();
    }

    @Override
    @WebMethod
    @SneakyThrows
    public void clearUsers(
            @WebParam (name = "session", partName = "session") @NotNull Session session
    ) {
        serviceLocator.getSessionService().validateAdmin(session, Role.ADMIN);
        serviceLocator.getUserService().clear();

    }

    @Override
    @WebMethod
    @SneakyThrows
    public void addAllUsers(
            @WebParam (name = "users", partName = "users") @NotNull List<User> users,
            @WebParam (name = "session", partName = "session") @NotNull Session session
    ) {
        serviceLocator.getSessionService().validateAdmin(session, Role.ADMIN);
        serviceLocator.getUserService().addAll(users);
    }

}

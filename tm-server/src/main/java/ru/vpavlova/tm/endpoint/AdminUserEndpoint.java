package ru.vpavlova.tm.endpoint;

import lombok.SneakyThrows;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.vpavlova.tm.api.endpoint.IAdminUserEndpoint;
import ru.vpavlova.tm.api.service.ServiceLocator;
import ru.vpavlova.tm.entity.Session;
import ru.vpavlova.tm.entity.User;
import ru.vpavlova.tm.enumerated.Role;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import java.util.List;

@WebService
public class AdminUserEndpoint extends AbstractEndpoint implements IAdminUserEndpoint {

    public AdminUserEndpoint(@NotNull ServiceLocator serviceLocator) {
        super(serviceLocator);
    }

    @Override
    @WebMethod
    @SneakyThrows
    public void addUser(
            @WebParam (name = "session", partName = "session") @NotNull Session session,
            @WebParam (name = "user", partName = "user") @NotNull User user
    ) {
        serviceLocator.getSessionService().validateAdmin(session, Role.ADMIN);
        serviceLocator.getUserService().add(user);
    }

    @Override
    @WebMethod
    @SneakyThrows
    public void removeUser(
            @WebParam (name = "session", partName = "session") @NotNull Session session,
            @WebParam (name = "user", partName = "user") @NotNull User user
    ) {
        serviceLocator.getSessionService().validateAdmin(session, Role.ADMIN);
        serviceLocator.getUserService().remove(user);
    }

    @Override
    @WebMethod
    public void removeOneByLogin(
            @WebParam(name = "session", partName = "session") @Nullable final Session session,
            @WebParam(name = "login", partName = "login") @Nullable final String login
    ) throws Exception {
        serviceLocator.getSessionService().validateAdmin(session, Role.ADMIN);
        serviceLocator.getUserService().removeByLogin(login);
    }

    @NotNull
    @Override
    @WebMethod
    @SneakyThrows
    public User createUser(
            @WebParam (name = "session", partName = "session") @NotNull Session session,
            @WebParam (name = "login", partName = "login") @NotNull String login,
            @WebParam (name = "password", partName = "password") @NotNull String password
    ) {
        serviceLocator.getSessionService().validateAdmin(session, Role.ADMIN);
        return serviceLocator.getUserService().create(login, password);
    }

    @Override
    @WebMethod
    @SneakyThrows
    public void createUserWithEmail(
            @WebParam (name = "session", partName = "session") @NotNull Session session,
            @WebParam (name = "login", partName = "login") @NotNull String login,
            @WebParam (name = "password", partName = "password") @NotNull String password,
            @WebParam (name = "email", partName = "email") @NotNull String email
    ) {
        serviceLocator.getSessionService().validateAdmin(session, Role.ADMIN);
        serviceLocator.getUserService().create(login, password, email);
    }

    @Override
    @WebMethod
    @SneakyThrows
    public void setUserPassword(
            @WebParam (name = "session", partName = "session") @NotNull Session session,
            @WebParam (name = "userId", partName = "userId") @NotNull String userId,
            @WebParam (name = "password", partName = "password") @NotNull String password
    ) {
        serviceLocator.getSessionService().validateAdmin(session, Role.ADMIN);
        serviceLocator.getUserService().setPassword(userId, password);
    }

    @Override
    @WebMethod
    @SneakyThrows
    public void updateUser(
            @WebParam (name = "session", partName = "session") @NotNull Session session,
            @WebParam (name = "userId", partName = "userId") @NotNull String userId,
            @WebParam (name = "firstName", partName = "firstName") @Nullable String firstName,
            @WebParam (name = "lastName", partName = "lastName") @Nullable String lastName,
            @WebParam (name = "middleName", partName = "middleName") @Nullable String middleName
    ) {
        serviceLocator.getSessionService().validateAdmin(session, Role.ADMIN);
        serviceLocator.getUserService().updateUser(userId, firstName, lastName, middleName);
    }

    @Override
    @WebMethod
    @SneakyThrows
    public void lockUserByLogin(
            @WebParam (name = "session", partName = "session") @NotNull Session session,
            @WebParam (name = "login", partName = "login") @NotNull String login
    ) {
        serviceLocator.getSessionService().validateAdmin(session, Role.ADMIN);
        serviceLocator.getUserService().lockUserByLogin(login);
    }

    @Override
    @WebMethod
    @SneakyThrows
    public void unlockUserByLogin(
            @WebParam (name = "session", partName = "session") @NotNull Session session,
            @WebParam (name = "login", partName = "login") @NotNull String login
    ) {
        serviceLocator.getSessionService().validateAdmin(session, Role.ADMIN);
        serviceLocator.getUserService().unlockUserByLogin(login);
    }

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
            @WebParam (name = "session", partName = "session") @NotNull Session session,
            @WebParam (name = "users", partName = "users") @NotNull List<User> users
    ) {
        serviceLocator.getSessionService().validateAdmin(session, Role.ADMIN);
        serviceLocator.getUserService().addAll(users);
    }

}

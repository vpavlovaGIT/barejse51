package ru.vpavlova.tm.endpoint;

import lombok.SneakyThrows;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.vpavlova.tm.api.endpoint.IAdminUserEndpoint;
import ru.vpavlova.tm.api.service.ServiceLocator;
import ru.vpavlova.tm.dto.SessionDTO;
import ru.vpavlova.tm.dto.UserDTO;
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
            @WebParam(name = "session", partName = "session") @NotNull SessionDTO session,
            @WebParam(name = "user", partName = "user") @NotNull UserDTO user
    ) {
        serviceLocator.getSessionDTOService().validateAdmin(session, Role.ADMIN);
        serviceLocator.getUserDTOService().add(user);
    }

    @Override
    @WebMethod
    @SneakyThrows
    public void removeUser(
            @WebParam(name = "session", partName = "session") @NotNull SessionDTO session,
            @WebParam(name = "user", partName = "user") @NotNull UserDTO user
    ) {
        serviceLocator.getSessionDTOService().validateAdmin(session, Role.ADMIN);
        serviceLocator.getUserDTOService().remove(user);
    }

    @Override
    @WebMethod
    @SneakyThrows
    public void removeOneByLogin(
            @WebParam(name = "session", partName = "session") @Nullable final SessionDTO session,
            @WebParam(name = "login", partName = "login") @Nullable final String login
    ) {
        serviceLocator.getSessionDTOService().validateAdmin(session, Role.ADMIN);
        serviceLocator.getUserService().removeByLogin(login);
    }

    @NotNull
    @Override
    @WebMethod
    @SneakyThrows
    public void createUser(
            @WebParam(name = "session", partName = "session") @NotNull SessionDTO session,
            @WebParam(name = "login", partName = "login") @NotNull String login,
            @WebParam(name = "password", partName = "password") @NotNull String password
    ) {
        serviceLocator.getSessionDTOService().validateAdmin(session, Role.ADMIN);
        serviceLocator.getUserService().create(login, password);
    }

    @Override
    @WebMethod
    @SneakyThrows
    public void createUserWithEmail(
            @WebParam(name = "session", partName = "session") @NotNull SessionDTO session,
            @WebParam(name = "login", partName = "login") @NotNull String login,
            @WebParam(name = "password", partName = "password") @NotNull String password,
            @WebParam(name = "email", partName = "email") @NotNull String email
    ) {
        serviceLocator.getSessionDTOService().validateAdmin(session, Role.ADMIN);
        serviceLocator.getUserService().create(login, password, email);
    }

    @Override
    @WebMethod
    @SneakyThrows
    public void setUserPassword(
            @WebParam(name = "session", partName = "session") @NotNull SessionDTO session,
            @WebParam(name = "userId", partName = "userId") @NotNull String userId,
            @WebParam(name = "password", partName = "password") @NotNull String password
    ) {
        serviceLocator.getSessionDTOService().validateAdmin(session, Role.ADMIN);
        serviceLocator.getUserService().setPassword(userId, password);
    }

    @Override
    @WebMethod
    @SneakyThrows
    public void updateUser(
            @WebParam(name = "session", partName = "session") @NotNull SessionDTO session,
            @WebParam(name = "userId", partName = "userId") @NotNull String userId,
            @WebParam(name = "firstName", partName = "firstName") @Nullable String firstName,
            @WebParam(name = "lastName", partName = "lastName") @Nullable String lastName,
            @WebParam(name = "middleName", partName = "middleName") @Nullable String middleName
    ) {
        serviceLocator.getSessionDTOService().validateAdmin(session, Role.ADMIN);
        serviceLocator.getUserService().updateUser(userId, firstName, lastName, middleName);
    }

    @Override
    @WebMethod
    @SneakyThrows
    public void lockUserByLogin(
            @WebParam(name = "session", partName = "session") @NotNull SessionDTO session,
            @WebParam(name = "login", partName = "login") @NotNull String login
    ) {
        serviceLocator.getSessionDTOService().validateAdmin(session, Role.ADMIN);
        serviceLocator.getUserDTOService().lockUserByLogin(login);
    }

    @Override
    @WebMethod
    @SneakyThrows
    public void unlockUserByLogin(
            @WebParam(name = "session", partName = "session") @NotNull SessionDTO session,
            @WebParam(name = "login", partName = "login") @NotNull String login
    ) {
        serviceLocator.getSessionDTOService().validateAdmin(session, Role.ADMIN);
        serviceLocator.getUserDTOService().lockUserByLogin(login);
    }

    @Override
    @WebMethod
    @SneakyThrows
    public List<UserDTO> findAllUsers(
            @WebParam(name = "session", partName = "session") @NotNull SessionDTO session
    ) {
        serviceLocator.getSessionDTOService().validateAdmin(session, Role.ADMIN);
        return serviceLocator.getUserDTOService().findAll();
    }

    @Override
    @WebMethod
    @SneakyThrows
    public void clearUsers(
            @WebParam(name = "session", partName = "session") @NotNull SessionDTO session
    ) {
        serviceLocator.getSessionDTOService().validateAdmin(session, Role.ADMIN);
        serviceLocator.getUserService().clear();

    }

    @Override
    @WebMethod
    @SneakyThrows
    public void addAllUsers(
            @WebParam(name = "session", partName = "session") @NotNull SessionDTO session,
            @WebParam(name = "users", partName = "users") @NotNull List<UserDTO> users
    ) {
        serviceLocator.getSessionDTOService().validateAdmin(session, Role.ADMIN);
        serviceLocator.getUserDTOService().addAll(users);
    }

}

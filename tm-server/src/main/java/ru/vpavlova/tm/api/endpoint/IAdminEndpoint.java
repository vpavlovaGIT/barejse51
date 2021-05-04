package ru.vpavlova.tm.api.endpoint;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.vpavlova.tm.entity.Session;
import ru.vpavlova.tm.entity.User;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import java.util.List;

public interface IAdminEndpoint {

    @WebMethod
    void addUser(
            @WebParam(name = "user", partName = "user") @NotNull final User user,
            @WebParam(name = "session", partName = "session") @NotNull Session session
    );

    @WebMethod
    void removeUser(
            @WebParam(name = "user", partName = "user") @NotNull final User user,
            @WebParam(name = "session", partName = "session") @NotNull Session session
    );

    @NotNull
    @WebMethod
    User createUser(
            @WebParam(name = "login", partName = "login") @NotNull final String login,
            @WebParam(name = "password", partName = "password") @NotNull final String password,
            @WebParam(name = "session", partName = "session") @NotNull Session session
    );

    @WebMethod
    void createUserWithEmail(
            @WebParam(name = "login", partName = "login") @NotNull final String login,
            @WebParam(name = "password", partName = "password") @NotNull final String password,
            @WebParam(name = "email", partName = "email") @NotNull final String email,
            @WebParam(name = "session", partName = "session") @NotNull Session session
    );

    @WebMethod
    void setUserPassword(
            @WebParam(name = "userId", partName = "userId") @NotNull final String userId,
            @WebParam(name = "password", partName = "password") @NotNull final String password,
            @WebParam(name = "session", partName = "session") @NotNull Session session
    );

    @WebMethod
    void updateUser(
            @WebParam(name = "userId", partName = "userId") @NotNull final String userId,
            @WebParam(name = "firstName", partName = "firstName") @Nullable final String firstName,
            @WebParam(name = "lastName", partName = "lastName") @Nullable final String lastName,
            @WebParam(name = "middleName", partName = "middleName") @Nullable final String middleName,
            @WebParam(name = "session", partName = "session") @NotNull Session session
    );

    @WebMethod
    void lockUserByLogin(
            @WebParam(name = "login", partName = "login") @NotNull final String login,
            @WebParam(name = "session", partName = "session") @NotNull Session session
    );

    @WebMethod
    void unlockUserByLogin(
            @WebParam(name = "login", partName = "login") @NotNull final String login,
            @WebParam(name = "session", partName = "session") @NotNull Session session
    );

    @Nullable
    @WebMethod
    List<User> findAllUsers(
            @WebParam(name = "session", partName = "session") @NotNull Session session
    );

    @WebMethod
    void clearUsers(
            @WebParam(name = "session", partName = "session") @NotNull Session session
    );

    @WebMethod
    void addAllUsers(
            @WebParam(name = "userList", partName = "userList") final List<User> users,
            @WebParam(name = "session", partName = "session") @NotNull Session session
    );

}

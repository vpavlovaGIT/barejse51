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
            @WebParam(name = "session", partName = "session") @NotNull Session session,
            @WebParam(name = "user", partName = "user") @NotNull final User user
    );

    @WebMethod
    void removeUser(
            @WebParam(name = "session", partName = "session") @NotNull Session session,
            @WebParam(name = "user", partName = "user") @NotNull final User user
    );

    @WebMethod
    void removeOneByLogin(
            @WebParam(name = "session", partName = "session") @NotNull Session session,
            @WebParam(name = "login", partName = "login") @Nullable String login
    ) throws Exception;

    @NotNull
    @WebMethod
    User createUser(
            @WebParam(name = "session", partName = "session") @NotNull Session session,
            @WebParam(name = "login", partName = "login") @NotNull final String login,
            @WebParam(name = "password", partName = "password") @NotNull final String password
    );

    @WebMethod
    void createUserWithEmail(
            @WebParam(name = "session", partName = "session") @NotNull Session session,
            @WebParam(name = "login", partName = "login") @NotNull final String login,
            @WebParam(name = "password", partName = "password") @NotNull final String password,
            @WebParam(name = "email", partName = "email") @NotNull final String email
    );

    @WebMethod
    void setUserPassword(
            @WebParam(name = "session", partName = "session") @NotNull Session session,
            @WebParam(name = "userId", partName = "userId") @NotNull final String userId,
            @WebParam(name = "password", partName = "password") @NotNull final String password
    );

    @WebMethod
    void updateUser(
            @WebParam(name = "session", partName = "session") @NotNull Session session,
            @WebParam(name = "userId", partName = "userId") @NotNull final String userId,
            @WebParam(name = "firstName", partName = "firstName") @Nullable final String firstName,
            @WebParam(name = "lastName", partName = "lastName") @Nullable final String lastName,
            @WebParam(name = "middleName", partName = "middleName") @Nullable final String middleName
    );

    @WebMethod
    void lockUserByLogin(
            @WebParam(name = "session", partName = "session") @NotNull Session session,
            @WebParam(name = "login", partName = "login") @NotNull final String login
    );

    @WebMethod
    void unlockUserByLogin(
            @WebParam(name = "session", partName = "session") @NotNull Session session,
            @WebParam(name = "login", partName = "login") @NotNull final String login
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
            @WebParam(name = "session", partName = "session") @NotNull Session session,
            @WebParam(name = "userList", partName = "userList") final List<User> users
    );

}

package ru.vpavlova.tm.api.repository;

import org.apache.ibatis.annotations.*;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.vpavlova.tm.api.IRepository;
import ru.vpavlova.tm.dto.UserDTO;

import java.util.List;
import java.util.Optional;

public interface IUserRepository extends IRepository<UserDTO> {

    @Insert("INSERT INTO `app_user`(`id`, `login`, `lock`, `password_hash`, " +
            "`email`, `first_name`, `last_name`, `middle_name`, `role`) " +
            "VALUES(#{id}, #{login}, #{lock}, #{passwordHash}, " +
            "#{email}, #{firstName}, #{lastName}, #{middleName}, #{role})")
    void add(@NotNull UserDTO user);

    @Delete("DELETE FROM app_user")
    void clear();

    @NotNull
    @Select("SELECT * FROM `app_user`")
    @Result(column = "id", property = "id")
    @Result(column = "login", property = "login")
    @Result(column = "password_hash", property = "passwordHash")
    @Result(column = "email", property = "email")
    @Result(column = "first_name", property = "firstName")
    @Result(column = "last_name", property = "lastName")
    @Result(column = "middle_name", property = "middleName")
    @Result(column = "role", property = "role")
    @Result(column = "lock", property = "lock")
    List<UserDTO> findAll();

    @NotNull
    @Select("SELECT * FROM `app_user` WHERE `login` = #{login} LIMIT 1")
    @Result(column = "id", property = "id")
    @Result(column = "login", property = "login")
    @Result(column = "password_hash", property = "passwordHash")
    @Result(column = "email", property = "email")
    @Result(column = "first_name", property = "firstName")
    @Result(column = "last_name", property = "lastName")
    @Result(column = "middle_name", property = "middleName")
    @Result(column = "role", property = "role")
    @Result(column = "lock", property = "lock")
    UserDTO findByLogin(@Param("login") @NotNull String login);

    @Nullable
    @Select("SELECT * FROM `app_user` WHERE `email` = #{email} LIMIT 1")
    UserDTO findUserByEmail(@Nullable String email);

    @NotNull
    @Select("SELECT * FROM `app_user` WHERE `id` = #{id} LIMIT 1")
    @Result(column = "id", property = "id")
    @Result(column = "login", property = "login")
    @Result(column = "password_hash", property = "passwordHash")
    @Result(column = "email", property = "email")
    @Result(column = "first_name", property = "firstName")
    @Result(column = "last_name", property = "lastName")
    @Result(column = "middle_name", property = "middleName")
    @Result(column = "role", property = "role")
    @Result(column = "lock", property = "lock")
    Optional<UserDTO> findOneById(@Param("id") @Nullable String id);

    @Update("UPDATE `app_user` SET `locked` = `1` WHERE `id` = #{id}")
    void lockUser(@NotNull UserDTO user);

    @Update("UPDATE `app_user` SET `locked` = `0` WHERE `id` = #{id}")
    void unlockUser(@NotNull UserDTO user);

    @Delete("DELETE FROM `app_user` WHERE `login` = #{login}")
    void removeByLogin(@Param("login") @NotNull String login);

    @Delete("DELETE FROM `app_user` WHERE `id` = #{id}")
    void removeById(@Param("id") @Nullable String id);

    @Update("UPDATE `app_user` SET `password_hash` = #{password} WHERE `id` = #{userId}")
    void setPassword(@Param("password") @NotNull String password, @Param("userId") @NotNull String userId);

    @Update("UPDATE `app_user` SET `first_name` = #{firstName}, `last_name` = #{lastName}, " +
            "`middle_name` = #{middleName} WHERE `id` = #{id}")
    void updateUser(@NotNull UserDTO user);

}

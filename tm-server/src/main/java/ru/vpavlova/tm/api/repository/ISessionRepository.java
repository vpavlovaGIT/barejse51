package ru.vpavlova.tm.api.repository;

import org.apache.ibatis.annotations.*;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.vpavlova.tm.api.IRepository;
import ru.vpavlova.tm.dto.SessionDTO;

import java.util.List;
import java.util.Optional;

public interface ISessionRepository extends IRepository<SessionDTO> {

    @Insert("INSERT INTO `app_session`(`id`, `user_id`, `signature`, `timestamp`) " +
            "VALUES(#{id}, #{userId}, #{signature}, #{timestamp})")
    void add(@NotNull SessionDTO entity);

    @Delete("DELETE FROM `app_session`")
    void clear();

    @NotNull
    @Select("SELECT * FROM `app_session`")
    @Result(column = "id", property = "id")
    @Result(column = "user_id", property = "userId")
    @Result(column = "signature", property = "signature")
    @Result(column = "timestamp", property = "timestamp")
    List<SessionDTO> findAll();

    @NotNull
    @Select("SELECT * FROM `app_session` WHERE `id` = #{id} LIMIT 1")
    @Result(column = "id", property = "id")
    @Result(column = "user_id", property = "userId")
    @Result(column = "signature", property = "signature")
    @Result(column = "timestamp", property = "timestamp")
    Optional<SessionDTO> findById(@Nullable String id);

    @Delete("DELETE FROM `app_session` WHERE `id` = #{id}")
    void removeById(@Param("id") @Nullable String id);

}

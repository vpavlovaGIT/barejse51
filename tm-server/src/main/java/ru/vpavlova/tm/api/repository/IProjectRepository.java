package ru.vpavlova.tm.api.repository;

import lombok.SneakyThrows;
import org.apache.ibatis.annotations.*;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.vpavlova.tm.api.IRepository;
import ru.vpavlova.tm.dto.ProjectDTO;
import ru.vpavlova.tm.enumerated.Status;

import java.util.List;
import java.util.Optional;

public interface IProjectRepository extends IRepository<ProjectDTO> {

    @Insert("INSERT INTO `app_project` " +
            "(`id`, `name`, `description`, `user_id`, `created`, `date_start`, `date_finish`, `status`) " +
            "VALUES(" +
            "#{id}, #{name}, #{description}, #{userId}, #{created}, #{dateStart}, #{dateFinish}, #{status})")
    void add(@NotNull ProjectDTO project);

    @SneakyThrows
    @Update("UPDATE `app_project` SET `status` = #{status} " +
            "WHERE `id` = #{id} AND `user_id` = #{userId} LIMIT 1")
    void changeStatusById(
            @Param("userId") @Nullable String userId,
            @Param("id") @Nullable String id,
            @Param("status") @Nullable Status status);

    @Delete("DELETE FROM `app_project`")
    void clear();

    @NotNull
    @Select("SELECT * FROM `app_project`")
    @Result(column = "id", property = "id")
    @Result(column = "name", property = "name")
    @Result(column = "description", property = "description")
    @Result(column = "date_start", property = "dateStart")
    @Result(column = "date_finish", property = "dateFinish")
    @Result(column = "user_id", property = "userId")
    @Result(column = "status", property = "status")
    List<ProjectDTO> findAll();

    @NotNull
    @Select("SELECT * FROM `app_project` WHERE `id` = #{id} LIMIT 1")
    @Result(column = "id", property = "id")
    @Result(column = "name", property = "name")
    @Result(column = "description", property = "description")
    @Result(column = "date_start", property = "dateStart")
    @Result(column = "date_finish", property = "dateFinish")
    @Result(column = "user_id", property = "userId")
    @Result(column = "status", property = "status")
    Optional<ProjectDTO> findById(@Param("id") @Nullable String id);

    @NotNull
    @Select("SELECT * FROM `app_project` WHERE `user_id` = #{userId} LIMIT #{index}, 1")
    @Result(column = "id", property = "id")
    @Result(column = "name", property = "name")
    @Result(column = "description", property = "description")
    @Result(column = "date_start", property = "dateStart")
    @Result(column = "date_finish", property = "dateFinish")
    @Result(column = "user_id", property = "userId")
    @Result(column = "status", property = "status")
    Optional<ProjectDTO> findByIndex(
            @Param("userId") @Nullable String userId, @Param("index") @NotNull Integer index
    );

    @NotNull
    @Select("SELECT * FROM `app_project` WHERE `name` = #{name} AND `user_id` = #{userId} LIMIT 1")
    @Result(column = "id", property = "id")
    @Result(column = "name", property = "name")
    @Result(column = "description", property = "description")
    @Result(column = "date_start", property = "dateStart")
    @Result(column = "date_finish", property = "dateFinish")
    @Result(column = "user_id", property = "userId")
    @Result(column = "status", property = "status")
    Optional<ProjectDTO> findByName(
            @Param("userId") @Nullable String userId, @Param("name") @NotNull String name
    );

    @NotNull
    @Select("SELECT * FROM `app_project` WHERE `id` = #{id} AND `user_id` = #{userId} LIMIT 1")
    @Result(column = "id", property = "id")
    @Result(column = "name", property = "name")
    @Result(column = "description", property = "description")
    @Result(column = "date_start", property = "dateStart")
    @Result(column = "date_finish", property = "dateFinish")
    @Result(column = "user_id", property = "userId")
    @Result(column = "status", property = "status")
    Optional<ProjectDTO> findOneByIdAndUserId(
            @Param("userId") @Nullable String userId, @Param("id") @NotNull String id
    );

    @Delete("DELETE FROM `app_project` WHERE `id` = #{id}")
    void removeById(@Param("id") @Nullable String id);

    @Delete("DELETE FROM `app_project` WHERE `name` = #{name} AND `user_id` = #{userId} LIMIT 1")
    void removeByName(
            @Param("userId") @Nullable String userId, @Param("name") @NotNull String name
    );

    @Delete("DELETE FROM `app_project` WHERE `id` = #{id} AND `user_id` = #{userId} LIMIT 1")
    void removeOneByIdAndUserId(@Param("userId") @Nullable String userId, @Param("id") @NotNull String id);

    @SneakyThrows
    @Update("UPDATE `app_project` SET `name` = #{name}, `description` = #{description}" +
            " WHERE `id` = #{id} AND `user_id` = #{userId} LIMIT 1")
    void updateById(
            @Param("userId") @Nullable String userId,
            @Param("id") @Nullable String id,
            @Param("name") @Nullable String name,
            @Param("description") @Nullable String description
    );

}

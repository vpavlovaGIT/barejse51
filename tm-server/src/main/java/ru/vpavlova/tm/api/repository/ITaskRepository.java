package ru.vpavlova.tm.api.repository;

import lombok.SneakyThrows;
import org.apache.ibatis.annotations.*;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.vpavlova.tm.api.IRepository;
import ru.vpavlova.tm.entity.Task;
import ru.vpavlova.tm.enumerated.Status;

import java.util.List;
import java.util.Optional;

public interface ITaskRepository extends IRepository<Task> {

    @Insert("INSERT INTO `app_task` (`id`, `name`, `description`, `user_id`, " +
            "`created`, `date_start`, `date_finish`, `status`, `project_id`) " +
            "VALUES(#{id}, #{name}, #{description}, #{userId}, " +
            "#{created}, #{dateStart}, #{dateFinish}, #{status}, #{projectId})")
    void add(@NotNull Task task);

    @Update("UPDATE `app_task` SET `project_id` = #{projectId} WHERE `user_id` = #{userId} AND `id` = #{taskId}")
    void bindTaskByProject(
            @Param("userId") @NotNull String userId,
            @Param("projectId") @NotNull String projectId,
            @Param("taskId") @NotNull String taskId
    );

    @SneakyThrows
    @Update("UPDATE `app_task` SET `status` = #{status} " +
            "WHERE `id` = #{id} AND `user_id` = #{userId} LIMIT 1")
    void changeStatusById(
            @Param("userId") @Nullable String userId,
            @Param("id") @Nullable String id,
            @Param("status") @Nullable Status status);

    @Delete("DELETE FROM `app_task`")
    void clear();

    @Delete("DELETE FROM `app_task` WHERE `user_id` = #{userId}")
    void clearByUserId(@Param("userId") @NotNull String userId);

    @NotNull
    @Select("SELECT * FROM `app_task` WHERE `id` = #{id} AND `user_id` = #{userId} LIMIT 1")
    @Result(column = "id", property = "id")
    @Result(column = "name", property = "name")
    @Result(column = "description", property = "description")
    @Result(column = "date_start", property = "dateStart")
    @Result(column = "date_finish", property = "dateFinish")
    @Result(column = "user_id", property = "userId")
    @Result(column = "project_id", property = "projectId")
    @Result(column = "status", property = "status")
    Optional<Task> findOneByIdAndUserId(
            @Param("userId") @Nullable String userId, @Param("id") @NotNull String id
    );

    @Delete("DELETE FROM `app_task` WHERE `id` = #{id} AND `user_id` = #{userId} LIMIT 1")
    void removeOneByIdAndUserId(@Param("userId") @Nullable String userId, @Param("id") @NotNull String id);

    @NotNull
    @Select("SELECT * FROM `app_task`")
    @Result(column = "id", property = "id")
    @Result(column = "name", property = "name")
    @Result(column = "description", property = "description")
    @Result(column = "date_start", property = "dateStart")
    @Result(column = "date_finish", property = "dateFinish")
    @Result(column = "user_id", property = "userId")
    @Result(column = "project_id", property = "projectId")
    @Result(column = "status", property = "status")
    List<Task> findAll();

    @NotNull
    @Select("SELECT * FROM `app_task` WHERE `user_id` = #{userId} AND `project_id` = #{projectId}")
    @Result(column = "id", property = "id")
    @Result(column = "name", property = "name")
    @Result(column = "description", property = "description")
    @Result(column = "date_start", property = "dateStart")
    @Result(column = "date_finish", property = "dateFinish")
    @Result(column = "user_id", property = "userId")
    @Result(column = "project_id", property = "projectId")
    @Result(column = "status", property = "status")
    List<Task> findAllByProjectId(
            @Param("userId") @NotNull String userId,
            @Param("projectId") @NotNull String projectId
    );

    @NotNull
    @Select("SELECT * FROM `app_task` WHERE `id` = #{id} LIMIT 1")
    @Result(column = "id", property = "id")
    @Result(column = "name", property = "name")
    @Result(column = "description", property = "description")
    @Result(column = "date_start", property = "dateStart")
    @Result(column = "date_finish", property = "dateFinish")
    @Result(column = "user_id", property = "userId")
    @Result(column = "project_id", property = "projectId")
    @Result(column = "status", property = "status")
    Optional<Task> findById(@Param("id") @Nullable String id);

    @NotNull
    @Select("SELECT * FROM `app_task` WHERE `user_id` = #{userId} LIMIT #{index}, 1")
    @Result(column = "id", property = "id")
    @Result(column = "name", property = "name")
    @Result(column = "description", property = "description")
    @Result(column = "date_start", property = "dateStart")
    @Result(column = "date_finish", property = "dateFinish")
    @Result(column = "user_id", property = "userId")
    @Result(column = "project_id", property = "projectId")
    @Result(column = "status", property = "status")
    Optional<Task> findByIndex(
            @Param("userId") @Nullable String userId, @Param("index") @NotNull Integer index
    );

    @NotNull
    @Select("SELECT * FROM `app_task` WHERE `name` = #{name} AND `user_id` = #{userId} LIMIT 1")
    @Result(column = "id", property = "id")
    @Result(column = "name", property = "name")
    @Result(column = "description", property = "description")
    @Result(column = "date_start", property = "dateStart")
    @Result(column = "date_finish", property = "dateFinish")
    @Result(column = "user_id", property = "userId")
    @Result(column = "project_id", property = "projectId")
    @Result(column = "status", property = "status")
    Optional<Task> findByName(
            @Param("userId") @Nullable String userId, @Param("name") @NotNull String name
    );

    @Delete("DELETE FROM `app_task` WHERE `user_id` = #{userId} AND `project_id` = #{projectId}")
    void removeAllByProjectId(
            @Param("userId") @NotNull String userId,
            @Param("projectId") @NotNull String projectId
    );

    @Delete("DELETE FROM `app_task` WHERE `id` = #{id}")
    void removeById(@Param("id") @Nullable String id);

    @Delete("DELETE FROM `app_task` WHERE `name` = #{name} AND `user_id` = #{userId} LIMIT 1")
    void removeByName(
            @Param("userId") @Nullable String userId, @Param("name") @NotNull String name
    );

    @Update("UPDATE `app_task` SET `project_id` = NULL WHERE `user_id` = #{userId} AND `id` = #{taskId}")
    void unbindTaskFromProject(
            @Param("userId") @NotNull String userId,
            @Param("taskId") @NotNull String taskId
    );

    @SneakyThrows
    @Update("UPDATE `app_task` SET `name` = #{name}, `description` = #{description}" +
            " WHERE `id` = #{id} AND `user_id` = #{userId} LIMIT 1")
    void updateById(
            @Param("userId") @Nullable String userId,
            @Param("id") @Nullable String id,
            @Param("name") @Nullable String name,
            @Param("description") @Nullable String description
    );

}

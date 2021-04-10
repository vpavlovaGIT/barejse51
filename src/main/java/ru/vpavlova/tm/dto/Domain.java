package ru.vpavlova.tm.dto;

import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.Nullable;
import ru.vpavlova.tm.entity.Project;
import ru.vpavlova.tm.entity.Task;
import ru.vpavlova.tm.entity.User;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
public class Domain implements Serializable {

    @Nullable private List<Project> projects;

    @Nullable private List<Task> tasks;

    @Nullable private List<User> users;

}

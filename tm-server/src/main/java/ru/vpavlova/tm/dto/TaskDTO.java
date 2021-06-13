package ru.vpavlova.tm.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.vpavlova.tm.api.entity.IWBS;

import javax.persistence.*;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "app_task")
public class TaskDTO extends AbstractBusinessEntityDTO implements IWBS {

    @Column
    @Nullable
    private String projectId;

    public TaskDTO(@NotNull String name) {
        this.name = name;
    }

}

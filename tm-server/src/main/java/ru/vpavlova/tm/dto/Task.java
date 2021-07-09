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
public class Task extends AbstractBusinessEntity implements IWBS {

    @Nullable
    @Column(name = "project_id")
    private String projectId;

    public Task(@Nullable final String name, @Nullable final String description) {
        this.name = name;
        this.description = description;
    }

}

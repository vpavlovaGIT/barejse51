package ru.vpavlova.tm.dto;

import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import ru.vpavlova.tm.api.entity.IWBS;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Table(name = "app_project")
public class ProjectDTO extends AbstractBusinessEntityDTO implements IWBS {

    public ProjectDTO(@NotNull final String name, @NotNull final String description) {
        this.name = name;
        this.description = description;
    }

}

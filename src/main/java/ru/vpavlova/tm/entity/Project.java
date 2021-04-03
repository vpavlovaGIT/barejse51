package ru.vpavlova.tm.entity;

import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import ru.vpavlova.tm.api.entity.IWBS;

@NoArgsConstructor
public class Project extends AbstractBusinessEntity implements IWBS {

    public Project(@NotNull final String name, @NotNull final String description) {
        this.name = name;
        this.description = description;
    }

}

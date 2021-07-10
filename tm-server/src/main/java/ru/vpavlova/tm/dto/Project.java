package ru.vpavlova.tm.dto;

import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.jetbrains.annotations.NotNull;
import ru.vpavlova.tm.api.entity.IWBS;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Table(name = "app_project")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Project extends AbstractBusinessEntity implements IWBS {

    public Project(@NotNull final String name, @NotNull final String description) {
        this.name = name;
        this.description = description;
    }

}

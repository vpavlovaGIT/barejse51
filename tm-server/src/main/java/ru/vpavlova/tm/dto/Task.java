package ru.vpavlova.tm.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.jetbrains.annotations.Nullable;
import ru.vpavlova.tm.api.entity.IWBS;
import ru.vpavlova.tm.listener.LoggerEntityListener;

import javax.persistence.*;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "app_task")
@EntityListeners(LoggerEntityListener.class)
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Task extends AbstractBusinessEntity implements IWBS {

    @Nullable
    @Column(name = "project_id")
    private String projectId;

    public Task(@Nullable final String name, @Nullable final String description) {
        this.name = name;
        this.description = description;
    }

}

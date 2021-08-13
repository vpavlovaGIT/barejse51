package ru.vpavlova.tm.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.jetbrains.annotations.Nullable;
import ru.vpavlova.tm.api.entity.IWBS;
import ru.vpavlova.tm.listener.LoggerEntityListener;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "app_project")
@EntityListeners(LoggerEntityListener.class)
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class ProjectGraph extends AbstractBusinessGraphEntity implements IWBS {

    @Nullable
    @JsonIgnore
    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TaskGraph> tasks = new ArrayList<>();

}

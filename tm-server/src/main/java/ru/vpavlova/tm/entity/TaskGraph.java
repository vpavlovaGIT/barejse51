package ru.vpavlova.tm.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
public class TaskGraph extends AbstractBusinessGraphEntity  implements IWBS {

    @Nullable
    @ManyToOne
    @JsonIgnore
    private ProjectGraph project;

}

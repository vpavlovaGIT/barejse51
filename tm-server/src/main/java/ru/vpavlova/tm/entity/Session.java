package ru.vpavlova.tm.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jetbrains.annotations.Nullable;

import javax.persistence.*;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "app_session")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Session extends AbstractEntity implements Cloneable {

    @Column
    @Nullable
    Long timestamp;

    @Column
    @Nullable
    String signature;

    @Nullable
    @ManyToOne
    private User user;

}

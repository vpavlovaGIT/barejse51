package ru.vpavlova.tm.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.Nullable;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "app_session")
@JsonIgnoreProperties(ignoreUnknown = true)
public class SessionDTO extends AbstractBusinessEntityDTO implements Cloneable {

    @Override
    public SessionDTO clone() {
        try {
            return (SessionDTO) super.clone();
        } catch (CloneNotSupportedException e) {
            return null;
        }
    }

    @Column
    @Nullable
    private Long timestamp;

    @Column
    @Nullable
    String userId;

    @Column
    @Nullable
    String signature;

}

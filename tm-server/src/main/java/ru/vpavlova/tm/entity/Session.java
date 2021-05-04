package ru.vpavlova.tm.entity;

import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.Nullable;

@Getter
@Setter
public final class Session extends AbstractEntity implements Cloneable {

    @Override
    public Session clone() {
        try {
            return (Session) super.clone();
        } catch (CloneNotSupportedException e) {
            return null;
        }
    }

    @Nullable Long timestamp;

    @Nullable String userId;

    @Nullable String signature;

    public Session() {
    }

}

package ru.vpavlova.tm.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.vpavlova.tm.api.entity.IWBS;

@Getter
@Setter
@NoArgsConstructor
public class Task extends AbstractBusinessEntity implements IWBS {

    @Nullable
    private String projectId;

    public Task(@NotNull String name) {
        this.name = name;
    }

}

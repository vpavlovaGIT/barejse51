package ru.vpavlova.tm.listener;

import org.jetbrains.annotations.NotNull;
import ru.vpavlova.tm.enumerated.OperationType;
import ru.vpavlova.tm.service.ActiveMQConnectionService;

import javax.persistence.*;

import static ru.vpavlova.tm.enumerated.OperationType.*;

public class LoggerEntityListener {

    @PostLoad
    public void onPostLoad(@NotNull final Object entity) {
        sendMessage(entity, LOAD);
    }

    @PrePersist
    public void onPrePersist(@NotNull final Object entity) {
        sendMessage(entity, START_PERSIST);
    }

    @PostPersist
    public void onPostPersist(@NotNull final Object entity) {
        sendMessage(entity, FINISH_PERSIST);
    }

    @PreUpdate
    public void onPreUpdate(@NotNull final Object entity) {
        sendMessage(entity, START_UPDATE);
    }

    @PostUpdate
    public void onPostUpdate(@NotNull final Object entity) {
        sendMessage(entity, FINISH_UPDATE);
    }

    @PreRemove
    public void onPreRemove(@NotNull final Object entity) {
        sendMessage(entity, START_REMOVE);
    }

    @PostRemove
    public void onPostRemove(@NotNull final Object entity) {
        sendMessage(entity, FINISH_REMOVE);
    }

    public void sendMessage(
            @NotNull final Object entity, @NotNull final OperationType operation
    ) {
        if (ActiveMQConnectionService.getInstance() == null) return;
        ActiveMQConnectionService.getInstance().getMessageService().sendMessage(entity, operation);
    }

}

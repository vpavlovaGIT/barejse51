package ru.vpavlova.tm;

import lombok.SneakyThrows;
import org.jetbrains.annotations.NotNull;
import ru.vpavlova.tm.api.service.IReceiverService;
import ru.vpavlova.tm.listener.LogMessageListener;
import ru.vpavlova.tm.service.ActiveMQConnectionService;

public class Application {

    @SneakyThrows
    public static void main(final String[] args) {
        @NotNull final IReceiverService receiverService = new ActiveMQConnectionService();
        receiverService.receive(new LogMessageListener());
    }

}
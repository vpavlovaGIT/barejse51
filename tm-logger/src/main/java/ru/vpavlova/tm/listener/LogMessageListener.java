package ru.vpavlova.tm.listener;

import lombok.AllArgsConstructor;
import org.jetbrains.annotations.NotNull;
import ru.vpavlova.tm.api.service.ILoggingService;
import ru.vpavlova.tm.service.LoggingService;

import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

@AllArgsConstructor
public class LogMessageListener implements MessageListener {

    @NotNull
    final ILoggingService loggingService = new LoggingService();

    @Override
    public void onMessage(Message message) {
        if (message instanceof ObjectMessage) {
            loggingService.writeLog(message);
        }
    }

}

package ru.vpavlova.tm.service;

import lombok.SneakyThrows;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.vpavlova.tm.api.service.ILoggingService;
import ru.vpavlova.tm.dto.Logger;

import javax.jms.Message;
import javax.jms.ObjectMessage;
import java.io.File;
import java.io.FileWriter;

import static ru.vpavlova.tm.constant.LogFileName.*;

public class LoggingService implements ILoggingService {

    @Override
    @SneakyThrows
    public void writeLog(final Message message) {
        @NotNull final ObjectMessage objectMessage = (ObjectMessage) message;
        final Logger logger = (Logger) objectMessage.getObject();
        @Nullable final String className = logger.getClassName();
        @Nullable final String fileName = getFileName(className);
        if (fileName == null) return;
        @NotNull File file = new File(fileName);
        file.getParentFile().mkdirs();
        @NotNull final FileWriter fileOutputStream = new FileWriter(file, true);
        @NotNull String log = logger.getId() + " : " +
                logger.getOperation() + " : " +
                logger.getCreated() + "\n" +
                logger.getClassName() + "\n" +
                logger.getEntity() + "\n";
        System.out.println(log);
        fileOutputStream.write(log);
        fileOutputStream.close();
    }

    @Nullable
    private String getFileName(@Nullable final String className) {
        if (className == null) return null;
        switch (className) {
            case "Session":
            case "SessionGraph":
                return SESSION_LOG;
            case "User":
            case "UserGraph":
                return USER_LOG;
            case "Task":
            case "TaskGraph":
                return TASK_LOG;
            case "Project":
            case "ProjectGraph":
                return PROJECT_LOG;
            default:
                return null;
        }
    }

}

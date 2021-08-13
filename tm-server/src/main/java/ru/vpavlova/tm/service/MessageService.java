package ru.vpavlova.tm.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.jetbrains.annotations.NotNull;
import ru.vpavlova.tm.api.service.ServiceLocator;
import ru.vpavlova.tm.dto.Logger;
import ru.vpavlova.tm.enumerated.OperationType;

import javax.jms.*;

import static ru.vpavlova.tm.service.ActiveMQConnectionService.JMS_LOGGER_TOPIC;

public class MessageService {

    private final ServiceLocator serviceLocator;

    public MessageService(ServiceLocator serviceLocator) {
        this.serviceLocator = serviceLocator;
    }

    @NotNull
    @SneakyThrows
    private Logger getLogger(@NotNull final Object entity, @NotNull final OperationType operation) {
        @NotNull final ObjectMapper mapper = new ObjectMapper();
        @NotNull final String className = entity.getClass().getSimpleName();
        @NotNull final String json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(entity);

        final Logger logger = new Logger();
        logger.setOperation(operation);
        logger.setEntity(json);
        logger.setClassName(className);

        return logger;
    }

    @SneakyThrows
    public void sendMessage(
            @NotNull final Object entity,
            @NotNull final OperationType operation
    ) {
        @NotNull final Logger logger = getLogger(entity, operation);
        @NotNull final Connection connection = ActiveMQConnectionService.getInstance().getConnection();
        @NotNull final Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        @NotNull final Destination destination = session.createTopic(JMS_LOGGER_TOPIC);
        @NotNull final MessageProducer producer = session.createProducer(destination);
        @NotNull final ObjectMessage objectMessage = session.createObjectMessage(logger);
        producer.send(objectMessage);
        producer.close();
        session.close();
    }

}

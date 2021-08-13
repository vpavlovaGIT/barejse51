package ru.vpavlova.tm.service;

import lombok.Getter;
import lombok.SneakyThrows;
import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.jetbrains.annotations.NotNull;
import ru.vpavlova.tm.api.service.IActiveMQConnectionService;
import ru.vpavlova.tm.api.service.ServiceLocator;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;

@Getter
public class ActiveMQConnectionService implements IActiveMQConnectionService {

    private static ActiveMQConnectionService instance;

    @NotNull
    private final ServiceLocator serviceLocator;

    @NotNull
    private static final String JMS_BROKER_URL = ActiveMQConnection.DEFAULT_BROKER_URL;

    @NotNull
    public static final String JMS_LOGGER_TOPIC = "JCG_TOPIC";

    @NotNull
    private final MessageService messageService;

    @NotNull
    private final ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(JMS_BROKER_URL);

    @NotNull
    private final Connection connection;

    @SneakyThrows
    public ActiveMQConnectionService(@NotNull final ServiceLocator serviceLocator) {
        this.serviceLocator = serviceLocator;
        messageService = new MessageService(serviceLocator);
        connection = connectionFactory.createConnection();
        connection.start();
        instance = this;
    }

    @Override
    @SneakyThrows
    public void shutDown() {
        connection.close();
    }

    public static ActiveMQConnectionService getInstance() {
        return instance;
    }

}

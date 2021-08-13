package ru.vpavlova.tm.service;

import lombok.SneakyThrows;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.jetbrains.annotations.NotNull;
import ru.vpavlova.tm.api.service.IReceiverService;

import javax.jms.*;

import static ru.vpavlova.tm.constant.ActiveMQConst.*;

public class ActiveMQConnectionService implements IReceiverService {

    @NotNull
    private final ConnectionFactory connectionFactory;

    public ActiveMQConnectionService() {
        connectionFactory = new ActiveMQConnectionFactory(URL);
    }

    @Override
    @SneakyThrows
    public void receive(@NotNull final MessageListener listener) {
        @NotNull final Connection connection = connectionFactory.createConnection();
        connection.start();
        System.out.println("Connection success...");
        @NotNull final Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        @NotNull final Destination destination = session.createTopic(STRING);
        final MessageConsumer consumer = session.createConsumer(destination);
        consumer.setMessageListener(listener);
    }

}

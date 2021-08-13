package ru.vpavlova.tm.api.service;

import javax.jms.MessageListener;

public interface IReceiverService {

    void receive(MessageListener listener);

}

package ru.vpavlova.tm.api.service;

import javax.jms.Message;

public interface ILoggingService {

    void writeLog(Message objectMessage);

}

package ru.vpavlova.tm.endpoint;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.Action;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

/**
 * This class was generated by Apache CXF 3.4.2
 * 2021-05-07T20:35:09.377+03:00
 * Generated source version: 3.4.2
 *
 */
@WebService(targetNamespace = "http://endpoint.tm.vpavlova.ru/", name = "TaskEndpoint")
@XmlSeeAlso({ObjectFactory.class})
public interface TaskEndpoint {

    @WebMethod
    @Action(input = "http://endpoint.tm.vpavlova.ru/TaskEndpoint/findAllTasksRequest", output = "http://endpoint.tm.vpavlova.ru/TaskEndpoint/findAllTasksResponse")
    @RequestWrapper(localName = "findAllTasks", targetNamespace = "http://endpoint.tm.vpavlova.ru/", className = "ru.vpavlova.tm.endpoint.FindAllTasks")
    @ResponseWrapper(localName = "findAllTasksResponse", targetNamespace = "http://endpoint.tm.vpavlova.ru/", className = "ru.vpavlova.tm.endpoint.FindAllTasksResponse")
    @WebResult(name = "return", targetNamespace = "")
    public java.util.List<ru.vpavlova.tm.endpoint.Task> findAllTasks(

        @WebParam(name = "session", targetNamespace = "")
        ru.vpavlova.tm.endpoint.Session session
    );

    @WebMethod
    @Action(input = "http://endpoint.tm.vpavlova.ru/TaskEndpoint/removeTaskByIdRequest", output = "http://endpoint.tm.vpavlova.ru/TaskEndpoint/removeTaskByIdResponse")
    @RequestWrapper(localName = "removeTaskById", targetNamespace = "http://endpoint.tm.vpavlova.ru/", className = "ru.vpavlova.tm.endpoint.RemoveTaskById")
    @ResponseWrapper(localName = "removeTaskByIdResponse", targetNamespace = "http://endpoint.tm.vpavlova.ru/", className = "ru.vpavlova.tm.endpoint.RemoveTaskByIdResponse")
    public void removeTaskById(

        @WebParam(name = "id", targetNamespace = "")
        java.lang.String id,
        @WebParam(name = "session", targetNamespace = "")
        ru.vpavlova.tm.endpoint.Session session
    );

    @WebMethod
    @Action(input = "http://endpoint.tm.vpavlova.ru/TaskEndpoint/findTaskByIdRequest", output = "http://endpoint.tm.vpavlova.ru/TaskEndpoint/findTaskByIdResponse")
    @RequestWrapper(localName = "findTaskById", targetNamespace = "http://endpoint.tm.vpavlova.ru/", className = "ru.vpavlova.tm.endpoint.FindTaskById")
    @ResponseWrapper(localName = "findTaskByIdResponse", targetNamespace = "http://endpoint.tm.vpavlova.ru/", className = "ru.vpavlova.tm.endpoint.FindTaskByIdResponse")
    @WebResult(name = "return", targetNamespace = "")
    public ru.vpavlova.tm.endpoint.Task findTaskById(

        @WebParam(name = "id", targetNamespace = "")
        java.lang.String id,
        @WebParam(name = "session", targetNamespace = "")
        ru.vpavlova.tm.endpoint.Session session
    );

    @WebMethod
    @Action(input = "http://endpoint.tm.vpavlova.ru/TaskEndpoint/clearTasksRequest", output = "http://endpoint.tm.vpavlova.ru/TaskEndpoint/clearTasksResponse")
    @RequestWrapper(localName = "clearTasks", targetNamespace = "http://endpoint.tm.vpavlova.ru/", className = "ru.vpavlova.tm.endpoint.ClearTasks")
    @ResponseWrapper(localName = "clearTasksResponse", targetNamespace = "http://endpoint.tm.vpavlova.ru/", className = "ru.vpavlova.tm.endpoint.ClearTasksResponse")
    public void clearTasks(

        @WebParam(name = "session", targetNamespace = "")
        ru.vpavlova.tm.endpoint.Session session
    );
}

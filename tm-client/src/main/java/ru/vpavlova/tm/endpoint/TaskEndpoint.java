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
 * 2021-06-07T17:56:00.316+03:00
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
    @Action(input = "http://endpoint.tm.vpavlova.ru/TaskEndpoint/finishTaskByIdRequest", output = "http://endpoint.tm.vpavlova.ru/TaskEndpoint/finishTaskByIdResponse")
    @RequestWrapper(localName = "finishTaskById", targetNamespace = "http://endpoint.tm.vpavlova.ru/", className = "ru.vpavlova.tm.endpoint.FinishTaskById")
    @ResponseWrapper(localName = "finishTaskByIdResponse", targetNamespace = "http://endpoint.tm.vpavlova.ru/", className = "ru.vpavlova.tm.endpoint.FinishTaskByIdResponse")
    public void finishTaskById(

        @WebParam(name = "session", targetNamespace = "")
        ru.vpavlova.tm.endpoint.Session session,
        @WebParam(name = "id", targetNamespace = "")
        java.lang.String id
    );

    @WebMethod
    @Action(input = "http://endpoint.tm.vpavlova.ru/TaskEndpoint/updateTaskByIdRequest", output = "http://endpoint.tm.vpavlova.ru/TaskEndpoint/updateTaskByIdResponse")
    @RequestWrapper(localName = "updateTaskById", targetNamespace = "http://endpoint.tm.vpavlova.ru/", className = "ru.vpavlova.tm.endpoint.UpdateTaskById")
    @ResponseWrapper(localName = "updateTaskByIdResponse", targetNamespace = "http://endpoint.tm.vpavlova.ru/", className = "ru.vpavlova.tm.endpoint.UpdateTaskByIdResponse")
    public void updateTaskById(

        @WebParam(name = "session", targetNamespace = "")
        ru.vpavlova.tm.endpoint.Session session,
        @WebParam(name = "id", targetNamespace = "")
        java.lang.String id,
        @WebParam(name = "name", targetNamespace = "")
        java.lang.String name,
        @WebParam(name = "description", targetNamespace = "")
        java.lang.String description
    );

    @WebMethod
    @Action(input = "http://endpoint.tm.vpavlova.ru/TaskEndpoint/updateTaskByIndexRequest", output = "http://endpoint.tm.vpavlova.ru/TaskEndpoint/updateTaskByIndexResponse")
    @RequestWrapper(localName = "updateTaskByIndex", targetNamespace = "http://endpoint.tm.vpavlova.ru/", className = "ru.vpavlova.tm.endpoint.UpdateTaskByIndex")
    @ResponseWrapper(localName = "updateTaskByIndexResponse", targetNamespace = "http://endpoint.tm.vpavlova.ru/", className = "ru.vpavlova.tm.endpoint.UpdateTaskByIndexResponse")
    public void updateTaskByIndex(

        @WebParam(name = "session", targetNamespace = "")
        ru.vpavlova.tm.endpoint.Session session,
        @WebParam(name = "index", targetNamespace = "")
        java.lang.Integer index,
        @WebParam(name = "name", targetNamespace = "")
        java.lang.String name,
        @WebParam(name = "description", targetNamespace = "")
        java.lang.String description
    );

    @WebMethod
    @Action(input = "http://endpoint.tm.vpavlova.ru/TaskEndpoint/startTaskByIdRequest", output = "http://endpoint.tm.vpavlova.ru/TaskEndpoint/startTaskByIdResponse")
    @RequestWrapper(localName = "startTaskById", targetNamespace = "http://endpoint.tm.vpavlova.ru/", className = "ru.vpavlova.tm.endpoint.StartTaskById")
    @ResponseWrapper(localName = "startTaskByIdResponse", targetNamespace = "http://endpoint.tm.vpavlova.ru/", className = "ru.vpavlova.tm.endpoint.StartTaskByIdResponse")
    public void startTaskById(

        @WebParam(name = "session", targetNamespace = "")
        ru.vpavlova.tm.endpoint.Session session,
        @WebParam(name = "id", targetNamespace = "")
        java.lang.String id
    );

    @WebMethod
    @Action(input = "http://endpoint.tm.vpavlova.ru/TaskEndpoint/startTaskByNameRequest", output = "http://endpoint.tm.vpavlova.ru/TaskEndpoint/startTaskByNameResponse")
    @RequestWrapper(localName = "startTaskByName", targetNamespace = "http://endpoint.tm.vpavlova.ru/", className = "ru.vpavlova.tm.endpoint.StartTaskByName")
    @ResponseWrapper(localName = "startTaskByNameResponse", targetNamespace = "http://endpoint.tm.vpavlova.ru/", className = "ru.vpavlova.tm.endpoint.StartTaskByNameResponse")
    public void startTaskByName(

        @WebParam(name = "session", targetNamespace = "")
        ru.vpavlova.tm.endpoint.Session session,
        @WebParam(name = "name", targetNamespace = "")
        java.lang.String name
    );

    @WebMethod
    @Action(input = "http://endpoint.tm.vpavlova.ru/TaskEndpoint/removeTaskByIdRequest", output = "http://endpoint.tm.vpavlova.ru/TaskEndpoint/removeTaskByIdResponse")
    @RequestWrapper(localName = "removeTaskById", targetNamespace = "http://endpoint.tm.vpavlova.ru/", className = "ru.vpavlova.tm.endpoint.RemoveTaskById")
    @ResponseWrapper(localName = "removeTaskByIdResponse", targetNamespace = "http://endpoint.tm.vpavlova.ru/", className = "ru.vpavlova.tm.endpoint.RemoveTaskByIdResponse")
    public void removeTaskById(

        @WebParam(name = "session", targetNamespace = "")
        ru.vpavlova.tm.endpoint.Session session,
        @WebParam(name = "id", targetNamespace = "")
        java.lang.String id
    );

    @WebMethod
    @Action(input = "http://endpoint.tm.vpavlova.ru/TaskEndpoint/findTaskOneByIndexRequest", output = "http://endpoint.tm.vpavlova.ru/TaskEndpoint/findTaskOneByIndexResponse")
    @RequestWrapper(localName = "findTaskOneByIndex", targetNamespace = "http://endpoint.tm.vpavlova.ru/", className = "ru.vpavlova.tm.endpoint.FindTaskOneByIndex")
    @ResponseWrapper(localName = "findTaskOneByIndexResponse", targetNamespace = "http://endpoint.tm.vpavlova.ru/", className = "ru.vpavlova.tm.endpoint.FindTaskOneByIndexResponse")
    @WebResult(name = "return", targetNamespace = "")
    public ru.vpavlova.tm.endpoint.Task findTaskOneByIndex(

        @WebParam(name = "session", targetNamespace = "")
        ru.vpavlova.tm.endpoint.Session session,
        @WebParam(name = "index", targetNamespace = "")
        java.lang.Integer index
    );

    @WebMethod
    @Action(input = "http://endpoint.tm.vpavlova.ru/TaskEndpoint/findTaskOneByNameRequest", output = "http://endpoint.tm.vpavlova.ru/TaskEndpoint/findTaskOneByNameResponse")
    @RequestWrapper(localName = "findTaskOneByName", targetNamespace = "http://endpoint.tm.vpavlova.ru/", className = "ru.vpavlova.tm.endpoint.FindTaskOneByName")
    @ResponseWrapper(localName = "findTaskOneByNameResponse", targetNamespace = "http://endpoint.tm.vpavlova.ru/", className = "ru.vpavlova.tm.endpoint.FindTaskOneByNameResponse")
    @WebResult(name = "return", targetNamespace = "")
    public ru.vpavlova.tm.endpoint.Task findTaskOneByName(

        @WebParam(name = "session", targetNamespace = "")
        ru.vpavlova.tm.endpoint.Session session,
        @WebParam(name = "name", targetNamespace = "")
        java.lang.String name
    );

    @WebMethod
    @Action(input = "http://endpoint.tm.vpavlova.ru/TaskEndpoint/unbindTaskFromProjectRequest", output = "http://endpoint.tm.vpavlova.ru/TaskEndpoint/unbindTaskFromProjectResponse")
    @RequestWrapper(localName = "unbindTaskFromProject", targetNamespace = "http://endpoint.tm.vpavlova.ru/", className = "ru.vpavlova.tm.endpoint.UnbindTaskFromProject")
    @ResponseWrapper(localName = "unbindTaskFromProjectResponse", targetNamespace = "http://endpoint.tm.vpavlova.ru/", className = "ru.vpavlova.tm.endpoint.UnbindTaskFromProjectResponse")
    public void unbindTaskFromProject(

        @WebParam(name = "session", targetNamespace = "")
        ru.vpavlova.tm.endpoint.Session session,
        @WebParam(name = "taskId", targetNamespace = "")
        java.lang.String taskId
    );

    @WebMethod
    @Action(input = "http://endpoint.tm.vpavlova.ru/TaskEndpoint/startTaskByIndexRequest", output = "http://endpoint.tm.vpavlova.ru/TaskEndpoint/startTaskByIndexResponse")
    @RequestWrapper(localName = "startTaskByIndex", targetNamespace = "http://endpoint.tm.vpavlova.ru/", className = "ru.vpavlova.tm.endpoint.StartTaskByIndex")
    @ResponseWrapper(localName = "startTaskByIndexResponse", targetNamespace = "http://endpoint.tm.vpavlova.ru/", className = "ru.vpavlova.tm.endpoint.StartTaskByIndexResponse")
    public void startTaskByIndex(

        @WebParam(name = "session", targetNamespace = "")
        ru.vpavlova.tm.endpoint.Session session,
        @WebParam(name = "index", targetNamespace = "")
        java.lang.Integer index
    );

    @WebMethod
    @Action(input = "http://endpoint.tm.vpavlova.ru/TaskEndpoint/addTaskRequest", output = "http://endpoint.tm.vpavlova.ru/TaskEndpoint/addTaskResponse")
    @RequestWrapper(localName = "addTask", targetNamespace = "http://endpoint.tm.vpavlova.ru/", className = "ru.vpavlova.tm.endpoint.AddTask")
    @ResponseWrapper(localName = "addTaskResponse", targetNamespace = "http://endpoint.tm.vpavlova.ru/", className = "ru.vpavlova.tm.endpoint.AddTaskResponse")
    @WebResult(name = "return", targetNamespace = "")
    public ru.vpavlova.tm.endpoint.Task addTask(

        @WebParam(name = "session", targetNamespace = "")
        ru.vpavlova.tm.endpoint.Session session,
        @WebParam(name = "name", targetNamespace = "")
        java.lang.String name,
        @WebParam(name = "description", targetNamespace = "")
        java.lang.String description
    );

    @WebMethod
    @Action(input = "http://endpoint.tm.vpavlova.ru/TaskEndpoint/clearTasksRequest", output = "http://endpoint.tm.vpavlova.ru/TaskEndpoint/clearTasksResponse")
    @RequestWrapper(localName = "clearTasks", targetNamespace = "http://endpoint.tm.vpavlova.ru/", className = "ru.vpavlova.tm.endpoint.ClearTasks")
    @ResponseWrapper(localName = "clearTasksResponse", targetNamespace = "http://endpoint.tm.vpavlova.ru/", className = "ru.vpavlova.tm.endpoint.ClearTasksResponse")
    public void clearTasks(

        @WebParam(name = "session", targetNamespace = "")
        ru.vpavlova.tm.endpoint.Session session
    );

    @WebMethod
    @Action(input = "http://endpoint.tm.vpavlova.ru/TaskEndpoint/findTaskByIdRequest", output = "http://endpoint.tm.vpavlova.ru/TaskEndpoint/findTaskByIdResponse")
    @RequestWrapper(localName = "findTaskById", targetNamespace = "http://endpoint.tm.vpavlova.ru/", className = "ru.vpavlova.tm.endpoint.FindTaskById")
    @ResponseWrapper(localName = "findTaskByIdResponse", targetNamespace = "http://endpoint.tm.vpavlova.ru/", className = "ru.vpavlova.tm.endpoint.FindTaskByIdResponse")
    @WebResult(name = "return", targetNamespace = "")
    public ru.vpavlova.tm.endpoint.Task findTaskById(

        @WebParam(name = "session", targetNamespace = "")
        ru.vpavlova.tm.endpoint.Session session,
        @WebParam(name = "id", targetNamespace = "")
        java.lang.String id
    );

    @WebMethod
    @Action(input = "http://endpoint.tm.vpavlova.ru/TaskEndpoint/finishTaskByNameRequest", output = "http://endpoint.tm.vpavlova.ru/TaskEndpoint/finishTaskByNameResponse")
    @RequestWrapper(localName = "finishTaskByName", targetNamespace = "http://endpoint.tm.vpavlova.ru/", className = "ru.vpavlova.tm.endpoint.FinishTaskByName")
    @ResponseWrapper(localName = "finishTaskByNameResponse", targetNamespace = "http://endpoint.tm.vpavlova.ru/", className = "ru.vpavlova.tm.endpoint.FinishTaskByNameResponse")
    public void finishTaskByName(

        @WebParam(name = "session", targetNamespace = "")
        ru.vpavlova.tm.endpoint.Session session,
        @WebParam(name = "name", targetNamespace = "")
        java.lang.String name
    );

    @WebMethod
    @Action(input = "http://endpoint.tm.vpavlova.ru/TaskEndpoint/changeTaskStatusByIdRequest", output = "http://endpoint.tm.vpavlova.ru/TaskEndpoint/changeTaskStatusByIdResponse")
    @RequestWrapper(localName = "changeTaskStatusById", targetNamespace = "http://endpoint.tm.vpavlova.ru/", className = "ru.vpavlova.tm.endpoint.ChangeTaskStatusById")
    @ResponseWrapper(localName = "changeTaskStatusByIdResponse", targetNamespace = "http://endpoint.tm.vpavlova.ru/", className = "ru.vpavlova.tm.endpoint.ChangeTaskStatusByIdResponse")
    public void changeTaskStatusById(

        @WebParam(name = "session", targetNamespace = "")
        ru.vpavlova.tm.endpoint.Session session,
        @WebParam(name = "id", targetNamespace = "")
        java.lang.String id,
        @WebParam(name = "status", targetNamespace = "")
        ru.vpavlova.tm.endpoint.Status status
    );

    @WebMethod
    @Action(input = "http://endpoint.tm.vpavlova.ru/TaskEndpoint/finishTaskByIndexRequest", output = "http://endpoint.tm.vpavlova.ru/TaskEndpoint/finishTaskByIndexResponse")
    @RequestWrapper(localName = "finishTaskByIndex", targetNamespace = "http://endpoint.tm.vpavlova.ru/", className = "ru.vpavlova.tm.endpoint.FinishTaskByIndex")
    @ResponseWrapper(localName = "finishTaskByIndexResponse", targetNamespace = "http://endpoint.tm.vpavlova.ru/", className = "ru.vpavlova.tm.endpoint.FinishTaskByIndexResponse")
    public void finishTaskByIndex(

        @WebParam(name = "session", targetNamespace = "")
        ru.vpavlova.tm.endpoint.Session session,
        @WebParam(name = "index", targetNamespace = "")
        java.lang.Integer index
    );

    @WebMethod
    @Action(input = "http://endpoint.tm.vpavlova.ru/TaskEndpoint/changeTaskStatusByIndexRequest", output = "http://endpoint.tm.vpavlova.ru/TaskEndpoint/changeTaskStatusByIndexResponse")
    @RequestWrapper(localName = "changeTaskStatusByIndex", targetNamespace = "http://endpoint.tm.vpavlova.ru/", className = "ru.vpavlova.tm.endpoint.ChangeTaskStatusByIndex")
    @ResponseWrapper(localName = "changeTaskStatusByIndexResponse", targetNamespace = "http://endpoint.tm.vpavlova.ru/", className = "ru.vpavlova.tm.endpoint.ChangeTaskStatusByIndexResponse")
    public void changeTaskStatusByIndex(

        @WebParam(name = "session", targetNamespace = "")
        ru.vpavlova.tm.endpoint.Session session,
        @WebParam(name = "index", targetNamespace = "")
        java.lang.Integer index,
        @WebParam(name = "status", targetNamespace = "")
        ru.vpavlova.tm.endpoint.Status status
    );

    @WebMethod
    @Action(input = "http://endpoint.tm.vpavlova.ru/TaskEndpoint/changeTaskStatusByNameRequest", output = "http://endpoint.tm.vpavlova.ru/TaskEndpoint/changeTaskStatusByNameResponse")
    @RequestWrapper(localName = "changeTaskStatusByName", targetNamespace = "http://endpoint.tm.vpavlova.ru/", className = "ru.vpavlova.tm.endpoint.ChangeTaskStatusByName")
    @ResponseWrapper(localName = "changeTaskStatusByNameResponse", targetNamespace = "http://endpoint.tm.vpavlova.ru/", className = "ru.vpavlova.tm.endpoint.ChangeTaskStatusByNameResponse")
    public void changeTaskStatusByName(

        @WebParam(name = "session", targetNamespace = "")
        ru.vpavlova.tm.endpoint.Session session,
        @WebParam(name = "name", targetNamespace = "")
        java.lang.String name,
        @WebParam(name = "status", targetNamespace = "")
        ru.vpavlova.tm.endpoint.Status status
    );

    @WebMethod
    @Action(input = "http://endpoint.tm.vpavlova.ru/TaskEndpoint/removeTaskOneByIndexRequest", output = "http://endpoint.tm.vpavlova.ru/TaskEndpoint/removeTaskOneByIndexResponse")
    @RequestWrapper(localName = "removeTaskOneByIndex", targetNamespace = "http://endpoint.tm.vpavlova.ru/", className = "ru.vpavlova.tm.endpoint.RemoveTaskOneByIndex")
    @ResponseWrapper(localName = "removeTaskOneByIndexResponse", targetNamespace = "http://endpoint.tm.vpavlova.ru/", className = "ru.vpavlova.tm.endpoint.RemoveTaskOneByIndexResponse")
    public void removeTaskOneByIndex(

        @WebParam(name = "session", targetNamespace = "")
        ru.vpavlova.tm.endpoint.Session session,
        @WebParam(name = "index", targetNamespace = "")
        java.lang.Integer index
    );

    @WebMethod
    @Action(input = "http://endpoint.tm.vpavlova.ru/TaskEndpoint/bindTaskByProjectRequest", output = "http://endpoint.tm.vpavlova.ru/TaskEndpoint/bindTaskByProjectResponse")
    @RequestWrapper(localName = "bindTaskByProject", targetNamespace = "http://endpoint.tm.vpavlova.ru/", className = "ru.vpavlova.tm.endpoint.BindTaskByProject")
    @ResponseWrapper(localName = "bindTaskByProjectResponse", targetNamespace = "http://endpoint.tm.vpavlova.ru/", className = "ru.vpavlova.tm.endpoint.BindTaskByProjectResponse")
    public void bindTaskByProject(

        @WebParam(name = "session", targetNamespace = "")
        ru.vpavlova.tm.endpoint.Session session,
        @WebParam(name = "projectId", targetNamespace = "")
        java.lang.String projectId,
        @WebParam(name = "taskId", targetNamespace = "")
        java.lang.String taskId
    );

    @WebMethod
    @Action(input = "http://endpoint.tm.vpavlova.ru/TaskEndpoint/removeTaskOneByNameRequest", output = "http://endpoint.tm.vpavlova.ru/TaskEndpoint/removeTaskOneByNameResponse")
    @RequestWrapper(localName = "removeTaskOneByName", targetNamespace = "http://endpoint.tm.vpavlova.ru/", className = "ru.vpavlova.tm.endpoint.RemoveTaskOneByName")
    @ResponseWrapper(localName = "removeTaskOneByNameResponse", targetNamespace = "http://endpoint.tm.vpavlova.ru/", className = "ru.vpavlova.tm.endpoint.RemoveTaskOneByNameResponse")
    public void removeTaskOneByName(

        @WebParam(name = "session", targetNamespace = "")
        ru.vpavlova.tm.endpoint.Session session,
        @WebParam(name = "name", targetNamespace = "")
        java.lang.String name
    );
}

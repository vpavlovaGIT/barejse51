package ru.vpavlova.tm.api.endpoint;

import com.sun.istack.internal.NotNull;
import ru.vpavlova.tm.endpoint.*;

public interface EndpointLocator {

    @NotNull
    AdminEndpoint getAdminEndpoint();

    @NotNull
    ProjectEndpoint getProjectEndpoint();

    @NotNull
    SessionEndpoint getSessionEndpoint();

    @NotNull
    TaskEndpoint getTaskEndpoint();

    @NotNull
    UserEndpoint getUserEndpoint();

}

package ru.vpavlova.tm.bootstrap;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.SneakyThrows;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.reflections.Reflections;
import ru.vpavlova.tm.api.endpoint.EndpointLocator;
import ru.vpavlova.tm.api.service.IPropertyService;
import ru.vpavlova.tm.api.repository.*;
import ru.vpavlova.tm.api.service.*;
import ru.vpavlova.tm.command.AbstractCommand;
import ru.vpavlova.tm.component.FileScanner;
import ru.vpavlova.tm.endpoint.*;
import ru.vpavlova.tm.repository.*;
import ru.vpavlova.tm.service.*;
import ru.vpavlova.tm.util.SystemUtil;
import ru.vpavlova.tm.util.TerminalUtil;

import javax.xml.ws.Endpoint;
import java.io.File;
import java.lang.Exception;
import java.lang.reflect.Modifier;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class Bootstrap implements ServiceLocator, EndpointLocator {

    @NotNull
    private final ICommandRepository commandRepository = new CommandRepository();

    @NotNull
    private final ICommandService commandService = new CommandService(commandRepository);

    @NotNull
    private final ILoggerService loggerService = new LoggerService();

    @NotNull
    public final IPropertyService propertyService = new PropertyService();

    @NotNull
    public final AdminEndpointService adminEndpointService = new AdminEndpointService();

    @NotNull
    public final AdminEndpoint adminEndpoint = adminEndpointService.getAdminEndpointPort();

    @NotNull
    public final ProjectEndpointService projectEndpointService = new ProjectEndpointService();

    @NotNull
    public final ProjectEndpoint projectEndpoint = projectEndpointService.getProjectEndpointPort();

    @NotNull
    public final SessionEndpointService sessionEndpointService = new SessionEndpointService();

    @NotNull
    public final SessionEndpoint sessionEndpoint = sessionEndpointService.getSessionEndpointPort();

    @NotNull
    public final TaskEndpointService taskEndpointService = new TaskEndpointService();

    @NotNull
    public final TaskEndpoint taskEndpoint = taskEndpointService.getTaskEndpointPort();

    @NotNull
    public final UserEndpointService userEndpointService = new UserEndpointService();

    @NotNull
    public final UserEndpoint userEndpoint = userEndpointService.getUserEndpointPort();

    @NotNull
    private final FileScanner fileScanner = new FileScanner(this);

    @Nullable
    private Session session = null;

    public void parseArg(@Nullable final String arg) {
        if (arg == null || arg.isEmpty()) return;
        @Nullable final AbstractCommand command = commandService.getCommandByArg(arg);
        if (command == null) return;
        command.execute();
    }

    private void textWelcome() {
        loggerService.debug("TEST!!");
        loggerService.info("*** WELCOME TO TASK MANAGER ***");
    }

    private void init() {
        initPID();
        initCommands();
        initFileScanner();
        initEndpoint();
    }

    private void initEndpoint() {
        registry(sessionEndpoint);
        registry(taskEndpoint);
        registry(projectEndpoint);
        registry(userEndpoint);
        registry(adminEndpoint);
    }

    private void registry(@NotNull final Object endpoint) {
        @NotNull final String host = propertyService.getServerHost();
        @NotNull final String port = propertyService.getServerPort();
        @NotNull final String name = endpoint.getClass().getSimpleName();
        @NotNull final String wsdl = "http://" + host + ":" + port + "/" + name + "?wsdl";
        System.out.println(wsdl);
        Endpoint.publish(wsdl, endpoint);
    }

    private void initFileScanner() {
        fileScanner.init();
    }

    @SneakyThrows
    private void initCommands() {
        @NotNull final Reflections reflections = new Reflections("ru.vpavlova.tm.command");
        @NotNull final Set<Class<? extends AbstractCommand>> classes =
                reflections.getSubTypesOf(ru.vpavlova.tm.command.AbstractCommand.class);
        for (@NotNull final Class<? extends AbstractCommand> clazz : classes) {
            final boolean isAbstract = Modifier.isAbstract(clazz.getModifiers());
            if (isAbstract) continue;
            registry(clazz.newInstance());
        }
    }

    @SneakyThrows
    private void initPID() {
        @NotNull final String fileName = "task-manager.pid";
        @NotNull final String pid = Long.toString(SystemUtil.getPID());
        Files.write(Paths.get(fileName), pid.getBytes());
        @NotNull final File file = new File(fileName);
        file.deleteOnExit();
    }

    private void process() {
        while (true) {
            try {
                @NotNull final String command = TerminalUtil.nextLine();
                loggerService.command(command);
                parseCommand(command);
                System.out.println("[OK]");
            } catch (@NotNull final Exception e) {
                loggerService.error(e);
                System.err.println("[FAIL]");
            }
        }
    }

    public void run(@Nullable final String... args) {
        textWelcome();
        if (parseArgs(args)) System.exit(0);
        init();
        process();
    }

    public void parseCommand(@Nullable final String cmd) {
        if (!Optional.ofNullable(cmd).isPresent()) return;
        @Nullable final AbstractCommand command = commandService.getCommandByName(cmd);
        if (!Optional.ofNullable(command).isPresent()) return;
        command.execute();
    }

    public boolean parseArgs(@Nullable final String[] args) {
        if (!Optional.ofNullable(args).isPresent() || args.length == 0) return false;
        @Nullable final String arg = args[0];
        parseArg(arg);
        return true;
    }

    private void registry(final AbstractCommand command) {
        if (!Optional.ofNullable(command).isPresent()) return;
        command.setServiceLocator(this);
        command.setEndpointLocator(this);
        command.setBootstrap(this);
        commandService.add(command);
    }

}

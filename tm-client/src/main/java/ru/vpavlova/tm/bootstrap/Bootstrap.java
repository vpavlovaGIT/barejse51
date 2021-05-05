package ru.vpavlova.tm.bootstrap;

import lombok.Getter;
import lombok.SneakyThrows;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.reflections.Reflections;
import ru.vpavlova.tm.api.IPropertyService;
import ru.vpavlova.tm.api.endpoint.*;
import ru.vpavlova.tm.api.repository.*;
import ru.vpavlova.tm.api.service.*;
import ru.vpavlova.tm.command.AbstractCommand;
import ru.vpavlova.tm.component.Backup;
import ru.vpavlova.tm.component.FileScanner;
import ru.vpavlova.tm.endpoint.*;
import ru.vpavlova.tm.entity.Session;
import ru.vpavlova.tm.enumerated.Role;
import ru.vpavlova.tm.enumerated.Status;
import ru.vpavlova.tm.repository.*;
import ru.vpavlova.tm.service.*;
import ru.vpavlova.tm.util.SystemUtil;
import ru.vpavlova.tm.util.TerminalUtil;

import javax.xml.ws.Endpoint;
import java.io.File;
import java.lang.reflect.Modifier;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.Set;

@Getter
public class Bootstrap implements ServiceLocator {

    @NotNull
    private final ICommandRepository commandRepository = new CommandRepository();

    @NotNull
    private final ICommandService commandService = new CommandService(commandRepository);

    @NotNull
    private final ILoggerService loggerService = new LoggerService();

    @NotNull
    public final IPropertyService propertyService = new PropertyService();

    @NotNull
    private final Backup backup = new Backup(this);

    @NotNull
    private final FileScanner fileScanner = new FileScanner(this);

    @Nullable
    private Session session = null;

    public Bootstrap() {
    }

    public void parseArg(@Nullable final String arg) {
        if (arg == null || arg.isEmpty()) return;
        @Nullable final AbstractCommand command = commandService.getCommandByArg(arg);
        if (command == null) return;
        command.execute();
    }

    public void initData() {
        @NotNull String userId = userService.create("test", "test", "test@test.ru").getId();
        projectService.add(userId, "DEMO 1", "description1").setStatus(Status.COMPLETE);
        projectService.add(userId, "BETA 2", "description2").setStatus(Status.IN_PROGRESS);
        projectService.add(userId, "LAMBDA 3", "description3").setStatus(Status.IN_PROGRESS);
        taskService.add(userId, "B_TASK 4", "bbb").setStatus(Status.NOT_STARTED);
        taskService.add(userId, "C_TASK 5", "ccc").setStatus(Status.COMPLETE);
        taskService.add(userId, "C_TASK 6", "ccc").setStatus(Status.COMPLETE);
        @NotNull String adminId = userService.create("admin", "admin", Role.ADMIN).getId();
        projectService.add(adminId, "OMEGA 4", "description4").setStatus(Status.NOT_STARTED);
        projectService.add(adminId, "GAMMA 5", "description5").setStatus(Status.COMPLETE);
        projectService.add(adminId, "GAMMA 6", "description6").setStatus(Status.IN_PROGRESS);
        taskService.add(adminId, "A_TASK 1", "aaa").setStatus(Status.COMPLETE);
        taskService.add(adminId, "D_TASK 2", "ddd").setStatus(Status.IN_PROGRESS);
        taskService.add(adminId, "E_TASK 3", "eee").setStatus(Status.IN_PROGRESS);
    }

    private void textWelcome() {
        loggerService.debug("TEST!!");
        loggerService.info("*** WELCOME TO TASK MANAGER ***");
    }

    private void init() {
        initPID();
        initCommands();
        initData();
        initBackup();
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


    private void initBackup() {
        backup.init();
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
        @Nullable final Role[] roles = command.roles();
        authService.checkRole(roles);
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
        commandService.add(command);
    }

}

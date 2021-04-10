package ru.vpavlova.tm.command.data;

import lombok.SneakyThrows;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.vpavlova.tm.dto.Domain;
import ru.vpavlova.tm.enumerated.Role;

import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;

public class DataBinarySaveCommand extends AbstractDataCommand {

    @Nullable
    @Override
    public String arg() {
        return null;
    }

    @NotNull
    @Override
    public String name() {
        return "data-bin-save";
    }

    @NotNull
    @Override
    public String description() {
        return "Save binary data.";
    }

    @Override
    @SneakyThrows
    public void execute() {
        System.out.println("[DATA BIN SAVE]");
        @NotNull final Domain domain = getDomain();
        @NotNull final File file = new File(FILE_BINARY);
        Files.deleteIfExists(file.toPath());
        Files.createFile(file.toPath());
        @NotNull final FileOutputStream fileOutputStream = new FileOutputStream(file);
        @NotNull final ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
        objectOutputStream.writeObject(domain);
        objectOutputStream.close();
        fileOutputStream.close();
    }

    @NotNull
    public Role[] roles() {
        return new Role[]{Role.ADMIN};
    }

}

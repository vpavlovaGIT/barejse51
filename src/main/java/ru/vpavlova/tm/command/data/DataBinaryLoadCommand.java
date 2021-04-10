package ru.vpavlova.tm.command.data;

import lombok.SneakyThrows;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.vpavlova.tm.dto.Domain;
import ru.vpavlova.tm.enumerated.Role;

import java.io.FileInputStream;
import java.io.ObjectInputStream;

public class DataBinaryLoadCommand extends AbstractDataCommand {

    @Nullable
    @Override
    public String arg() {
        return null;
    }

    @NotNull
    @Override
    public String name() {
        return "data-bin-load";
    }

    @NotNull
    @Override
    public String description() {
        return "Load binary data.";
    }

    @Override
    @SneakyThrows
    public void execute() {
        System.out.println("[DATA BIN LOAD]");
        @NotNull final FileInputStream fileInputStream = new FileInputStream(FILE_BINARY);
        @NotNull final ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
        @NotNull final Domain domain = (Domain) objectInputStream.readObject();
        setDomain(domain);
        objectInputStream.close();
        fileInputStream.close();
    }

    @NotNull
    public Role[] roles() {
        return new Role[]{Role.ADMIN};
    }

}

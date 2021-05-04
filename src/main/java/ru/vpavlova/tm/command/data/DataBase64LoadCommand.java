package ru.vpavlova.tm.command.data;

import lombok.SneakyThrows;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.vpavlova.tm.dto.Domain;
import ru.vpavlova.tm.enumerated.Role;
import sun.misc.BASE64Decoder;

import java.io.ByteArrayInputStream;
import java.io.ObjectInputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

public class DataBase64LoadCommand extends AbstractDataCommand {

    @Nullable
    @Override
    public String arg() {
        return null;
    }

    @NotNull
    @Override
    public String name() {
        return "data-base64-load";
    }

    @NotNull
    @Override
    public String description() {
        return "Load base64 data.";
    }

    @Override
    @SneakyThrows
    public void execute() {
        System.out.println("[DATA BASE64 LOAD]");
        @NotNull final String base64date = new String(Files.readAllBytes(Paths.get(FILE_BASE64)));
        @NotNull final byte[] decodedData = new BASE64Decoder().decodeBuffer(base64date);
        @NotNull final ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(decodedData);
        @NotNull final ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
        @NotNull final Domain domain = (Domain) objectInputStream.readObject();
        setDomain(domain);
        objectInputStream.close();
        byteArrayInputStream.close();
    }

    @NotNull
    public Role[] roles() {
        return new Role[]{Role.ADMIN};
    }

}

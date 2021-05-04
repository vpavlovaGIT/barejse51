package ru.vpavlova.tm.command.data;

import lombok.SneakyThrows;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.vpavlova.tm.dto.Domain;
import ru.vpavlova.tm.enumerated.Role;
import sun.misc.BASE64Encoder;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;

public class DataBase64SaveCommand extends AbstractDataCommand {

    @Nullable
    @Override
    public String arg() {
        return null;
    }

    @NotNull
    @Override
    public String name() {
        return "data-base64-save";
    }

    @NotNull
    @Override
    public String description() {
        return "Save base64 data.";
    }

    @Override
    @SneakyThrows
    public void execute() {
        System.out.println("[DATA BASE64 SAVE]");
        @NotNull final Domain domain = getDomain();
        @NotNull final File file = new File(FILE_BASE64);
        Files.deleteIfExists(file.toPath());
        Files.createFile(file.toPath());
        @NotNull final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        @NotNull final ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
        objectOutputStream.writeObject(domain);
        objectOutputStream.close();
        byteArrayOutputStream.close();
        @NotNull final byte[] bytes = byteArrayOutputStream.toByteArray();
        @NotNull final String base64 = new BASE64Encoder().encode(bytes);
        @NotNull final FileOutputStream fileOutputStream = new FileOutputStream(file);
        fileOutputStream.write(base64.getBytes());
        fileOutputStream.flush();
        fileOutputStream.close();
    }

    @NotNull
    public Role[] roles() {
        return new Role[]{Role.ADMIN};
    }

}

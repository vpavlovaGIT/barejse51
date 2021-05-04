package ru.vpavlova.tm.command.data;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.vpavlova.tm.dto.Domain;
import ru.vpavlova.tm.enumerated.Role;

import java.io.FileOutputStream;

public class DataJsonSaveFasterXmlCommand extends AbstractDataCommand {

    @Nullable
    @Override
    public String arg() {
        return null;
    }

    @NotNull
    @Override
    public String name() {
        return "data-json-save-fasterxml";
    }

    @NotNull
    @Override
    public String description() {
        return "Save json data to file.";
    }

    @Override
    @SneakyThrows
    public void execute() {
        System.out.println("DATA SAVE JSON");
        @NotNull final Domain domain = getDomain();
        @NotNull final ObjectMapper objectMapper = new ObjectMapper();
        @NotNull final String json = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(domain);
        @NotNull final FileOutputStream fileOutputStream = new FileOutputStream(FILE_JSON_FASTERXML);
        fileOutputStream.write(json.getBytes());
        fileOutputStream.flush();
        fileOutputStream.close();
    }

    @NotNull
    public Role[] roles() {
        return new Role[]{Role.ADMIN};
    }

}

package ru.vpavlova.tm.command.data;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.vpavlova.tm.dto.Domain;
import ru.vpavlova.tm.enumerated.Role;

import java.nio.file.Files;
import java.nio.file.Paths;

public class DataJsonLoadFasterXmlCommand extends AbstractDataCommand {

    @Nullable
    @Override
    public String arg() {
        return null;
    }

    @NotNull
    @Override
    public String name() {
        return "data-json-load-fasterxml";
    }

    @NotNull
    @Override
    public String description() {
        return "Load json data from file.";
    }

    @Override
    @SneakyThrows
    public void execute() {
        System.out.println("[DATA LOAD JSON]");
        @NotNull final String json = new String(Files.readAllBytes(Paths.get(FILE_JSON_FASTERXML)));
        @NotNull final ObjectMapper objectMapper = new ObjectMapper(new JsonFactory());
        @NotNull final Domain domain = objectMapper.readValue(json, Domain.class);
        setDomain(domain);
    }

    @NotNull
    public Role[] roles() {
        return new Role[]{Role.ADMIN};
    }

}

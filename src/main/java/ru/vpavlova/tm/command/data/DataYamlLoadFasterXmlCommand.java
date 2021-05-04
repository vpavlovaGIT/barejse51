package ru.vpavlova.tm.command.data;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import lombok.SneakyThrows;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.vpavlova.tm.dto.Domain;
import ru.vpavlova.tm.enumerated.Role;

import java.nio.file.Files;
import java.nio.file.Paths;

public class DataYamlLoadFasterXmlCommand extends AbstractDataCommand {

    @Nullable
    @Override
    public String arg() {
        return null;
    }

    @NotNull
    @Override
    public String name() {
        return "data-yaml-load-fasterxml";
    }

    @NotNull
    @Override
    public String description() {
        return "Load yaml data from file by FasterXml library.";
    }

    @Override
    @SneakyThrows
    public void execute() {
        System.out.println("DATA YAML LOAD");
        @NotNull final String yaml = new String(Files.readAllBytes(Paths.get(FILE_YAML_FASTERXML)));
        @NotNull final ObjectMapper objectMapper = new ObjectMapper(new YAMLFactory());
        @NotNull final Domain domain = objectMapper.readValue(yaml, Domain.class);
        setDomain(domain);
    }

    @NotNull
    public Role[] roles() {
        return new Role[]{Role.ADMIN};
    }

}

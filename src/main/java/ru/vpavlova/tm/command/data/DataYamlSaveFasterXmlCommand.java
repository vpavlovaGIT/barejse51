package ru.vpavlova.tm.command.data;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import lombok.SneakyThrows;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.vpavlova.tm.dto.Domain;
import ru.vpavlova.tm.enumerated.Role;

import java.io.FileOutputStream;

public class DataYamlSaveFasterXmlCommand extends AbstractDataCommand {

    @Nullable
    @Override
    public String arg() {
        return null;
    }

    @NotNull
    @Override
    public String name() {
        return "data-yaml-save-fasterxml";
    }

    @NotNull
    @Override
    public String description() {
        return "Save yaml data to file by FasterXml library.";
    }

    @Override
    @SneakyThrows
    public void execute() {
        System.out.println("DATA YAML SAVE");
        @NotNull final Domain domain = getDomain();
        @NotNull final ObjectMapper objectMapper = new ObjectMapper(new YAMLFactory());
        @NotNull final String yaml = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(domain);
        @NotNull final FileOutputStream fileOutputStream = new FileOutputStream(FILE_YAML_FASTERXML);
        fileOutputStream.write(yaml.getBytes());
        fileOutputStream.flush();
        fileOutputStream.close();
    }

    @NotNull
    public Role[] roles() {
        return new Role[]{Role.ADMIN};
    }

}

package ru.vpavlova.tm.command.data;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import lombok.SneakyThrows;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.vpavlova.tm.dto.Domain;
import ru.vpavlova.tm.enumerated.Role;

import java.nio.file.Files;
import java.nio.file.Paths;

public class DataXmlLoadFasterXmlCommand extends AbstractDataCommand {

    @Nullable
    @Override
    public String arg() {
        return null;
    }

    @NotNull
    @Override
    public String name() {
        return "data-xml-load-fasterxml";
    }

    @NotNull
    @Override
    public String description() {
        return "Load xml data from file.";
    }

    @Override
    @SneakyThrows
    public void execute() {
        System.out.println("DATA XML LOAD");
        @NotNull final String xml = new String(Files.readAllBytes(Paths.get(FILE_XML_FASTERXML)));
        @NotNull final ObjectMapper objectMapper = new XmlMapper();
        @NotNull final Domain domain = objectMapper.readValue(xml, Domain.class);
        setDomain(domain);
    }

    @NotNull
    public Role[] roles() {
        return new Role[]{Role.ADMIN};
    }

}

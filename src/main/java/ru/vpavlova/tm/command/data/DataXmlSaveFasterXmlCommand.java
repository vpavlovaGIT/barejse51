package ru.vpavlova.tm.command.data;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import lombok.SneakyThrows;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.vpavlova.tm.dto.Domain;
import ru.vpavlova.tm.enumerated.Role;

import java.io.FileOutputStream;

public class DataXmlSaveFasterXmlCommand extends AbstractDataCommand {

    @Nullable
    @Override
    public String arg() {
        return null;
    }

    @NotNull
    @Override
    public String name() {
        return "data-xml-save-fasterxml";
    }

    @NotNull
    @Override
    public String description() {
        return "Save xml data to file.";
    }

    @Override
    @SneakyThrows
    public void execute() {
        System.out.println("DATA XML SAVE");
        @NotNull final Domain domain = getDomain();
        @NotNull final ObjectMapper objectMapper = new XmlMapper();
        @NotNull final String xml = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(domain);
        @NotNull final FileOutputStream fileOutputStream = new FileOutputStream(FILE_XML_FASTERXML);
        fileOutputStream.write(xml.getBytes());
        fileOutputStream.flush();
        fileOutputStream.close();
    }

    @NotNull
    public Role[] roles() {
        return new Role[]{Role.ADMIN};
    }

}

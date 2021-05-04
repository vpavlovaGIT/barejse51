package ru.vpavlova.tm.command.data;

import lombok.SneakyThrows;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.vpavlova.tm.dto.Domain;
import ru.vpavlova.tm.enumerated.Role;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import java.io.FileOutputStream;

public class DataJsonSaveJaxBCommand extends AbstractDataCommand {

    @Nullable
    @Override
    public String arg() {
        return null;
    }

    @NotNull
    @Override
    public String name() {
        return "data-json-save-jaxb";
    }

    @NotNull
    @Override
    public String description() {
        return "Save json data to file by JaxB library.";
    }

    @Override
    @SneakyThrows
    public void execute() {
        System.out.println("DATA SAVE JSON");
        System.setProperty(JAVAX_XML_BIND_CONTEXT_FACTORY, ORG_ECLIPSE_PERSISTENCE_JAXB_JAXBCONTEXT_FACTORY);
        @NotNull Domain domain = getDomain();
        @NotNull final JAXBContext jaxbContext = JAXBContext.newInstance(Domain.class);
        @NotNull final Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
        jaxbMarshaller.setProperty(ECLIPSELINK_MEDIA_TYPE, APPLICATION_JSON);
        jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        @NotNull final FileOutputStream fileOutputStream = new FileOutputStream(FILE_JSON_JAXB);
        jaxbMarshaller.marshal(domain, fileOutputStream);
        fileOutputStream.flush();
        fileOutputStream.close();
    }

    @NotNull
    public Role[] roles() {
        return new Role[]{Role.ADMIN};
    }

}

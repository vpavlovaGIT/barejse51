package ru.vpavlova.tm.command.data;

import lombok.SneakyThrows;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.vpavlova.tm.dto.Domain;
import ru.vpavlova.tm.enumerated.Role;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import java.io.File;

public class DataJsonLoadJaxBCommand extends AbstractDataCommand {

    @Nullable
    @Override
    public String arg() {
        return null;
    }

    @NotNull
    @Override
    public String name() {
        return "data-json-load-jaxb";
    }

    @NotNull
    @Override
    public String description() {
        return "Load json data from file by JaxB library.";
    }

    @Override
    @SneakyThrows
    public void execute() {
        System.out.println("DATA LOAD JSON");
        System.setProperty(JAVAX_XML_BIND_CONTEXT_FACTORY, ORG_ECLIPSE_PERSISTENCE_JAXB_JAXBCONTEXT_FACTORY);
        @NotNull final File file = new File(FILE_JSON_JAXB);
        @NotNull final JAXBContext jaxbContext = JAXBContext.newInstance(Domain.class);
        @NotNull Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        unmarshaller.setProperty(ECLIPSELINK_MEDIA_TYPE, APPLICATION_JSON);
        @NotNull final Domain domain = (Domain) unmarshaller.unmarshal(file);
        setDomain(domain);
    }

    @NotNull
    public Role[] roles() {
        return new Role[]{Role.ADMIN};
    }

}

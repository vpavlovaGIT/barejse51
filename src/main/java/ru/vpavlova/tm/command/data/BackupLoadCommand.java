package ru.vpavlova.tm.command.data;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import lombok.SneakyThrows;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.vpavlova.tm.dto.Domain;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;

public class BackupLoadCommand extends AbstractDataCommand {

    @Nullable
    @Override
    public String arg() {
        return null;
    }

    @NotNull
    @Override
    public String name() {
        return "backup-load";
    }

    @NotNull
    @Override
    public String description() {
        return "Load backup data.";
    }

    @Override
    @SneakyThrows
    public void execute() {
        @NotNull final File file = new File(BACKUP_XML);
        if (!file.exists()) return;
        @NotNull final String xml = new String(Files.readAllBytes(Paths.get(BACKUP_XML)));
        @NotNull final ObjectMapper objectMapper = new XmlMapper();
        @NotNull final Domain domain = objectMapper.readValue(xml, Domain.class);
        setDomain(domain);
    }

}

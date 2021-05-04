package ru.vpavlova.tm.command.data;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import lombok.SneakyThrows;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.vpavlova.tm.dto.Domain;

import java.io.FileOutputStream;

public class BackupSaveCommand extends AbstractDataCommand {

    @NotNull
    public final static String BACKUP_SAVE = "backup-save";

    @Nullable
    @Override
    public String arg() {
        return null;
    }

    @NotNull
    @Override
    public String name() {
        return BACKUP_SAVE;
    }

    @NotNull
    @Override
    public String description() {
        return "Save backup data.";
    }

    @Override
    @SneakyThrows
    public void execute() {
        @NotNull final Domain domain = getDomain();
        @NotNull final ObjectMapper objectMapper = new XmlMapper();
        @NotNull final String xml = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(domain);
        @NotNull final FileOutputStream fileOutputStream = new FileOutputStream(BACKUP_XML);
        fileOutputStream.write(xml.getBytes());
        fileOutputStream.flush();
        fileOutputStream.close();
    }

}

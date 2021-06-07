package ru.vpavlova.tm.component;


import lombok.SneakyThrows;
import org.jetbrains.annotations.NotNull;
import ru.vpavlova.tm.api.service.ServiceLocator;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Backup implements Runnable {

    @NotNull
    public static final String FILE_BINARY = "./data.bin";

    @NotNull
    public static final String FILE_BASE64 = "./data.base64";

    @NotNull
    public static final String FILE_FASTERXML_JSON = "./data-fasterxml.json";

    @NotNull
    public static final String FILE_FASTERXML_XML = "./data-fasterxml.xml";

    @NotNull
    public static final String FILE_FASTERXML_YAML = "./data-fasterxml.yaml";

    @NotNull
    public static final String FILE_JAXB_JSON = "./data-jaxb.json";

    @NotNull
    public static final String FILE_JAXB_XML = "./data-jaxb.xml";

    @NotNull
    public static final String JAXB_JSON_PROPERTY_NAME = "eclipselink.media-type";

    @NotNull
    public static final String JAXB_JSON_PROPERTY_VALUE = "application/json";

    @NotNull
    public static final String SYSTEM_JSON_PROPERTY_NAME = "javax.xml.bind.context.factory";

    @NotNull
    public static final String SYSTEM_JSON_PROPERTY_VALUE = "org.eclipse.persistence.jaxb.JAXBContextFactory";

    @NotNull
    public static final String BACKUP_XML = "./backup.xml";

    @NotNull
    private static final int INTERVAL = 30;

    @NotNull
    private final ScheduledExecutorService es = Executors.newSingleThreadScheduledExecutor();


    @NotNull
    private final ServiceLocator serviceLocator;

    public Backup(@NotNull ServiceLocator serviceLocator) {
        this.serviceLocator = serviceLocator;
    }

    public void init() {
        load();
        start();
    }

    @SneakyThrows
    public void load() {
        serviceLocator.getDataService().loadBackup();
    }

    public void start() {
        es.scheduleWithFixedDelay(this, 0, INTERVAL, TimeUnit.SECONDS);
    }

    @Override
    @SneakyThrows
    public void run() {
        serviceLocator.getDataService().saveBackup();
    }

}

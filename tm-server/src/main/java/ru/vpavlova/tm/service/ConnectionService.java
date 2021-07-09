package ru.vpavlova.tm.service;

import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.jetbrains.annotations.NotNull;
import ru.vpavlova.tm.api.IPropertyService;
import ru.vpavlova.tm.api.service.IConnectionService;
import ru.vpavlova.tm.dto.*;
import ru.vpavlova.tm.entity.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.HashMap;
import java.util.Map;

public class ConnectionService implements IConnectionService {

    @NotNull
    private final IPropertyService propertyService;

    @NotNull
    private final EntityManagerFactory entityManagerFactory;

    public ConnectionService(
            @NotNull final IPropertyService propertyService
    ) {
        this.propertyService = propertyService;
        this.entityManagerFactory = factory();
    }

    private EntityManagerFactory factory() {
        @NotNull final Map<String, String> settings = new HashMap<>();
        settings.put(org.hibernate.cfg.Environment.DRIVER, propertyService.getJdbcDriver());
        settings.put(org.hibernate.cfg.Environment.URL, propertyService.getJdbcUrl());
        settings.put(org.hibernate.cfg.Environment.USER, propertyService.getJdbcUser());
        settings.put(org.hibernate.cfg.Environment.PASS, propertyService.getJdbcPassword());
        settings.put(org.hibernate.cfg.Environment.DIALECT, propertyService.getDialect());
        settings.put(org.hibernate.cfg.Environment.HBM2DDL_AUTO, propertyService.getHbm2ddlAuto());
        settings.put(org.hibernate.cfg.Environment.SHOW_SQL, propertyService.getShowSql());

        @NotNull final StandardServiceRegistryBuilder registryBuilder =
                new StandardServiceRegistryBuilder();
        registryBuilder.applySettings(settings);
        @NotNull final StandardServiceRegistry registry = registryBuilder.build();
        @NotNull final MetadataSources sources = new MetadataSources(registry);

        sources.addAnnotatedClass(ProjectGraph.class);
        sources.addAnnotatedClass(Project.class);

        sources.addAnnotatedClass(TaskGraph.class);
        sources.addAnnotatedClass(Task.class);

        sources.addAnnotatedClass(SessionGraph.class);
        sources.addAnnotatedClass(Session.class);

        sources.addAnnotatedClass(UserGraph.class);
        sources.addAnnotatedClass(User.class);

        @NotNull final Metadata metadata = sources.getMetadataBuilder().build();
        return metadata.getSessionFactoryBuilder().build();
    }

    @NotNull
    @Override
    public EntityManager getEntityManager() {
        return entityManagerFactory.createEntityManager();
    }

}

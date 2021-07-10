package ru.vpavlova.tm.service;

import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Environment;
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
        @NotNull
        final Map<String, String> settings = new HashMap<>();
        settings.put(Environment.DRIVER, propertyService.getJdbcDriver());
        settings.put(Environment.URL, propertyService.getJdbcUrl());
        settings.put(Environment.USER, propertyService.getJdbcUser());
        settings.put(Environment.PASS, propertyService.getJdbcPassword());
        settings.put(Environment.DIALECT, propertyService.getDialect());
        settings.put(Environment.HBM2DDL_AUTO, propertyService.getHbm2ddlAuto());
        settings.put(Environment.SHOW_SQL, propertyService.getShowSql());

        settings.put(Environment.USE_SECOND_LEVEL_CACHE, propertyService.getUseSecondLevelCache());
        settings.put(Environment.USE_QUERY_CACHE, propertyService.getUseQueryCache());
        settings.put(Environment.USE_MINIMAL_PUTS, propertyService.getUseMinimalPuts());
        settings.put(Environment.CACHE_REGION_PREFIX, propertyService.getCacheRegionPrefix());
        settings.put(Environment.CACHE_PROVIDER_CONFIG, propertyService.getCacheProviderConfig());
        settings.put(Environment.CACHE_REGION_FACTORY, propertyService.getCacheRegionFactory());
        settings.put(PropertyService.USE_LITE_MEMBER, propertyService.getUseLiteMemberValue());

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

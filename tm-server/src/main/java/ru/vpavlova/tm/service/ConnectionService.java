package ru.vpavlova.tm.service;

import org.apache.ibatis.datasource.pooled.PooledDataSource;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.transaction.TransactionFactory;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.jetbrains.annotations.NotNull;
import ru.vpavlova.tm.api.IPropertyService;
import ru.vpavlova.tm.api.repository.*;
import ru.vpavlova.tm.api.service.IConnectionService;
import ru.vpavlova.tm.dto.*;
import ru.vpavlova.tm.entity.*;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

public class ConnectionService implements IConnectionService {

    @NotNull
    private final IPropertyService propertyService;

    @NotNull
    private final SqlSessionFactory sqlSessionFactory;

    @NotNull
    private final EntityManagerFactory entityManagerFactory;

    public ConnectionService(
            @NotNull final IPropertyService propertyService
    ) {
        this.propertyService = propertyService;
        this.sqlSessionFactory = getSqlSessionFactory();
        this.entityManagerFactory = factory();
    }

    @NotNull
    @Override
    public SqlSession getSqlSession() {
        return sqlSessionFactory.openSession();
    }

    @NotNull
    private SqlSessionFactory getSqlSessionFactory() {
        @NotNull final String driver = propertyService.getJdbcDriver();
        @NotNull final String username = propertyService.getJdbcUser();
        @NotNull final String password = propertyService.getJdbcPassword();
        @NotNull final String url = propertyService.getJdbcUrl();

        @NotNull final DataSource dataSource = new PooledDataSource(driver, url, username, password);
        @NotNull final TransactionFactory transactionFactory = new JdbcTransactionFactory();
        @NotNull final Environment environment = new Environment("development", transactionFactory, dataSource);
        @NotNull final Configuration configuration = new Configuration(environment);
        configuration.addMapper(IUserRepository.class);
        configuration.addMapper(ISessionRepository.class);
        configuration.addMapper(IProjectRepository.class);
        configuration.addMapper(ITaskRepository.class);
        return new SqlSessionFactoryBuilder().build(configuration);
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

        sources.addAnnotatedClass(ProjectDTO.class);
        sources.addAnnotatedClass(Project.class);

        sources.addAnnotatedClass(TaskDTO.class);
        sources.addAnnotatedClass(Task.class);

        sources.addAnnotatedClass(SessionDTO.class);
        sources.addAnnotatedClass(Session.class);

        sources.addAnnotatedClass(UserDTO.class);
        sources.addAnnotatedClass(User.class);

        @NotNull final Metadata metadata = sources.getMetadataBuilder().build();
        return metadata.getSessionFactoryBuilder().build();
    }

}

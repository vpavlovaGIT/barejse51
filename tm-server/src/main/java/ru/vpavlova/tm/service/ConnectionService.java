package ru.vpavlova.tm.service;

import org.apache.ibatis.datasource.pooled.PooledDataSource;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.transaction.TransactionFactory;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;
import org.jetbrains.annotations.NotNull;
import ru.vpavlova.tm.api.IPropertyService;
import ru.vpavlova.tm.api.repository.*;
import ru.vpavlova.tm.api.service.IConnectionService;

import javax.sql.DataSource;

public class ConnectionService implements IConnectionService {

    @NotNull
    private final IPropertyService propertyService;

    @NotNull
    private final SqlSessionFactory sqlSessionFactory;

    public ConnectionService(
            @NotNull final IPropertyService propertyService
    ) {
        this.propertyService = propertyService;
        this.sqlSessionFactory = getSqlSessionFactory();
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

}

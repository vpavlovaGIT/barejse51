package ru.vpavlova.tm.service;

import lombok.SneakyThrows;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.vpavlova.tm.api.IPropertyService;

import java.io.InputStream;
import java.util.Properties;

public class PropertyService implements IPropertyService {

    @NotNull
    public static final String APPLICATION_VERSION = "version";

    @NotNull
    public static final String APPLICATION_VERSION_DEFAULT = "1.0.0";

    @NotNull
    public static final String DEVELOPER_EMAIL = "email";

    @NotNull
    public static final String DEVELOPER_EMAIL_DEFAULT = "";

    @NotNull
    public static final String DEVELOPER_NAME = "developer";

    @NotNull
    public static final String DEVELOPER_NAME_DEFAULT = "";

    @NotNull
    public static final String FILE_NAME = "application.properties";

    @NotNull
    public static final String JDBC_DRIVER = "jdbc.driver";

    @NotNull
    public static final String JDBC_DRIVER_DEFAULT = "com.mysql.jdbc.Driver";

    @NotNull
    public static final String JDBC_PASSWORD = "jdbc.password";

    @NotNull
    public static final String JDBC_PASSWORD_DEFAULT = "329354";

    @NotNull
    public static final String JDBC_USER = "jdbc.user";

    @NotNull
    public static final String JDBC_USER_DEFAULT = "root";

    @NotNull
    public static final String PASSWORD_ITERATION = "password.iteration";

    @NotNull
    public static final String PASSWORD_ITERATION_DEFAULT = "1";

    @NotNull
    public static final String PASSWORD_SECRET = "password.secret";

    @NotNull
    public static final String PASSWORD_SECRET_DEFAULT = "";

    @NotNull
    public static final String SERVER_HOST = "server.host";

    @NotNull
    public static final String SERVER_HOST_DEFAULT = "localhost";

    @NotNull
    public static final String SERVER_PORT = "server.port";

    @NotNull
    public static final String SERVER_PORT_DEFAULT = "8080";

    @NotNull
    public static final String SIGN_ITERATION = "sign.iteration";

    @NotNull
    public static final String SIGN_ITERATION_DEFAULT = "1";

    @NotNull
    public static final String SIGN_SECRET = "sign.secret";

    @NotNull
    public static final String SIGN_SECRET_DEFAULT = "";

    @NotNull
    public static final String USE_LITE_MEMBER = "hibernate.cache.hazelcast.use_lite_member";

    @NotNull
    private static final String CACHE_PROVIDER_CONFIG = "cache.config";

    @NotNull
    private static final String CACHE_PROVIDER_CONFIG_DEFAULT = "hazelcast.xml";

    @NotNull
    private static final String CACHE_REGION_FACTORY = "cache.factory";

    @NotNull
    private static final String CACHE_REGION_FACTORY_DEFAULT
            = "com.hazelcast.hibernate.HazelcastLocalCacheRegionFactory";

    @NotNull
    private static final String CACHE_REGION_PREFIX = "cache.prefix";

    @NotNull
    private static final String CACHE_REGION_PREFIX_DEFAULT = "tm";

    @NotNull
    private static final String DIALECT = "factory.dialect";

    @NotNull
    private static final String DIALECT_DEFAULT = "org.hibernate.dialect.MySQL5InnoDBDialect";

    @NotNull
    private static final String HBM2DLL_AUTO = "factory.hbm2dllauto";

    @NotNull
    private static final String HBM2DLL_AUTO_DEFAULT = "update";

    @NotNull
    private static final String JDBC_URL = "jdbc.url";

    @NotNull
    private static final String JDBC_URL_DEFAULT = "jdbc:mysql://localhost:3306/task_manager";

    @NotNull
    private static final String SHOW_SQL = "factory.showsql";

    @NotNull
    private static final String SHOW_SQL_DEFAULT = "true";

    @NotNull
    private static final String USE_LITE_MEMBER_VALUE = "cache.lite-member";

    @NotNull
    private static final String USE_LITE_MEMBER_VALUE_DEFAULT = "true";

    @NotNull
    private static final String USE_MINIMAL_PUTS = "cache.minimal-puts";

    @NotNull
    private static final String USE_MINIMAL_PUTS_DEFAULT = "true";

    @NotNull
    private static final String USE_QUERY_CACHE = "cache.query";

    @NotNull
    private static final String USE_QUERY_CACHE_DEFAULT = "true";

    @NotNull
    private static final String USE_SECOND_LEVEL_CACHE = "cache.level";

    @NotNull
    private static final String USE_SECOND_LEVEL_CACHE_DEFAULT = "true";

    @NotNull
    private final Properties properties = new Properties();

    @SneakyThrows
    public PropertyService() {
        @Nullable final InputStream inputStream = ClassLoader.getSystemResourceAsStream(FILE_NAME);
        if (inputStream == null) return;
        properties.load(inputStream);
        inputStream.close();
    }

    @NotNull
    @Override
    public String getApplicationVersion() {
        if (System.getProperties().containsKey(APPLICATION_VERSION)) return System.getProperty(APPLICATION_VERSION);
        if (System.getenv().containsKey(APPLICATION_VERSION)) return System.getenv(APPLICATION_VERSION);
        return properties.getProperty(APPLICATION_VERSION, APPLICATION_VERSION_DEFAULT);
    }

    @NotNull
    @Override
    public String getCacheProviderConfig() {
        return properties.getProperty(CACHE_PROVIDER_CONFIG, CACHE_PROVIDER_CONFIG_DEFAULT);
    }

    @NotNull
    @Override
    public String getCacheRegionFactory() {
        return properties.getProperty(CACHE_REGION_FACTORY, CACHE_REGION_FACTORY_DEFAULT);
    }

    @NotNull
    @Override
    public String getCacheRegionPrefix() {
        return properties.getProperty(CACHE_REGION_PREFIX, CACHE_REGION_PREFIX_DEFAULT);
    }

    @NotNull
    @Override
    public String getDeveloperEmail() {
        if (System.getProperties().containsKey(DEVELOPER_EMAIL)) return System.getProperty(DEVELOPER_EMAIL);
        if (System.getenv().containsKey(DEVELOPER_EMAIL)) return System.getenv(DEVELOPER_EMAIL);
        return properties.getProperty(DEVELOPER_EMAIL, DEVELOPER_EMAIL_DEFAULT);
    }

    @NotNull
    @Override
    public String getDeveloperName() {
        if (System.getProperties().containsKey(DEVELOPER_NAME)) return System.getProperty(DEVELOPER_NAME);
        if (System.getenv().containsKey(DEVELOPER_NAME)) return System.getenv(DEVELOPER_NAME);
        return properties.getProperty(DEVELOPER_NAME, DEVELOPER_NAME_DEFAULT);
    }

    @NotNull
    @Override
    public String getDialect() {
        return properties.getProperty(DIALECT, DIALECT_DEFAULT);
    }

    @NotNull
    @Override
    public String getHbm2ddlAuto() {
        return properties.getProperty(HBM2DLL_AUTO, HBM2DLL_AUTO_DEFAULT);
    }

    @NotNull
    @Override
    public String getJdbcDriver() {
        return properties.getProperty(JDBC_DRIVER, JDBC_DRIVER_DEFAULT);
    }

    @NotNull
    @Override
    public String getJdbcPassword() {
        return properties.getProperty(JDBC_PASSWORD, JDBC_PASSWORD_DEFAULT);
    }

    @NotNull
    @Override
    public String getJdbcUrl() {
        return properties.getProperty(JDBC_URL, JDBC_URL_DEFAULT);
    }

    @NotNull
    @Override
    public String getJdbcUser() {
        return properties.getProperty(JDBC_USER, JDBC_USER_DEFAULT);
    }

    @Override
    public @NotNull String getServerHost() {
        if (System.getProperties().containsKey(SERVER_HOST)) return System.getProperty(SERVER_HOST);
        if (System.getenv().containsKey(SERVER_HOST)) return System.getenv(SERVER_HOST);
        return properties.getProperty(SERVER_HOST, SERVER_HOST_DEFAULT);
    }

    @Override
    public @NotNull String getServerPort() {
        if (System.getProperties().containsKey(SERVER_PORT)) return System.getProperty(SERVER_PORT);
        if (System.getenv().containsKey(SERVER_PORT)) return System.getenv(SERVER_PORT);
        return properties.getProperty(SERVER_PORT, SERVER_PORT_DEFAULT);
    }

    @NotNull
    @Override
    public String getShowSql() {
        return properties.getProperty(SHOW_SQL, SHOW_SQL_DEFAULT);
    }

    @NotNull
    @Override
    public String getUseLiteMemberValue() {
        return properties.getProperty(USE_LITE_MEMBER_VALUE, USE_LITE_MEMBER_VALUE_DEFAULT);
    }

    @NotNull
    @Override
    public String getUseMinimalPuts() {
        return properties.getProperty(USE_MINIMAL_PUTS, USE_MINIMAL_PUTS_DEFAULT);
    }

    @NotNull
    @Override
    public String getUseQueryCache() {
        return properties.getProperty(USE_QUERY_CACHE, USE_QUERY_CACHE_DEFAULT);
    }

    @NotNull
    @Override
    public String getUseSecondLevelCache() {
        return properties.getProperty(USE_SECOND_LEVEL_CACHE, USE_SECOND_LEVEL_CACHE_DEFAULT);
    }

    @NotNull
    @Override
    public Integer getPasswordIteration() {
        if (System.getProperties().containsKey(PASSWORD_ITERATION)) {
            @NotNull final String value = System.getProperty(PASSWORD_ITERATION);
            return Integer.valueOf(value);
        }
        if (System.getenv().containsKey(PASSWORD_ITERATION)) {
            @NotNull final String value = System.getenv(PASSWORD_ITERATION);
            return Integer.valueOf(value);
        }
        @NotNull final String value = properties.getProperty(PASSWORD_ITERATION, PASSWORD_ITERATION_DEFAULT);
        return Integer.valueOf(value);
    }

    @NotNull
    @Override
    public String getPasswordSecret() {
        if (System.getProperties().containsKey(PASSWORD_SECRET)) return System.getProperty(PASSWORD_SECRET);
        if (System.getenv().containsKey(PASSWORD_SECRET)) return System.getenv(PASSWORD_SECRET);
        return properties.getProperty(PASSWORD_SECRET, PASSWORD_SECRET_DEFAULT);
    }

    @NotNull
    @Override
    public Integer getSignIteration() {
        if (System.getProperties().containsKey(SIGN_ITERATION)) {
            @NotNull final String value = System.getProperty(SIGN_ITERATION);
            return Integer.valueOf(value);
        }
        if (System.getenv().containsKey(SIGN_ITERATION)) {
            @NotNull final String value = System.getenv(SIGN_ITERATION);
            return Integer.valueOf(value);
        }
        @NotNull final String value = properties.getProperty(SIGN_ITERATION, SIGN_ITERATION_DEFAULT);
        return Integer.valueOf(value);
    }

    @NotNull
    @Override
    public String getSignSecret() {
        if (System.getProperties().containsKey(SIGN_SECRET)) return System.getProperty(SIGN_SECRET);
        if (System.getenv().containsKey(SIGN_SECRET)) return System.getenv(SIGN_SECRET);
        return properties.getProperty(SIGN_SECRET, SIGN_SECRET_DEFAULT);
    }

}
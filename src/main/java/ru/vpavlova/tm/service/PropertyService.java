package ru.vpavlova.tm.service;

import lombok.SneakyThrows;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.vpavlova.tm.api.IPropertyService;

import java.io.InputStream;
import java.util.Properties;

public class PropertyService implements IPropertyService {

    @NotNull
    private static final String FILE_NAME = "application.properties";

    @NotNull
    private static final String PASSWORD_SECRET_KEY = "password.secret";

    @NotNull
    private static final String PASSWORD_SECRET_DEFAULT = "";

    @NotNull
    private static final String PASSWORD_ITERATION_KEY = "password.iteration";

    @NotNull
    private static final String PASSWORD_ITERATION_DEFAULT = "1";

    @NotNull
    private static final String APPLICATION_VERSION_KEY = "application.version";

    @NotNull
    private static final String APPLICATION_VERSION_DEFAULT = "";

    @NotNull
    private static final String APPLICATION_DEVELOPER_KEY = "developer.name";

    @NotNull
    private static final String APPLICATION_DEVELOPER_DEFAULT = "Victoria Pavlova";

    @NotNull
    private static final String DEVELOPER_EMAIL_KEY = "developer.email";

    @NotNull
    private static final String DEVELOPER_EMAIL_DEFAULT = "vpavlova@tsconsulting.com";

    @NotNull
    private static final String DEVELOPER_COMPANY_KEY = "developer.company";

    @NotNull
    private static final String DEVELOPER_COMPANY_DEFAULT = "TSC";

    @NotNull
    public static final String SIGN_ITERATION = "sign.iteration";

    @NotNull
    public static final String SIGN_ITERATION_DEFAULT = "1";

    @NotNull
    public static final String SIGN_SECRET = "sign.secret";

    @NotNull
    public static final String SIGN_SECRET_DEFAULT = "";

    @NotNull
    public static final String SERVER_HOST = "server.host";

    @NotNull
    public static final String SERVER_HOST_DEFAULT = "localhost";

    @NotNull
    public static final String SERVER_PORT = "server.port";

    @NotNull
    public static final String SERVER_PORT_DEFAULT = "8080";

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
    public String getPasswordSecret() {
        if (System.getProperties().containsKey(PASSWORD_SECRET_KEY))
            return System.getProperty(PASSWORD_SECRET_KEY);
        return properties.getProperty(PASSWORD_SECRET_KEY, PASSWORD_SECRET_DEFAULT);
    }

    @NotNull
    @Override
    public Integer getPasswordIteration() {
        if (System.getProperties().containsKey(PASSWORD_ITERATION_KEY)) {
            final String value = System.getProperty(PASSWORD_ITERATION_KEY);
            return Integer.parseInt(value);
        }
        if (System.getenv().containsKey(PASSWORD_ITERATION_KEY)) {
            final String value = System.getenv(PASSWORD_ITERATION_KEY);
            return Integer.parseInt(value);
        }
        final String value = properties.getProperty(PASSWORD_ITERATION_KEY, PASSWORD_ITERATION_DEFAULT);
        return Integer.parseInt(value);
    }

    @NotNull
    @Override
    public String getApplicationVersion() {
        if (System.getProperties().containsKey(APPLICATION_VERSION_KEY))
            return System.getProperty(APPLICATION_VERSION_KEY);
        if (System.getenv().containsKey(APPLICATION_VERSION_KEY))
            return System.getenv(APPLICATION_VERSION_KEY);
        return properties.getProperty(APPLICATION_VERSION_KEY, APPLICATION_VERSION_DEFAULT);
    }

    @NotNull
    @Override
    public String getDeveloperName() {
        if (System.getProperties().containsKey(APPLICATION_DEVELOPER_KEY))
            return System.getProperty(APPLICATION_DEVELOPER_KEY);
        if (System.getenv().containsKey(APPLICATION_DEVELOPER_KEY))
            return System.getenv(APPLICATION_DEVELOPER_KEY);
        return properties.getProperty(APPLICATION_DEVELOPER_KEY, APPLICATION_DEVELOPER_DEFAULT);
    }

    @NotNull
    @Override
    public String getDeveloperEmail() {
        if (System.getProperties().containsKey(DEVELOPER_EMAIL_KEY))
            return System.getProperty(DEVELOPER_EMAIL_KEY);
        if (System.getenv().containsKey(DEVELOPER_EMAIL_KEY))
            return System.getenv(DEVELOPER_EMAIL_KEY);
        return properties.getProperty(DEVELOPER_EMAIL_KEY, DEVELOPER_EMAIL_DEFAULT);
    }

    @NotNull
    @Override
    public String getDeveloperCompany() {
        if (System.getProperties().containsKey(DEVELOPER_COMPANY_KEY))
            return System.getProperty(DEVELOPER_COMPANY_KEY);
        if (System.getenv().containsKey(DEVELOPER_COMPANY_KEY))
            return System.getenv(DEVELOPER_COMPANY_KEY);
        return properties.getProperty(DEVELOPER_COMPANY_KEY, DEVELOPER_COMPANY_DEFAULT);
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
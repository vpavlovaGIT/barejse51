package ru.vpavlova.tm.api;

import org.jetbrains.annotations.NotNull;

public interface IPropertyService extends ISaltSetting {

    @NotNull
    String getApplicationVersion();

    @NotNull String getCacheProviderConfig();

    @NotNull String getCacheRegionFactory();

    @NotNull String getCacheRegionPrefix();

    @NotNull
    String getDeveloperEmail();

    @NotNull
    String getDeveloperName();

    @NotNull
    String getDialect();

    @NotNull
    String getHbm2ddlAuto();

    @NotNull
    String getJdbcDriver();

    @NotNull
    String getJdbcPassword();

    @NotNull
    String getJdbcUrl();

    @NotNull
    String getJdbcUser();

    @NotNull
    String getServerHost();

    @NotNull
    String getServerPort();

    @NotNull
    String getShowSql();

    @NotNull String getUseLiteMemberValue();

    @NotNull String getUseMinimalPuts();

    @NotNull String getUseQueryCache();

    @NotNull String getUseSecondLevelCache();

}

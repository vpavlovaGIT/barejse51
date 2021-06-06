package ru.vpavlova.tm.api.service;

import org.apache.ibatis.session.SqlSession;
import org.jetbrains.annotations.NotNull;

public interface IConnectionService {

    @NotNull
    SqlSession getSqlSession();

}

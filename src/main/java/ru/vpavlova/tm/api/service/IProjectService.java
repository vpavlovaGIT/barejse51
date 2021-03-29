package ru.vpavlova.tm.api.service;

import ru.vpavlova.tm.api.IBusinessService;
import ru.vpavlova.tm.entity.Project;

import java.util.Comparator;
import java.util.List;

public interface IProjectService extends IBusinessService<Project> {

    Project add(String userId, String name, String description);

}

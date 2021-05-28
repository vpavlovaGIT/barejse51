package ru.vpavlova.tm.reposotory;

import lombok.SneakyThrows;
import org.jetbrains.annotations.NotNull;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import ru.vpavlova.tm.api.IPropertyService;
import ru.vpavlova.tm.api.repository.IUserRepository;
import ru.vpavlova.tm.api.service.IConnectionService;
import ru.vpavlova.tm.entity.Task;
import ru.vpavlova.tm.entity.User;
import ru.vpavlova.tm.marker.DBCategory;
import ru.vpavlova.tm.marker.UnitCategory;
import ru.vpavlova.tm.repository.UserRepository;
import ru.vpavlova.tm.service.ConnectionService;
import ru.vpavlova.tm.service.PropertyService;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public class UserRepositoryTest {

    @NotNull
    private final IPropertyService propertyService = new PropertyService();

    @NotNull
    private final IConnectionService connectionService = new ConnectionService(propertyService);

    final Connection connection = connectionService.getConnection();

    final IUserRepository userRepository = new UserRepository(connection);

    @After
    @SneakyThrows
    public void after() {
        connection.commit();
    }

    @Test
    @Category(DBCategory.class)
    public void addAllUsersTest() {
        final List<User> users = new ArrayList<>();
        final User user1 = new User();
        final User user2 = new User();
        users.add(user1);
        users.add(user2);
        userRepository.addAll(users);
        Assert.assertTrue(userRepository.findById(user1.getId()).isPresent());
        Assert.assertTrue(userRepository.findById(user2.getId()).isPresent());
    }

    @Test
    @Category(DBCategory.class)
    public void addUserTest() {
        final User user = new User();
        Assert.assertNotNull(userRepository.add(user));
    }

    @Test
    @Category(DBCategory.class)
    public void clearTest() {
        userRepository.clear();
        Assert.assertTrue(userRepository.findAll().isEmpty());
    }

    @Test
    @Category(DBCategory.class)
    public void findAllUsers() {
        final List<User> users = new ArrayList<>();
        final User userOne = new User();
        final User userTwo = new User();
        users.add(userOne);
        users.add(userTwo);
        userRepository.addAll(users);
        Assert.assertEquals(2, userRepository.findAll().size());
    }

    @Test
    @Category(DBCategory.class)
    public void findUserOneByIdTest() {
        final User user1 = new User();
        final String userId = user1.getId();
        userRepository.add(user1);
        Assert.assertNotNull(userRepository.findById(userId));
    }

    @Test
    @Category(DBCategory.class)
    public void removeUserOneByIdTest() {
        final User user1 = new User();
        userRepository.add(user1);
        final String userId = user1.getId();
        userRepository.removeById(userId);
        Assert.assertNull(userRepository.findById(userId));
    }

    @Test
    @Category(DBCategory.class)
    public void removeUserTest() {
        final List<User> users = new ArrayList<>();
        for (@NotNull final User user : users) {
            Assert.assertNotNull(userRepository.removeById(user.getId()));
            Assert.assertNull(userRepository.findById(user.getId()));
        }
    }

}

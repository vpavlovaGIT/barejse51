package ru.vpavlova.tm.service.dto;

import org.jetbrains.annotations.NotNull;
import org.junit.Assert;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import ru.vpavlova.tm.api.IPropertyService;
import ru.vpavlova.tm.api.service.IConnectionService;
import ru.vpavlova.tm.api.service.dto.IUserDTOService;
import ru.vpavlova.tm.dto.UserDTO;
import ru.vpavlova.tm.marker.DBCategory;
import ru.vpavlova.tm.service.ConnectionService;
import ru.vpavlova.tm.service.PropertyService;

import java.util.ArrayList;
import java.util.List;

public class UserDTOServiceTest {

    @NotNull
    private final IPropertyService propertyService = new PropertyService();

    @NotNull
    private final IConnectionService connectionService = new ConnectionService(propertyService);

    @NotNull
    private final IUserDTOService userService = new UserDTOService(propertyService, connectionService);

    @Test
    @Category(DBCategory.class)
    public void addAllTest() {
        final List<UserDTO> users = new ArrayList<>();
        final UserDTO user1 = new UserDTO();
        final UserDTO user2 = new UserDTO();
        users.add(user1);
        users.add(user2);
        userService.addAll(users);
        Assert.assertTrue(userService.findOneById(user1.getId()).isPresent());
        Assert.assertTrue(userService.findOneById(user2.getId()).isPresent());
        userService.remove(users.get(0));
        userService.remove(users.get(1));
    }

    @Test
    @Category(DBCategory.class)
    public void addTest() {
        final UserDTO user = new UserDTO();
        userService.add(user);
        Assert.assertNotNull(userService.findOneById(user.getId()));
        userService.remove(user);
    }

    @Test
    @Category(DBCategory.class)
    public void findAll() {
        final int userSize = userService.findAll().size();
        userService.create("testFindAll", "test", "-");
        Assert.assertEquals(userSize + 1, userService.findAll().size());
        userService.removeByLogin("testFindAll");
    }

    @Test
    @Category(DBCategory.class)
    public void findByLogin() {
        final UserDTO user = new UserDTO();
        user.setLogin("testFindL");
        userService.add(user);
        final String login = user.getLogin();
        Assert.assertNotNull(login);
        Assert.assertTrue(userService.findByLogin(login).isPresent());
        userService.removeByLogin("testFindL");
    }

    @Test
    @Category(DBCategory.class)
    public void findOneByIdTest() {
        final UserDTO user = new UserDTO();
        final String userId = user.getId();
        userService.add(user);
        Assert.assertNotNull(userService.findOneById(userId));
        userService.remove(user);
    }

    @Test
    @Category(DBCategory.class)
    public void findOneByIndexTest() {
        final UserDTO user = new UserDTO();
        userService.add(user);
        final String userId = user.getId();
        Assert.assertTrue(userService.findOneById(userId).isPresent());
        userService.remove(user);
    }

    @Test
    @Category(DBCategory.class)
    public void isLoginExist() {
        final UserDTO user = new UserDTO();
        user.setLogin("testExist");
        userService.add(user);
        final String login = user.getLogin();
        Assert.assertNotNull(login);
        Assert.assertTrue(userService.isLoginExist(login));
        userService.remove(user);
    }

    @Test
    @Category(DBCategory.class)
    public void removeByLogin() {
        final UserDTO user = new UserDTO();
        user.setLogin("testRemoveByLogin");
        userService.add(user);
        final String login = user.getLogin();
        Assert.assertNotNull(login);
        userService.removeByLogin(login);
        Assert.assertFalse(userService.isLoginExist(login));
    }

    @Test
    @Category(DBCategory.class)
    public void removeOneByIdTest() {
        final UserDTO user = new UserDTO();
        userService.add(user);
        final String userId = user.getId();
        userService.removeOneById(userId);
        Assert.assertFalse(userService.findOneById(userId).isPresent());
    }

    @Test
    @Category(DBCategory.class)
    public void removeTest() {
        final UserDTO user = new UserDTO();
        userService.add(user);
        userService.remove(user);
        Assert.assertNotNull(userService.findOneById(user.getId()));
    }

}

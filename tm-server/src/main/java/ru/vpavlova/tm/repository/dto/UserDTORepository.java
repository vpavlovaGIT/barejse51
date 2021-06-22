package ru.vpavlova.tm.repository.dto;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.vpavlova.tm.api.repository.dto.IUserDTORepository;
import ru.vpavlova.tm.dto.UserDTO;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class UserDTORepository extends AbstractDTORepository<UserDTO> implements IUserDTORepository {

    public UserDTORepository(@NotNull EntityManager entityManager) {
        super(entityManager);
    }

    @NotNull
    public List<UserDTO> findAll() {
        return entityManager.createQuery("SELECT e FROM UserDTO e", UserDTO.class).getResultList();
    }

    @Override
    public @NotNull Optional<UserDTO> findById(@Nullable final String id) {
        return Optional.ofNullable(entityManager.find(UserDTO.class, id));
    }

    public void clear() {
        entityManager
                .createQuery("DELETE FROM UserDTO e")
                .executeUpdate();
    }

    public void removeOneById(@Nullable final String id) {
        UserDTO reference = entityManager.getReference(UserDTO.class, id);
        entityManager.remove(reference);
    }

    @Override
    public @NotNull Optional<UserDTO> findByLogin(@Nullable final String login) {
        return getSingleResult(entityManager
                .createQuery("SELECT e FROM UserDTO e WHERE e.login = :login", UserDTO.class)
                .setParameter("login", login)
                .setMaxResults(1));
    }

    @Override
    public void removeByLogin(@Nullable final String login) {
        entityManager
                .createQuery("DELETE FROM UserDTO e WHERE e.login = :login")
                .setParameter("login", login)
                .executeUpdate();
    }

}

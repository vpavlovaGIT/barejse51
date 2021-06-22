package ru.vpavlova.tm.repository.dto;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.vpavlova.tm.api.repository.dto.ISessionDTORepository;
import ru.vpavlova.tm.dto.SessionDTO;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class SessionDTORepository extends AbstractDTORepository<SessionDTO> implements ISessionDTORepository {

    public SessionDTORepository(@NotNull EntityManager entityManager) {
        super(entityManager);
    }

    @NotNull
    public List<SessionDTO> findAll() {
        return entityManager.createQuery("SELECT e FROM SessionDTO e", SessionDTO.class).getResultList();
    }

    public @NotNull Optional<SessionDTO> findOneById(@Nullable final String id) {
        return Optional.ofNullable(entityManager.find(SessionDTO.class, id));
    }

    public void clear() {
        entityManager
                .createQuery("DELETE FROM SessionDTO e")
                .executeUpdate();
    }

    public void removeOneById(@Nullable final String id) {
        SessionDTO reference = entityManager.getReference(SessionDTO.class, id);
        entityManager.remove(reference);
    }

}

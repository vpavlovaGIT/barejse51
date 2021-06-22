package ru.vpavlova.tm.api.repository.dto;

import org.jetbrains.annotations.NotNull;
import ru.vpavlova.tm.dto.AbstractEntityDTO;

import javax.persistence.TypedQuery;
import java.util.Optional;

public interface IRepositoryDTO<E extends AbstractEntityDTO> {

    void add(@NotNull E entity);

    @NotNull Optional<E> getSingleResult(@NotNull TypedQuery<E> query);

    void update(E entity);

}

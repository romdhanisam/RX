package eu.yayi.commons.jpa.persistence;

import eu.yayi.commons.jpa.persistence.entities.ModelObject;
import org.springframework.data.jpa.repository.JpaRepository;

import java.io.Serializable;

/**
 * Samir
 * @param <T>
 * @param <S>
 */
public interface BaseDao<T extends ModelObject<S>, S extends Serializable> extends JpaRepository<T, Long> {
}

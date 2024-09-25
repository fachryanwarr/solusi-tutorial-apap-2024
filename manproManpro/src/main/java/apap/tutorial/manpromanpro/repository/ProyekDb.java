package apap.tutorial.manpromanpro.repository;

import apap.tutorial.manpromanpro.model.Developer;
import apap.tutorial.manpromanpro.model.Proyek;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ProyekDb extends JpaRepository<Proyek, UUID> {
    List<Proyek> findByDeletedAtNull();

    List<Proyek> findByDeletedAtNull(Sort sort);

    List<Proyek> findByDeletedAtNullAndStatus(Integer status, Sort sort);

    List<Proyek> findByDeletedAtNullAndNamaContainsIgnoreCase(String nama, Sort sort);

    List<Proyek> findByDeletedAtNullAndNamaContainsIgnoreCaseAndStatus(String nama, Integer status, Sort sort);

    List<Proyek> findByDeletedAtNullAndDeveloper(Developer developer);
}

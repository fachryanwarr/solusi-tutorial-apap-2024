package apap.tutorial.manpromanpro.repository;

import apap.tutorial.manpromanpro.model.Developer;
import apap.tutorial.manpromanpro.model.Proyek;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ProyekDb extends JpaRepository<Proyek, UUID> {
    List<Proyek> findByDeletedAtNull();

    List<Proyek> findByDeletedAtNullAndDeveloper(Developer developer);
}

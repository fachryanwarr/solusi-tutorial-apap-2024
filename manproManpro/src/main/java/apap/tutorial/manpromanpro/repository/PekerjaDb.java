package apap.tutorial.manpromanpro.repository;

import apap.tutorial.manpromanpro.model.Pekerja;
import apap.tutorial.manpromanpro.model.Proyek;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface PekerjaDb extends JpaRepository<Pekerja, Long> {
    List<Pekerja> findByDeletedAtNull();
}

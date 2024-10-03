package apap.tutorial.manpromanpro.service;

import apap.tutorial.manpromanpro.model.Pekerja;
import apap.tutorial.manpromanpro.repository.PekerjaDb;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class PekerjaServiceImpl implements PekerjaService {
    @Autowired
    private PekerjaDb pekerjaDb;

    @Override
    public Pekerja addPekerja(Pekerja pekerja) {
        return pekerjaDb.save(pekerja);
    }

    @Override
    public List<Pekerja> getAllPekerja() {
        return pekerjaDb.findByDeletedAtNull();
    }

    @Override
    public Pekerja getPekerjaById(Long id) {
        var pekerja = pekerjaDb.findById(id);
        if (pekerja.isPresent()) {
            if (pekerja.get().getDeletedAt() == null) {
                return pekerja.get();
            }
        };

        throw new EntityNotFoundException("Pekerja tidak ditemukan");
    }

    @Override
    @Transactional
    public void deleteListPekerja(List<Pekerja> pekerjaList) {
        var pekerjaToDelete = new ArrayList<Pekerja>();

        if (pekerjaList != null) {
            for (Pekerja pekerja : pekerjaList) {
                if (pekerja.getListProyek() == null || pekerja.getListProyek().isEmpty()) {
                    pekerja.setDeletedAt(new Date());
                    pekerjaToDelete.add(pekerja);
                }
            }
        }

        pekerjaDb.saveAll(pekerjaToDelete);
    }
}

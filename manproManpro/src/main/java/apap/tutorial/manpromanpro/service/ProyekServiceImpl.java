package apap.tutorial.manpromanpro.service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import apap.tutorial.manpromanpro.model.Developer;
import apap.tutorial.manpromanpro.repository.ProyekDb;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import apap.tutorial.manpromanpro.model.Proyek;

@Service
public class ProyekServiceImpl implements ProyekService {
    @Autowired
    private ProyekDb proyekDb;

    @Override
    public void createProyek(Proyek proyek) {
        validateProject(proyek);
        proyekDb.save(proyek);
    }

    @Override
    public List<Proyek> getAllProyek() {
        return proyekDb.findByDeletedAtNull();
    }

    @Override
    public Proyek getProyekById(UUID id) {
        var proyek = proyekDb.findById(id);
        if (proyek.isPresent()) {
            if (proyek.get().getDeletedAt() == null) {
                return proyek.get();
            }
        }
        throw new EntityNotFoundException("Project tidak ditemukan");
    }

    @Override
    public void updateProject(Proyek proyek) {
        validateProject(proyek);
        var projectData = getProyekById(proyek.getId());

        projectData.setNama(proyek.getNama());
        projectData.setDeskripsi(proyek.getDeskripsi());
        projectData.setTanggalMulai(proyek.getTanggalMulai());
        projectData.setTanggalSelesai(proyek.getTanggalSelesai());
        projectData.setStatus(proyek.getStatus());
        projectData.setDeveloper(proyek.getDeveloper());

        proyekDb.save(projectData);
    }

    @Override
    public void deleteProject(UUID id) {
        var project = getProyekById(id);
        project.setDeletedAt(new Date());
        proyekDb.save(project);
    }

    @Override
    public List<Proyek> getByDeveloper(Developer developer) {
        return proyekDb.findByDeletedAtNullAndDeveloper(developer);
    }

    private void validateProject(Proyek proyek) {
        if (proyek.getTanggalSelesai().before(proyek.getTanggalMulai())) {
            throw new RuntimeException("Tanggal selesai harus setelah tanggal mulai");
        }
    }
}

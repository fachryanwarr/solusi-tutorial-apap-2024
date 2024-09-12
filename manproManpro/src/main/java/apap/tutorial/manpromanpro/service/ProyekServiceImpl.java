package apap.tutorial.manpromanpro.service;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

import apap.tutorial.manpromanpro.dto.ProjectResponseDTO;
import org.springframework.stereotype.Service;
import apap.tutorial.manpromanpro.model.Proyek;

@Service
public class ProyekServiceImpl implements ProyekService {
    private final List<Proyek> listProyek = new ArrayList<Proyek>();

    @Override
    public void createProyek(Proyek proyek) {
        validateProject(proyek);
        listProyek.add(proyek);
    }

    @Override
    public List<Proyek> getAllProyek() {
        return listProyek;
    }

    @Override
    public Proyek getProyekById(UUID id) {
        for (Proyek proyek : listProyek) {
            if (proyek.getId().equals(id)) {
                return proyek;
            }
        }
        throw new RuntimeException("Project tidak ditemukan");
    }

    @Override
    public void updateProject(Proyek proyek) {
        validateProject(proyek);
        var projectIndex = getProjectIndex(proyek);
        listProyek.set(projectIndex, proyek);
    }

    private Integer getProjectIndex(Proyek proyek) {
        for (int i = 0; i < listProyek.size(); i++) {
            if (listProyek.get(i).getId().equals(proyek.getId())) {
                return i;
            }
        }
        throw new RuntimeException("Project tidak ditemukan");
    }

    @Override
    public void deleteProject(UUID id) {
        var project = getProyekById(id);
        listProyek.remove(project);
    }


    private Integer getProjectIndex(UUID id) {
        for (int i = 0; i < listProyek.size(); i++) {
            if (listProyek.get(i).getId().equals(id)) {
                return i;
            }
        }
        throw new RuntimeException("Project tidak ditemukan");
    }

    private void validateProject(Proyek proyek) {
        if (proyek.getTanggalSelesai().before(proyek.getTanggalMulai())) {
            throw new RuntimeException("Tanggal selesai harus setelah tanggal mulai");
        }
    }

    @Override
    public ProjectResponseDTO getProjectResponse(Proyek proyek) {
        var proyekResponse = new ProjectResponseDTO();
        proyekResponse.setId(proyek.getId());
        proyekResponse.setDeveloper(proyek.getDeveloper());
        proyekResponse.setNama(proyek.getNama());
        proyekResponse.setTanggalMulai(convertDateToString(proyek.getTanggalMulai()));
        proyekResponse.setTanggalSelesai(convertDateToString(proyek.getTanggalSelesai()));
        proyekResponse.setStatus(getStatusStr(proyek.getStatus()));

        return proyekResponse;
    }

    private static String convertDateToString(Date sqlDate) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd MMMM yyyy", new Locale("id", "ID"));
        return formatter.format(sqlDate);
    }

    private static String getStatusStr(Integer status) {
        return switch (status) {
            case 1 -> "On Going";
            case 2 -> "Finished";
            default -> "Invalid";
        };
    }
}

package apap.tutorial.manpromanpro.dto.mapper;

import apap.tutorial.manpromanpro.dto.request.AddProjectRequestDTO;
import apap.tutorial.manpromanpro.dto.response.ProjectResponseDTO;
import apap.tutorial.manpromanpro.dto.request.UpdateProjectRequestDTO;
import apap.tutorial.manpromanpro.model.Proyek;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Locale;

@Component
public class ProyekMapper {
    public Proyek addProjectDTOToProject(AddProjectRequestDTO projectDTO) {
        var project = new Proyek();
        project.setNama(projectDTO.getNama());
        project.setTanggalMulai(projectDTO.getTanggalMulai());
        project.setTanggalSelesai(projectDTO.getTanggalSelesai());
        project.setStatus(projectDTO.getStatus());
        project.setDeskripsi(projectDTO.getDeskripsi());
        project.setDeveloper(projectDTO.getDeveloper());
        project.setListPekerja(projectDTO.getListPekerja());

        return project;
    }

    public Proyek updateProjectDTOToProyek(UpdateProjectRequestDTO projectRequestDTO) {
        var project = new Proyek();
        project.setId(projectRequestDTO.getId());
        project.setNama(projectRequestDTO.getNama());
        project.setTanggalMulai(projectRequestDTO.getTanggalMulai());
        project.setTanggalSelesai(projectRequestDTO.getTanggalSelesai());
        project.setStatus(projectRequestDTO.getStatus());
        project.setDeskripsi(projectRequestDTO.getDeskripsi());
        project.setDeveloper(projectRequestDTO.getDeveloper());

        return project;
    }

    public UpdateProjectRequestDTO proyekToUpdateProjectDTO(Proyek proyek) {
        var proyekDTO = new UpdateProjectRequestDTO();
        proyekDTO.setId(proyek.getId());
        proyekDTO.setNama(proyek.getNama());
        proyekDTO.setTanggalMulai(proyek.getTanggalMulai());
        proyekDTO.setTanggalSelesai(proyek.getTanggalSelesai());
        proyekDTO.setStatus(proyek.getStatus());
        proyekDTO.setDeskripsi(proyek.getDeskripsi());
        proyekDTO.setDeveloper(proyek.getDeveloper());

        return proyekDTO;
    }

    public ProjectResponseDTO proyekToProjectResponseDTO(Proyek proyek) {
        var projectResponse = new ProjectResponseDTO();

        var projectStatusStr = switch (proyek.getStatus()) {
            case 1 -> "On Going";
            case 2 -> "Finished";
            default -> "Invalid";
        };
        SimpleDateFormat formatter = new SimpleDateFormat("dd MMMM yyyy", new Locale("id", "ID"));
        SimpleDateFormat formatterDatetime = new SimpleDateFormat("MMMM dd, yyyy | HH:mm:ss");

        projectResponse.setId(proyek.getId());
        projectResponse.setNama(proyek.getNama());
        projectResponse.setTanggalMulaiStr(formatter.format(proyek.getTanggalMulai()));
        projectResponse.setTanggalSelesaiStr(formatter.format(proyek.getTanggalSelesai()));
        projectResponse.setStatusStr(projectStatusStr);
        projectResponse.setDeskripsi(proyek.getDeskripsi());
        projectResponse.setDeveloper(proyek.getDeveloper());
        projectResponse.setListPekerja(proyek.getListPekerja());
        projectResponse.setCreatedAt(formatterDatetime.format(proyek.getCreatedAt()));
        projectResponse.setUpdatedAt(formatterDatetime.format(proyek.getUpdatedAt()));

        return projectResponse;
    }
}

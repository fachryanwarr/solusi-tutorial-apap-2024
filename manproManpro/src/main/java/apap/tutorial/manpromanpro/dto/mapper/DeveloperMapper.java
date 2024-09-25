package apap.tutorial.manpromanpro.dto.mapper;

import apap.tutorial.manpromanpro.dto.request.AddDeveloperRequestDTO;
import apap.tutorial.manpromanpro.dto.request.UpdateDeveloperRequestDTO;
import apap.tutorial.manpromanpro.dto.response.DeveloperResponseDTO;
import apap.tutorial.manpromanpro.model.Developer;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Locale;

@Component
public class DeveloperMapper {
    public Developer developerRequestDTOToDeveloper(AddDeveloperRequestDTO developerRequestDTO) {
        var developer = new Developer();

        developer.setNama(developerRequestDTO.getNama());
        developer.setAlamat(developerRequestDTO.getAlamat());
        developer.setTanggalBerdiri(developerRequestDTO.getTanggalBerdiri());
        developer.setEmail(developerRequestDTO.getEmail());

        return developer;
    }

    public DeveloperResponseDTO developerToDeveloperResponse(Developer developer) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd MMMM yyyy", new Locale("id", "ID"));
        SimpleDateFormat formatterDatetime = new SimpleDateFormat("MMMM dd, yyyy | HH:mm:ss");

        var developerResponse = new DeveloperResponseDTO();
        developerResponse.setId(developer.getId());
        developerResponse.setNama(developer.getNama());
        developerResponse.setAlamat(developer.getAlamat());
        developerResponse.setEmail(developer.getEmail());
        developerResponse.setListProyek(developer.getListProyek());
        developerResponse.setTanggalBerdiri(formatter.format(developer.getTanggalBerdiri()));
        developerResponse.setCreatedAt(formatterDatetime.format(developer.getCreatedAt()));
        developerResponse.setUpdatedAt(formatterDatetime.format(developer.getUpdatedAt()));

        return developerResponse;
    }

    public UpdateDeveloperRequestDTO developerToUpdateDeveloperDTO(Developer developer) {
        var updateDTO = new UpdateDeveloperRequestDTO();
        updateDTO.setId(developer.getId());
        updateDTO.setNama(developer.getNama());
        updateDTO.setAlamat(developer.getAlamat());
        updateDTO.setEmail(developer.getEmail());
        updateDTO.setTanggalBerdiri(developer.getTanggalBerdiri());

        return updateDTO;
    }
}

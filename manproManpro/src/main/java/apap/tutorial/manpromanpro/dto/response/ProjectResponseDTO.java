package apap.tutorial.manpromanpro.dto.response;

import apap.tutorial.manpromanpro.model.Developer;
import apap.tutorial.manpromanpro.model.Pekerja;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProjectResponseDTO {
    private UUID id;
    private String nama;
    private String description;
    private String tanggalMulaiStr;
    private String deskripsi;
    private String tanggalSelesaiStr;
    private String statusStr;
    private String createdAt;
    private String updatedAt;
    private Developer developer;
    private List<Pekerja> listPekerja;
}

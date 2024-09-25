package apap.tutorial.manpromanpro.dto.response;

import apap.tutorial.manpromanpro.model.Proyek;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeveloperResponseDTO {
    private Long id;

    private String nama;

    private String alamat;

    private String tanggalBerdiri;

    private String email;

    private String createdAt;

    private String updatedAt;

    private List<Proyek> listProyek;
}

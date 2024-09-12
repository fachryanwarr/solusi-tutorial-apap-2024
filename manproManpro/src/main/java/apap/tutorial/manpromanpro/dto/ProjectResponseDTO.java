package apap.tutorial.manpromanpro.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProjectResponseDTO {
    private UUID id;
    private String nama;
    private String tanggalMulai;
    private String tanggalSelesai;
    private String status;
    private String developer;
}

package apap.tutorial.manpromanpro.controller.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProyekDTO {
    private UUID id;
    private String nama;
    private String tanggalMulai;
    private String tanggalSelesai;
    private String status;
    private String developer;
}

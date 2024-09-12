package apap.tutorial.manpromanpro.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Proyek {
    private UUID id;
    private String nama;
    private String tanggalMulai;
    private String tanggalSelesai;
    private String status;
    private String developer;
}

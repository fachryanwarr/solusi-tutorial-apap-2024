package apap.tutorial.manpromanpro.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Proyek {
    private UUID id;
    private String nama;
    private Date tanggalMulai;
    private Date tanggalSelesai;
    private Integer status;
    private String developer;
}

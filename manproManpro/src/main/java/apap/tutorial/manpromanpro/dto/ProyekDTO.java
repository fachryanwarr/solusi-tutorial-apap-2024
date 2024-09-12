package apap.tutorial.manpromanpro.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProyekDTO {
    private UUID id;
    private String nama;
    private Date tanggalMulai;
    private Date tanggalSelesai;
    private Integer status;
    private String developer;
}

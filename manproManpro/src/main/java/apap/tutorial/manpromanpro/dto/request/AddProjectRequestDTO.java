package apap.tutorial.manpromanpro.dto.request;

import apap.tutorial.manpromanpro.model.Developer;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AddProjectRequestDTO {
    @NotNull(message = "Nama must not be null")
    private String nama;

    @NotNull(message = "Deskripsi must not be null")
    private String deskripsi;

    @NotNull(message = "Tanggal mulai must not be null")
    private Date tanggalMulai;

    @NotNull(message = "Tanggal selesai must not be null")
    private Date tanggalSelesai;

    @NotNull(message = "Status must not be null")
    private Integer status;

    @NotNull(message = "Developer must not be null")
    private Developer developer;
}

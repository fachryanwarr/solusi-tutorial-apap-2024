package apap.tutorial.manpromanpro.dto.request;

import apap.tutorial.manpromanpro.model.Developer;
import apap.tutorial.manpromanpro.model.Pekerja;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

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

    @NotNull(message = "List pekerja must not be null")
    private List<Pekerja> listPekerja = new ArrayList<>();
}

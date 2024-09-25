package apap.tutorial.manpromanpro.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateDeveloperRequestDTO {
    @NotNull(message = "ID tidak boleh null")
    private Long id;

    @NotNull(message = "Nama tidak boleh null")
    @NotBlank(message = "Nama tidak boleh kosong")
    private String nama;

    @NotNull(message = "Alamat tidak boleh null")
    @NotBlank(message = "Alamat tidak boleh kosong")
    private String alamat;

    @NotNull(message = "Tanggal berdiri tidak boleh null")
    private Date tanggalBerdiri;

    @NotNull(message = "Email tidak boleh null")
    @NotBlank(message = "Email tidak boleh kosong")
    private String email;
}

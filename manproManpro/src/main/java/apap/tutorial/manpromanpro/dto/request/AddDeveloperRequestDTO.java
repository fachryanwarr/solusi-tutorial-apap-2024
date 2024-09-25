package apap.tutorial.manpromanpro.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddDeveloperRequestDTO {
    @NotNull(message = "Nama must not be null")
    private String nama;

    @NotNull(message = "Alamat must not be null")
    private String alamat;

    @NotNull(message = "Tanggal berdiri must not be null")
    private Date tanggalBerdiri;

    @Email(message = "Format email belum sesuai")
    @NotNull(message = "Email must not be null")
    private String email;
}

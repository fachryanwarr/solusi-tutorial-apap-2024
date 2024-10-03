package apap.tutorial.manpromanpro.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddPekerjaRequestDTO {
    @NotNull(message = "Nama tidak boleh null")
    @NotBlank(message = "Nama tidak boleh kosong")
    private String nama;

    @NotNull(message = "Usia tidak boleh null")
    @Positive(message = "Usia invalid kocak")
    private Integer usia;

    @NotNull(message = "Pekerjaan tidak boleh null")
    @NotBlank(message = "Pekerjaan tidak boleh kosong")
    private String pekerjaan;

    @NotNull(message = "Biografi tidak boleh null")
    @NotBlank(message = "Biografi tidak boleh kosong")
    private String biografi;
}

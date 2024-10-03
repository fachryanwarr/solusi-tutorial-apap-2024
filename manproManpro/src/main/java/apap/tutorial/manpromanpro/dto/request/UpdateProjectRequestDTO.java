package apap.tutorial.manpromanpro.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateProjectRequestDTO extends AddProjectRequestDTO {
    @NotNull(message = "ID must not be null")
    private UUID id;
}

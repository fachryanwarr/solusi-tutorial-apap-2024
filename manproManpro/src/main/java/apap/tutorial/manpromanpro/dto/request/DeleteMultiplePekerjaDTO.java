package apap.tutorial.manpromanpro.dto.request;

import apap.tutorial.manpromanpro.model.Pekerja;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeleteMultiplePekerjaDTO {
    private List<Pekerja> listPekerja = new ArrayList<>();
}

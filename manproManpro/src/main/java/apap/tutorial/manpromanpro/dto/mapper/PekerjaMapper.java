package apap.tutorial.manpromanpro.dto.mapper;

import apap.tutorial.manpromanpro.dto.request.AddPekerjaRequestDTO;
import apap.tutorial.manpromanpro.model.Pekerja;
import org.springframework.stereotype.Component;

@Component
public class PekerjaMapper {
    public Pekerja pekerjaRequestDTOToPekerja(AddPekerjaRequestDTO pekerjaDTO) {
        var pekerja = new Pekerja();
        pekerja.setNama(pekerjaDTO.getNama());
        pekerja.setPekerjaan(pekerjaDTO.getPekerjaan());
        pekerja.setUsia(pekerjaDTO.getUsia());
        pekerja.setBiografi(pekerjaDTO.getBiografi());

        return pekerja;
    }
}

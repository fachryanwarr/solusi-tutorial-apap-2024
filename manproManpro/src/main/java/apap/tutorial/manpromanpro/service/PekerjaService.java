package apap.tutorial.manpromanpro.service;

import apap.tutorial.manpromanpro.model.Pekerja;

import java.util.List;

public interface PekerjaService {
    Pekerja addPekerja(Pekerja pekerja);

    List<Pekerja> getAllPekerja();

    Pekerja getPekerjaById(Long id);

    void deleteListPekerja(List<Pekerja> pekerjaList);
}

package apap.tutorial.manpromanpro.service;

import java.util.List;
import java.util.UUID;

import apap.tutorial.manpromanpro.model.Developer;
import apap.tutorial.manpromanpro.model.Proyek;

public interface ProyekService {
    void createProyek(Proyek proyek);

    List<Proyek> getAllProyek();

    Proyek getProyekById(UUID id);

    void updateProject(Proyek proyek);

    void deleteProject(UUID id);

    List<Proyek> getByDeveloper(Developer developer);
}

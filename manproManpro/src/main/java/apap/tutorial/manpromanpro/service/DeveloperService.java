package apap.tutorial.manpromanpro.service;

import apap.tutorial.manpromanpro.dto.request.UpdateDeveloperRequestDTO;
import apap.tutorial.manpromanpro.model.Developer;

import java.util.List;

public interface DeveloperService {
    public void addDeveloper(Developer developer);

    public List<Developer> getAllDeveloper();

    public Developer getDeveloperById(Long id);

    public void updateDeveloper(UpdateDeveloperRequestDTO developerDTO);
}

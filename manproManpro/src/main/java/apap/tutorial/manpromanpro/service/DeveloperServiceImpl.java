package apap.tutorial.manpromanpro.service;

import apap.tutorial.manpromanpro.model.Developer;
import apap.tutorial.manpromanpro.repository.DeveloperDb;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeveloperServiceImpl implements DeveloperService {
    @Autowired
    private DeveloperDb developerDb;

    @Override
    public void addDeveloper(Developer developer) {
        developerDb.save(developer);
    }

    @Override
    public List<Developer> getAllDeveloper() {
        return developerDb.findAll();
    }

    @Override
    public Developer getDeveloperById(Long id) {
        var developer = developerDb.findById(id);
        if (developer.isPresent()) return developer.get();
        throw new EntityNotFoundException("Developer not found");
    }
}

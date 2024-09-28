package apap.tutorial.manpromanpro;

import apap.tutorial.manpromanpro.dto.mapper.PekerjaMapper;
import apap.tutorial.manpromanpro.dto.request.AddDeveloperRequestDTO;
import apap.tutorial.manpromanpro.dto.request.AddPekerjaRequestDTO;
import apap.tutorial.manpromanpro.dto.request.AddProjectRequestDTO;
import apap.tutorial.manpromanpro.dto.mapper.DeveloperMapper;
import apap.tutorial.manpromanpro.dto.mapper.ProyekMapper;
import apap.tutorial.manpromanpro.service.DeveloperService;
import apap.tutorial.manpromanpro.service.PekerjaService;
import apap.tutorial.manpromanpro.service.ProyekService;
import com.github.javafaker.Faker;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.sql.Date;
import java.util.concurrent.TimeUnit;

@SpringBootApplication
public class ManpromanproApplication {

    public static void main(String[] args) {
        SpringApplication.run(ManpromanproApplication.class, args);
    }

    @Bean
    CommandLineRunner run(ProyekService proyekService,
                          DeveloperService developerService,
                          ProyekMapper proyekMapper,
                          DeveloperMapper developerMapper,
                          PekerjaService pekerjaService,
                          PekerjaMapper pekerjaMapper) {
        var faker = new Faker();

        return args -> {
            for (int i = 0; i < 5; i++) {
                var developerDTO = new AddDeveloperRequestDTO();
                developerDTO.setNama(faker.company().name());
                developerDTO.setAlamat(faker.address().streetName());
                developerDTO.setTanggalBerdiri(new Date(faker.date().past(2000, TimeUnit.DAYS).getTime()));
                developerDTO.setEmail(faker.internet().emailAddress());

                try {
                    developerService.addDeveloper(developerMapper.developerRequestDTOToDeveloper(developerDTO));
                } catch (Exception ignored) {}
            }

            for (int i = 0; i < 10; i++) {
                var pekerjaDTO = new AddPekerjaRequestDTO();
                pekerjaDTO.setNama(faker.name().fullName());
                pekerjaDTO.setUsia(faker.number().numberBetween(18, 30));
                pekerjaDTO.setBiografi(faker.lorem().paragraph(2));
                pekerjaDTO.setPekerjaan(faker.job().field());

                try {
                    pekerjaService.addPekerja(pekerjaMapper.pekerjaRequestDTOToPekerja(pekerjaDTO));
                } catch (Exception ignored) {}
            }

            var developers = developerService.getAllDeveloper();
            var workers = pekerjaService.getAllPekerja();

            for (int i = 0; i < 20; i++) {
                var proyekDTO = new AddProjectRequestDTO();
                proyekDTO.setNama(faker.gameOfThrones().house());
                proyekDTO.setDeskripsi(faker.lorem().paragraph(2));
                proyekDTO.setTanggalMulai(new Date(faker.date().past(2000, TimeUnit.DAYS).getTime()));
                proyekDTO.setTanggalSelesai(new Date(faker.date().future(10, TimeUnit.DAYS).getTime()));
                proyekDTO.setStatus(faker.number().numberBetween(1, 3));
                proyekDTO.setDeveloper(developers.get(faker.number().numberBetween(0, developers.size())));
                proyekDTO.getListPekerja().add(workers.get(faker.number().numberBetween(0, workers.size())));

                try {
                    proyekService.createProyek(proyekMapper.addProjectDTOToProject(proyekDTO));
                } catch (Exception ignored) {}
            }
        };
    }
}

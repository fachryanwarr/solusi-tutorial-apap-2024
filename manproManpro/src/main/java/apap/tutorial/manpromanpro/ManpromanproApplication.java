package apap.tutorial.manpromanpro;

import apap.tutorial.manpromanpro.model.Proyek;
import apap.tutorial.manpromanpro.service.ProyekService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.sql.Date;
import java.util.UUID;

@SpringBootApplication
public class ManpromanproApplication {

    public static void main(String[] args) {
        SpringApplication.run(ManpromanproApplication.class, args);
    }

    @Bean
    CommandLineRunner run(ProyekService proyekService) {
        return args -> {
            var proyek1 = new Proyek(UUID.randomUUID(), "Skyline AI Platform", Date.valueOf("2024-01-01"), Date.valueOf("2024-06-30"), 1, "HyperTech Solutions");
            var proyek2 = new Proyek(UUID.randomUUID(), "Quantum Leap", Date.valueOf("2024-02-15"), Date.valueOf("2024-07-20"), 2, "Quantum Innovators");
            var proyek3 = new Proyek(UUID.randomUUID(), "Nebula Data Cloud", Date.valueOf("2024-03-10"), Date.valueOf("2024-08-25"), 1, "Nebula Technologies");
            var proyek4 = new Proyek(UUID.randomUUID(), "Apollo Blockchain Network", Date.valueOf("2024-04-05"), Date.valueOf("2024-09-30"), 2, "Apollo DevOps");
            var proyek5 = new Proyek(UUID.randomUUID(), "Phoenix Cyber Defense", Date.valueOf("2024-05-01"), Date.valueOf("2024-12-31"), 1, "Phoenix Security Labs");

            proyekService.createProyek(proyek1);
            proyekService.createProyek(proyek2);
            proyekService.createProyek(proyek3);
            proyekService.createProyek(proyek4);
            proyekService.createProyek(proyek5);
        };
    }
}

package apap.tutorial.manpromanpro.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import apap.tutorial.manpromanpro.dto.ProjectResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import apap.tutorial.manpromanpro.dto.ProyekDTO;
import apap.tutorial.manpromanpro.model.Proyek;
import apap.tutorial.manpromanpro.service.ProyekService;

@Controller
public class ProyekController {
    @Autowired
    private ProyekService proyekService;

    @GetMapping("/")
    private String home() {
        return "home";
    }

    @GetMapping("/proyek/add")
    public String addProyekForm(Model model) {
        try {
            var proyekDTO = new ProyekDTO();
            model.addAttribute("proyekDTO", proyekDTO);
        } catch (Exception e) {
            model.addAttribute("type", "error");
            model.addAttribute("msg", e.getMessage());
            return "response-page";
        }

        return "form-add-proyek";
    }

    @PostMapping("/proyek/add")
    public String addProyek(@ModelAttribute ProyekDTO proyekDTO, Model model) {
        try {
            UUID idProyek = UUID.randomUUID();
            var proyek = new Proyek(idProyek, proyekDTO.getNama(), proyekDTO.getTanggalMulai(), proyekDTO.getTanggalSelesai(), proyekDTO.getStatus(), proyekDTO.getDeveloper());
            proyekService.createProyek(proyek);

            model.addAttribute("type", "success");
            model.addAttribute("msg", "Project " + proyek.getNama() + " berhasil ditambahkan");
        } catch (Exception e) {
            model.addAttribute("type", "error");
            model.addAttribute("msg", e.getMessage());
        }

        return "response-page";
    }

    @GetMapping("/proyek/{id}/update")
    public String updateProjectForm(@PathVariable(value = "id") String id, Model model) {
        try {
            var idProject = UUID.fromString(id);
            var project = proyekService.getProyekById(idProject);

            model.addAttribute("proyekDTO", project);
            model.addAttribute("action", "/proyek/" + project.getId() + "/update");
        } catch (Exception e) {
            model.addAttribute("type", "error");
            model.addAttribute("msg", e.getMessage());
            return "response-page";
        }
        return "form-add-proyek";
    }

    @PostMapping("/proyek/{id}/update")
    public String updateProject(@ModelAttribute Proyek proyek, Model model) {
        try {
            proyekService.updateProject(proyek);
            model.addAttribute("type", "success");
            model.addAttribute("msg", "Project dengan id " + proyek.getId() + " berhasil diubah");
        } catch (Exception e) {
            model.addAttribute("type", "error");
            model.addAttribute("msg", e.getMessage());
        }

        return "response-page";
    }

    @GetMapping("/proyek/{id}/delete")
    public String deleteProject(@PathVariable(value = "id") String id, Model model) {
        try {
            UUID idProject = UUID.fromString(id);
            proyekService.deleteProject(idProject);

            model.addAttribute("type", "success");
            model.addAttribute("msg", "Project dengan id " + id + " berhasil dihapus");
        } catch (Exception e) {
            model.addAttribute("type", "error");
            model.addAttribute("msg", e.getMessage());
        }

        return "response-page";
    }

    @GetMapping("/proyek/viewall")
    public String listProyek(Model model) {
        try {
            List<Proyek> listProyek = proyekService.getAllProyek();
            List<ProjectResponseDTO> projectResponseList = new ArrayList<>();

            for (Proyek proyek : listProyek) {
                projectResponseList.add(proyekService.getProjectResponse(proyek));
            }
            model.addAttribute("listProyek", projectResponseList);
        } catch (Exception e) {
            model.addAttribute("type", "error");
            model.addAttribute("msg", e.getMessage());
            return "response-page";
        }

        return "viewall-proyek";
    }

    @GetMapping("/proyek/{id}")
    public String detailProyek(@PathVariable(value = "id") String id, Model model) {
        try {
            var proyek = proyekService.getProyekById(UUID.fromString(id));
            var projectResponse = proyekService.getProjectResponse(proyek);
            model.addAttribute("proyek", projectResponse);
        } catch (Exception e) {
            model.addAttribute("type", "error");
            model.addAttribute("msg", e.getMessage());
            return "response-page";
        }

        return "view-proyek";
    }
}

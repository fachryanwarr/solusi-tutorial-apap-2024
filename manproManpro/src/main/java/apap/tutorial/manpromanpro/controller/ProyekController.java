package apap.tutorial.manpromanpro.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import apap.tutorial.manpromanpro.dto.request.AddProjectRequestDTO;
import apap.tutorial.manpromanpro.dto.response.ProjectResponseDTO;
import apap.tutorial.manpromanpro.dto.request.UpdateProjectRequestDTO;
import apap.tutorial.manpromanpro.dto.mapper.ProyekMapper;
import apap.tutorial.manpromanpro.model.Pekerja;
import apap.tutorial.manpromanpro.service.DeveloperService;
import apap.tutorial.manpromanpro.service.PekerjaService;
import apap.tutorial.manpromanpro.utils.ErrorMessage;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import apap.tutorial.manpromanpro.model.Proyek;
import apap.tutorial.manpromanpro.service.ProyekService;

@Controller
public class ProyekController {
    @Autowired
    private ProyekService proyekService;
    @Autowired
    private ProyekMapper proyekMapper;
    @Autowired
    private DeveloperService developerService;
    @Autowired
    private PekerjaService pekerjaService;
    @Autowired
    private ErrorMessage errorMessage;

    @GetMapping("/")
    private String home() {
        return "home";
    }

    @GetMapping("/proyek/add")
    public String addProyekForm(Model model) {
        try {
            var proyekDTO = new AddProjectRequestDTO();
            model.addAttribute("proyekDTO", proyekDTO);
            model.addAttribute("developers", developerService.getAllDeveloper());
            model.addAttribute("listPekerjaOption", pekerjaService.getAllPekerja());
        } catch (Exception e) {
            model.addAttribute("type", "error");
            model.addAttribute("msg", e.getMessage());
            return "response-page";
        }

        return "proyek/form-add-proyek";
    }

    @PostMapping(value = "/proyek/add", params = {"addRow"})
    public String addProyekAddRow(@ModelAttribute AddProjectRequestDTO proyekDTO,
                                  Model model) {
        try {
            if(proyekDTO.getListPekerja() == null || proyekDTO.getListPekerja().isEmpty()){
                proyekDTO.setListPekerja(new ArrayList<>());
            }
            proyekDTO.getListPekerja().add(new Pekerja());
            model.addAttribute("proyekDTO", proyekDTO);
            model.addAttribute("developers", developerService.getAllDeveloper());
            model.addAttribute("listPekerjaOption", pekerjaService.getAllPekerja());

            return "proyek/form-add-proyek";
        } catch (Exception e) {
            model.addAttribute("type", "error");
            model.addAttribute("msg", e.getMessage());
        }

        return "response-page";
    }

    @PostMapping(value = "/proyek/add", params = {"deleteRow"})
    public String addProyekDeleteRow(@ModelAttribute AddProjectRequestDTO proyekDTO,
                                     @RequestParam("deleteRow") int row,
                                     Model model) {
        try {
            proyekDTO.getListPekerja().remove(row);
            model.addAttribute("proyekDTO", proyekDTO);
            model.addAttribute("developers", developerService.getAllDeveloper());
            model.addAttribute("listPekerjaOption", pekerjaService.getAllPekerja());

            return "proyek/form-add-proyek";
        } catch (Exception e) {
            model.addAttribute("type", "error");
            model.addAttribute("msg", e.getMessage());
        }

        return "response-page";
    }

    @PostMapping("/proyek/add")
    public String addProyek(@Valid @ModelAttribute AddProjectRequestDTO proyekDTO,
                            BindingResult bindingResult,
                            Model model) {
        if (bindingResult.hasErrors()) {
            var error = errorMessage.getErrorMsg(bindingResult);
            model.addAttribute("type", "error");
            model.addAttribute("msg", error);
            return "response-page";
        }

        try {
            var proyek = proyekMapper.addProjectDTOToProject(proyekDTO);
            proyekService.createProyek(proyek);

            model.addAttribute("type", "success");
            model.addAttribute("msg", "Project " + proyekDTO.getNama() + " berhasil ditambahkan");
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
            var proyekDTO = proyekMapper.proyekToUpdateProjectDTO(project);

            model.addAttribute("developers", developerService.getAllDeveloper());
            model.addAttribute("listPekerjaOption", pekerjaService.getAllPekerja());
            model.addAttribute("proyekDTO", proyekDTO);
        } catch (Exception e) {
            model.addAttribute("type", "error");
            model.addAttribute("msg", e.getMessage());
            return "response-page";
        }
        return "proyek/form-update-proyek";
    }

    @PostMapping(value = "/proyek/{id}/update", params = {"addRow"})
    public String updateProjectFormAddRow(@ModelAttribute UpdateProjectRequestDTO proyekDTO,
                                          Model model) {
        try {
            if(proyekDTO.getListPekerja() == null || proyekDTO.getListPekerja().isEmpty()){
                proyekDTO.setListPekerja(new ArrayList<>());
            }
            proyekDTO.getListPekerja().add(new Pekerja());
            model.addAttribute("developers", developerService.getAllDeveloper());
            model.addAttribute("listPekerjaOption", pekerjaService.getAllPekerja());
            model.addAttribute("proyekDTO", proyekDTO);
        } catch (Exception e) {
            model.addAttribute("type", "error");
            model.addAttribute("msg", e.getMessage());
            return "response-page";
        }
        return "proyek/form-update-proyek";
    }

    @PostMapping(value = "/proyek/{id}/update", params = {"deleteRow"})
    public String updateProjectFormDeleteRow(@ModelAttribute UpdateProjectRequestDTO proyekDTO,
                                             @RequestParam("deleteRow") int row,
                                             Model model) {
        try {
            proyekDTO.getListPekerja().remove(row);
            model.addAttribute("developers", developerService.getAllDeveloper());
            model.addAttribute("listPekerjaOption", pekerjaService.getAllPekerja());
            model.addAttribute("proyekDTO", proyekDTO);
        } catch (Exception e) {
            model.addAttribute("type", "error");
            model.addAttribute("msg", e.getMessage());
            return "response-page";
        }
        return "proyek/form-update-proyek";
    }

    @PostMapping("/proyek/{id}/update")
    public String updateProject(@ModelAttribute UpdateProjectRequestDTO proyekDTO, Model model) {
        try {
            var proyek = proyekMapper.updateProjectDTOToProyek(proyekDTO);
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
    public String listProyek(@RequestParam(value = "nama", required = false) String nama,
                             @RequestParam(value = "status", required = false) String status,
                             Model model) {
        try {
            List<Proyek> listProyek = proyekService.getAllProyekFilter(nama, status);
            List<ProjectResponseDTO> projectResponseList = new ArrayList<>();

            for (Proyek proyek : listProyek) {
                projectResponseList.add(proyekMapper.proyekToProjectResponseDTO(proyek));
            }

            if (nama != null) model.addAttribute("namaSelected", nama);
            if (status != null) model.addAttribute("statusSelected", status);

            model.addAttribute("listProyek", projectResponseList);
        } catch (Exception e) {
            model.addAttribute("type", "error");
            model.addAttribute("msg", e.getMessage());
            return "response-page";
        }

        return "proyek/viewall-proyek";
    }

    @GetMapping("/proyek/datatable")
    public String listProyekDatatable(@RequestParam(value = "nama", required = false) String nama,
                             @RequestParam(value = "status", required = false) String status,
                             Model model) {
        try {
            List<Proyek> listProyek = proyekService.getAllProyekFilter(nama, status);
            List<ProjectResponseDTO> projectResponseList = new ArrayList<>();

            for (Proyek proyek : listProyek) {
                projectResponseList.add(proyekMapper.proyekToProjectResponseDTO(proyek));
            }

            if (nama != null) model.addAttribute("namaSelected", nama);
            if (status != null) model.addAttribute("statusSelected", status);

            model.addAttribute("listProyek", projectResponseList);
        } catch (Exception e) {
            model.addAttribute("type", "error");
            model.addAttribute("msg", e.getMessage());
            return "response-page";
        }

        return "proyek/viewall-proyek-datatable";
    }

    @GetMapping("/proyek/{id}")
    public String detailProyek(@PathVariable(value = "id") String id, Model model) {
        try {
            var proyek = proyekService.getProyekById(UUID.fromString(id));
            var projectResponse = proyekMapper.proyekToProjectResponseDTO(proyek);
            model.addAttribute("proyek", projectResponse);
        } catch (Exception e) {
            model.addAttribute("type", "error");
            model.addAttribute("msg", e.getMessage());
            return "response-page";
        }

        return "proyek/view-proyek";
    }
}

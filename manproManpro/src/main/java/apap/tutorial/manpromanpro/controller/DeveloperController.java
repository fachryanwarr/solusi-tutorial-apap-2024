package apap.tutorial.manpromanpro.controller;

import apap.tutorial.manpromanpro.dto.request.AddDeveloperRequestDTO;
import apap.tutorial.manpromanpro.dto.mapper.DeveloperMapper;
import apap.tutorial.manpromanpro.dto.request.UpdateDeveloperRequestDTO;
import apap.tutorial.manpromanpro.model.Developer;
import apap.tutorial.manpromanpro.service.DeveloperService;
import apap.tutorial.manpromanpro.service.ProyekService;
import apap.tutorial.manpromanpro.utils.ErrorMessage;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class DeveloperController {
    @Autowired
    private DeveloperService developerService;
    @Autowired
    private DeveloperMapper developerMapper;
    @Autowired
    private ProyekService proyekService;
    @Autowired
    private ErrorMessage errorMessage;

    @GetMapping("/developer/add")
    public String formAddDeveloper(Model model) {
        try {
            var developerDTO = new AddDeveloperRequestDTO();
            model.addAttribute("developerDTO", developerDTO);

            return "developer/form-add-developer";
        } catch (Exception e) {
            model.addAttribute("type", "error");
            model.addAttribute("msg", e.getMessage());
            return "response-page";
        }
    }

    @PostMapping("/developer/add")
    public String addDeveloper(@Valid @ModelAttribute("developerDTO") AddDeveloperRequestDTO developerDTO,
                               BindingResult bindingResult,
                               Model model) {
        if (bindingResult.hasErrors()) {
            var error = errorMessage.getErrorMsg(bindingResult);
            model.addAttribute("type", "error");
            model.addAttribute("msg", error);
            return "response-page";
        }

        try {
            var developer = developerMapper.developerRequestDTOToDeveloper(developerDTO);
            developerService.addDeveloper(developer);

            model.addAttribute("type", "success");
            model.addAttribute("msg", "Developer " + developer.getNama() + " berhasil ditambahkan.");
        } catch (Exception e) {
            model.addAttribute("type", "error");
            model.addAttribute("msg", e.getMessage());
        }
        return "response-page";
    }

    @GetMapping("/developer/viewall")
    public String listDeveloper(Model model) {
        try {
            var listDeveloper = developerService.getAllDeveloper();
            for (Developer developer : listDeveloper) {
                developer.setListProyek(proyekService.getByDeveloper(developer));
            }
            model.addAttribute("listDeveloper", listDeveloper);
            return "developer/viewall-developer";
        } catch (Exception e) {
            model.addAttribute("type", "error");
            model.addAttribute("msg", e.getMessage());
            return "response-page";
        }
    }

    @GetMapping("/developer/{id}")
    public String detailDeveloper(@PathVariable("id") String id, Model model) {
        try {
            var idDeveloper = Long.parseLong(id);
            var developer = developerService.getDeveloperById(idDeveloper);
            developer.setListProyek(proyekService.getByDeveloper(developer));
            model.addAttribute("developer", developerMapper.developerToDeveloperResponse(developer));
            return "developer/view-developer";
        } catch (Exception e) {
            model.addAttribute("type", "error");
            model.addAttribute("msg", e.getMessage());
            return "response-page";
        }
    }

    @GetMapping("/developer/{id}/update")
    public String updateDeveloperForm(@PathVariable("id") String id, Model model) {
        try {
            var developer = developerService.getDeveloperById(Long.parseLong(id));
            var updateDTO = developerMapper.developerToUpdateDeveloperDTO(developer);

            model.addAttribute("developerDTO", updateDTO);

            return "developer/form-update-developer";
        }  catch (Exception e) {
            model.addAttribute("type", "error");
            model.addAttribute("msg", e.getMessage());
            return "response-page";
        }
    }

    @PostMapping("/developer/update")
    public String updateDeveloper(@Valid @ModelAttribute UpdateDeveloperRequestDTO updateDTO,
                                  BindingResult bindingResult,
                                  Model model) {
        if (bindingResult.hasErrors()) {
            var error = errorMessage.getErrorMsg(bindingResult);
            model.addAttribute("type", "error");
            model.addAttribute("msg", error);
            return "response-page";
        }

        try {
            developerService.updateDeveloper(updateDTO);
            model.addAttribute("type", "success");
            model.addAttribute("msg", "Developer dengan ID " + updateDTO.getId() + " berhasil diupdate");
            return "response-page";
        } catch (Exception e) {
            model.addAttribute("type", "error");
            model.addAttribute("msg", e.getMessage());
            return "response-page";
        }
    }
}

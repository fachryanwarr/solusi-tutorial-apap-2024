package apap.tutorial.manpromanpro.controller;

import apap.tutorial.manpromanpro.dto.mapper.PekerjaMapper;
import apap.tutorial.manpromanpro.dto.request.AddPekerjaRequestDTO;
import apap.tutorial.manpromanpro.dto.request.DeleteMultiplePekerjaDTO;
import apap.tutorial.manpromanpro.service.PekerjaService;
import apap.tutorial.manpromanpro.utils.ErrorMessage;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class PekerjaController {
    @Autowired
    private PekerjaService pekerjaService;
    @Autowired
    private PekerjaMapper pekerjaMapper;
    @Autowired
    private ErrorMessage errorMessage;

    @GetMapping("/pekerja/add")
    public String addPekerjaForm(Model model) {
        try {
            var pekerjaDTO = new AddPekerjaRequestDTO();
            model.addAttribute("pekerjaDTO", pekerjaDTO);
        } catch (Exception e) {
            model.addAttribute("type", "error");
            model.addAttribute("msg", e.getMessage());
            return "response-page";
        }

        return "pekerja/form-add-pekerja";
    }

    @PostMapping("/pekerja/add")
    public String addPekerja(@Valid @ModelAttribute AddPekerjaRequestDTO pekerjaDTO,
                             BindingResult bindingResult,
                             Model model) {
        if (bindingResult.hasErrors()) {
            var error = errorMessage.getErrorMsg(bindingResult);
            model.addAttribute("type", "error");
            model.addAttribute("msg", error);
            return "response-page";
        }

        try {
            var pekerja = pekerjaMapper.pekerjaRequestDTOToPekerja(pekerjaDTO);
            pekerjaService.addPekerja(pekerja);
            model.addAttribute("type", "success");
            model.addAttribute("msg", "Berhasil nambahin pekerja");
        } catch (Exception e) {
            model.addAttribute("type", "error");
            model.addAttribute("msg", e.getMessage());
        }

        return "response-page";
    }

    @GetMapping("/pekerja/viewall")
    public String getAllPekerja(Model model) {
        try {
            var listPekerja = pekerjaService.getAllPekerja();

            model.addAttribute("listPekerja", listPekerja);
            model.addAttribute("deleteDTO", new DeleteMultiplePekerjaDTO());
            return "pekerja/viewall-pekerja";
        } catch (Exception e) {
            model.addAttribute("type", "error");
            model.addAttribute("msg", e.getMessage());
            return "response-page";
        }
    }

    @PostMapping("/pekerja/delete")
    public String deletePekerja(@ModelAttribute DeleteMultiplePekerjaDTO deleteDTO, Model model) {
        try {
            pekerjaService.deleteListPekerja(deleteDTO.getListPekerja());
            model.addAttribute("type", "success");
            model.addAttribute("msg", "Berhasil delete list pekerja");
        } catch (Exception e) {
            model.addAttribute("type", "error");
            model.addAttribute("msg", e.getMessage());
        }

        return "response-page";
    }
}

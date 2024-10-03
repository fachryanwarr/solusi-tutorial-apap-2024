package apap.tutorial.manpromanpro.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
public class BaseController {

    @ModelAttribute
    public void addAttributes(Model model, HttpServletRequest request) {
        String currentUrl = request.getRequestURI();
        model.addAttribute("currentUrl", currentUrl);
    }
}

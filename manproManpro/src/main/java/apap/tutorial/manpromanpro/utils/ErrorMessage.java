package apap.tutorial.manpromanpro.utils;

import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;

@Component
public class ErrorMessage {
    public String getErrorMsg(BindingResult bindingResult) {
        var res = new StringBuilder();
        for (var i = 0; i < bindingResult.getErrorCount(); i++) {
            res.append(bindingResult.getFieldErrors().get(i).getDefaultMessage());
            if (i != bindingResult.getErrorCount() - 1)
                res.append(", ");
        }

        return res.toString();
    }
}

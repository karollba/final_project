package pl.visa.finalproject.config;

import org.eclipse.tags.shaded.org.apache.xpath.operations.Mod;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.NoSuchElementException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    public String handleRunTimeException(RuntimeException e, Model model) {
        model.addAttribute("errorMessage", e.getMessage());
        return "error";
    }

    @ExceptionHandler(NoSuchElementException.class)
    public String handleNotFound(NoSuchElementException e, Model model) {
        model.addAttribute("errorMessage", e.getMessage());
        return "error";
    }


}

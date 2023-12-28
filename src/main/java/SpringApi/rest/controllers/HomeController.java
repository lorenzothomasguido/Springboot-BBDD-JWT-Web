package SpringApi.rest.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class HomeController {

    @GetMapping("/login")
    public RedirectView redirectToLogin() {
        return new RedirectView("/login.html");
    }

    @GetMapping("/index")
    public RedirectView RedirectIndex() {
        return new RedirectView("/index.html");
    }

    @GetMapping("/registrar")
    public RedirectView RedirectRegistrar() {
        return new RedirectView("/registrar.html");
    }

    @GetMapping("/usuarios")
    public RedirectView RedirectUsuarios() {
        return new RedirectView("/usuarios.html");
    }

    @GetMapping("/buttons")
    public RedirectView RedirectButtons() {
        return new RedirectView("/buttons.html");
    }


    }


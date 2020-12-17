package world.ucode.cashflow.controllers;

import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import world.ucode.cashflow.models.Users;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
@RequestMapping("/hallo")
public class HalloController {
    @PostMapping
    public void postHallo(@RequestBody Users user, HttpServletResponse response, HttpServletRequest request) throws IOException {
        response.setStatus(111);

    }
    @GetMapping
    public String getHallo() {
        return "main";
    }
}

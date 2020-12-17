package world.ucode.cashflow.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import world.ucode.cashflow.models.Role;
import world.ucode.cashflow.models.Users;
import world.ucode.cashflow.repositories.UserRepo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;

@Controller
@RequestMapping("/sign_up")
public class RegistrationController {
    @Autowired
    private UserRepo userRepo;
    @PostMapping
    public void postSignUp(@RequestBody Users user, HttpServletResponse response) throws IOException {
        if (userRepo.findByLogin(user.getLogin()).isEmpty()) {
            user.setRoles(Collections.singleton(Role.USER));
            user.setPassword(BCrypt.hashpw(user.getPassword(), BCrypt.gensalt()));
            userRepo.save(user);

        } else {
            response.sendError(111, "pipec");
        }
    }
    @GetMapping
    public String getSignUp(HttpServletRequest request) {
        String string = request.getAttribute(CsrfToken.class.getName()).toString();
        System.out.println(string);
        return "main";
    }
}

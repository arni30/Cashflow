package world.ucode.cashflow.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;
import world.ucode.cashflow.models.Role;
import world.ucode.cashflow.models.Users;
import world.ucode.cashflow.repositories.UserRepo;
import world.ucode.cashflow.utils.MailSender;
import world.ucode.cashflow.utils.Token;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;

@Controller
@RequestMapping("/sign_up")
public class RegistrationController {
    @Autowired
    private Token token;
    @Autowired
    private MailSender mailSender;
    @Autowired
    private UserRepo userRepo;
    @PostMapping
    public String postSignUp(@RequestBody Users user, HttpServletResponse response) throws IOException {
        if (userRepo.findByLogin(user.getLogin()).isEmpty()) {
            user.setRoles(Collections.singleton(Role.USER));
            user.setPassword(BCrypt.hashpw(user.getPassword(), BCrypt.gensalt()));
            user.setToken(token.getJWTToken(user.getLogin()));
            user.setValidationStatus(0);
            userRepo.save(user);
            mailSender.sendMailConfirmation(user);

        } else {
            response.sendError(111, "pipec");
        }
        return "authorization";
    }
    @GetMapping
    public String getSignUp(HttpServletRequest request) {
        return "registration";
    }
    @GetMapping("confirmation{token}")
    public RedirectView confirmation(@RequestParam("token") String token){
        Users user = userRepo.findByToken(token).get(0);
        if (user != null) {
            System.out.println("hallo");
            user.setValidationStatus(1);
            userRepo.save(user);
            return new RedirectView("authorization");
        }
        return new RedirectView("error");
    }
} 


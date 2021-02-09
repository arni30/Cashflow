package world.ucode.cashflow.controllers.view;

import org.apache.xmlbeans.impl.common.NameUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
import world.ucode.cashflow.models.Role;
import world.ucode.cashflow.models.dao.Users;
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
    public void postSignUp(@RequestBody Users user, HttpServletResponse response) throws IOException {
        if (userRepo.findByLogin(user.getLogin()) == null) {
            user.setRoles(Collections.singleton(Role.USER));
            user.setPassword(BCrypt.hashpw(user.getPassword(), BCrypt.gensalt()));
            user.setToken(token.getJWTToken(user.getLogin()));
            user.setValidationStatus(0);
            userRepo.save(user);
            mailSender.sendMailConfirmation(user);

        } else {
            response.sendError(111, "pipec");
        }
        response.encodeRedirectURL("authorization");
    }
    @GetMapping
    public String getSignUp(HttpServletRequest request) {
        return "registration";
    }
    @GetMapping("confirmation{token}")
    public RedirectView confirmation(@RequestParam("token") String token){
        RedirectView redirectView = null;
        Users user = userRepo.findByToken(token).get(0);
        if (user != null) {
            user.setValidationStatus(1);
            userRepo.save(user);
            redirectView = new RedirectView("/login");
            return redirectView;
        }
        redirectView = new RedirectView("error");
        return redirectView;
    }
} 


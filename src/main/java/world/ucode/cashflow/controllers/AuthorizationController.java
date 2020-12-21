package world.ucode.cashflow.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import world.ucode.cashflow.models.Role;
import world.ucode.cashflow.models.Users;
import world.ucode.cashflow.repositories.UserRepo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
@Controller
@RequestMapping("/login")
public class AuthorizationController {
        @GetMapping
        public String getLogin() {
            return "authorization";
        }
}

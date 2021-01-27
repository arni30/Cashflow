package world.ucode.cashflow.controllers.view;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/login")
public class AuthorizationController {
        @GetMapping
        public String getLogin(HttpServletRequest request) {
            return "authorization";
        }
}

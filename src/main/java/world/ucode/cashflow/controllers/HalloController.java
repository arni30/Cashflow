package world.ucode.cashflow.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
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
        System.out.println("zbs");

    }
    @GetMapping
    public String getHallo(HttpServletRequest request) {
//        System.out.println(request.getUserPrincipal().getName());
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        System.out.println(authentication.getName());
        getEmployees();
        return "main";
    }
    private static void getEmployees()
    {
        final String uri = "https://api.privatbank.ua/p24api/pubinfo?json&exchange&coursid=5";

        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.getForObject(uri, String.class);

        System.out.println(result);
    }
}

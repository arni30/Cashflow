package world.ucode.cashflow.controllers.view;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import world.ucode.cashflow.repositories.UserRepo;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/wallets")
public class WalletsController {
    @Autowired
    private UserRepo userRepo;
//    @PostMapping
//    public String postWallets(@RequestBody Users user, HttpServletResponse response) throws IOException {
//    }
    @GetMapping
    public String getWallets(HttpServletRequest request) {
        return "wallets";
    }
}
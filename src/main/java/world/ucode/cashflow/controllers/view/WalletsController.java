package world.ucode.cashflow.controllers.view;

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

//@Controller
//@RequestMapping("/wallets")
//public class WalletsController {
//    @Autowired
//    private UserRepo userRepo;
////    @PostMapping
////    public String postWallets(@RequestBody Users user, HttpServletResponse response) throws IOException {
////    }
//    @GetMapping
//    public String getWallets(HttpServletRequest request) {
//        return "wallets";
//    }
//}
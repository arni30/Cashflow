package world.ucode.cashflow.controllers.view;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import world.ucode.cashflow.models.Wallet;
import world.ucode.cashflow.repositories.WalletRepo;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Controller
public class MainController {
    @Autowired
    WalletRepo walletRepo;
    @GetMapping
    public String getMain() {
        return "main";
    }
}
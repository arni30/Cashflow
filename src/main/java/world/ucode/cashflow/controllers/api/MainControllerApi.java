package world.ucode.cashflow.controllers.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import world.ucode.cashflow.models.dao.Wallet;
import world.ucode.cashflow.repositories.WalletRepo;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api")
public class MainControllerApi {
    @Autowired
    WalletRepo walletRepo;
    @GetMapping
    public List<Wallet> getMain(HttpServletResponse response) throws IOException {
        return walletRepo.findByUser_Id(1);
    }
}
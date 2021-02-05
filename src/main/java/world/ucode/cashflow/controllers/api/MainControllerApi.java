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

// TODO delete or move all here, main gets from transaction

@RestController
@RequestMapping("/api")
public class MainControllerApi {
//    private final WalletRepo walletRepo;
//    @Autowired
//    public MainControllerApi(WalletRepo walletRepo) {
//        this.walletRepo = walletRepo;
//    }
//
//    @GetMapping("/get")
//    public List<Wallet> getMain() throws IOException {
//        return walletRepo.findByUser_Id(2);
//    }
}
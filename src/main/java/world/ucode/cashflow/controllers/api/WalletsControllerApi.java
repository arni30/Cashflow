package world.ucode.cashflow.controllers.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import world.ucode.cashflow.models.dao.Users;
import world.ucode.cashflow.models.dao.Wallet;
import world.ucode.cashflow.models.dto.WalletDTO;
import world.ucode.cashflow.repositories.CurrencyRepo;
import world.ucode.cashflow.repositories.UserRepo;
import world.ucode.cashflow.repositories.WalletRepo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/wallets")
public class WalletsControllerApi {
    @Autowired
    private WalletRepo walletRepo;
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private CurrencyRepo currencyRepo;

    @GetMapping("/get")
    public List<Wallet> getWalletsAndCurrency() {
        return walletRepo.findByUser_Id(2);
    }
    @PostMapping("/create")
    public void postCreateWallet(@RequestBody Wallet wallet, HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            Users user = userRepo.findByLogin(request.getUserPrincipal().getName());
            wallet.setUser(user);
            walletRepo.save(wallet);
        }
        catch (Exception e) {
            e.printStackTrace();
            response.sendError(400, "Bad Request");
        }
    }
    @PostMapping("/update")
    public void postUpdateWallets(@RequestBody WalletDTO newWallet, HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            Wallet wallet = walletRepo.findById(newWallet.getId());
            wallet.setName(newWallet.getName().equals("") ? wallet.getName() : newWallet.getName());
            wallet.setCurrency(newWallet.getCurrencyId() == 0 ? wallet.getCurrency() : currencyRepo.findById(newWallet.getCurrencyId()));
            wallet.setBalance(newWallet.getBalance() == null ? wallet.getBalance() : newWallet.getBalance());
            walletRepo.save(wallet);
        }
        catch (Exception e) {
            e.printStackTrace();
            response.sendError(400, "Bad Request");
        }
    }

    @PostMapping("/delete")
    public void deleteWallet(@RequestBody Wallet wallet, HttpServletResponse response) throws IOException {
        try {
            walletRepo.delete(wallet);
        }
        catch (Exception e) {
            e.printStackTrace();
            response.sendError(400, "Bad Request");
        }
    }

}

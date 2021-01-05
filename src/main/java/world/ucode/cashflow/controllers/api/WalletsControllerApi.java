package world.ucode.cashflow.controllers.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import world.ucode.cashflow.models.Users;
import world.ucode.cashflow.models.Wallet;
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
    @PostMapping("/createWallet")
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
    @PostMapping("/updateWallet")
    public void postUpdateWallets(@RequestBody Wallet newWallet, HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            Wallet wallet = walletRepo.findById(newWallet.getId());
            wallet.setName(newWallet.getName() == null ? wallet.getName() : newWallet.getName());
            wallet.setCurrency(newWallet.getCurrency() == null ? wallet.getCurrency() : newWallet.getCurrency());
            wallet.setBalance(newWallet.getBalance() == null ? wallet.getBalance() : newWallet.getBalance());
            walletRepo.save(wallet);
        }
        catch (Exception e) {
            e.printStackTrace();
            response.sendError(400, "Bad Request");
        }
    }

}

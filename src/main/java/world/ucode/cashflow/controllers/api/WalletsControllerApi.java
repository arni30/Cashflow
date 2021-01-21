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
    public void postCreateWallet(@RequestBody WalletDTO wallet, HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            System.out.println("CREATE WALLET");
            Users user = userRepo.findByLogin("1");
            Wallet newWallet = new Wallet(wallet);
            System.out.println(currencyRepo.findById(wallet.getCurrencyId()).getName());
            newWallet.setCurrency(currencyRepo.findById(wallet.getCurrencyId()));
            newWallet.setUser(user);
            walletRepo.save(newWallet);
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
            wallet.setName(newWallet.getName() == null ? wallet.getName() : newWallet.getName());
            wallet.setCurrency(newWallet.getCurrencyId() == null ? wallet.getCurrency() : currencyRepo.findById(newWallet.getCurrencyId()));
            wallet.setBalance(newWallet.getBalance() == null ? wallet.getBalance() : newWallet.getBalance());
            walletRepo.save(wallet);
        }
        catch (Exception e) {
            e.printStackTrace();
            response.sendError(400, "Bad Request");
        }
    }

    @PostMapping("/delete")
    public void deleteWallet(@RequestBody WalletDTO wallet, HttpServletResponse response) throws IOException {
        try {
            walletRepo.delete(walletRepo.findById(wallet.getId()));
        }
        catch (Exception e) {
            e.printStackTrace();
            response.sendError(400, "Bad Request");
        }
    }

}

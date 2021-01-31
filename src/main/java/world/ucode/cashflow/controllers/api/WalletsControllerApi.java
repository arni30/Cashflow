package world.ucode.cashflow.controllers.api;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import world.ucode.cashflow.models.dao.Users;
import world.ucode.cashflow.models.dao.Wallet;
import world.ucode.cashflow.models.dto.WalletCurrencyDTO;
import world.ucode.cashflow.models.dto.WalletDTO;
import world.ucode.cashflow.repositories.CurrencyRepo;
import world.ucode.cashflow.repositories.UserRepo;
import world.ucode.cashflow.repositories.WalletRepo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.function.Supplier;

@Slf4j
@RestController
@RequestMapping("/api/wallets")
public class WalletsControllerApi {
    private final WalletRepo walletRepo;
    private final UserRepo userRepo;
    private final CurrencyRepo currencyRepo;
    @Autowired
    public WalletsControllerApi(WalletRepo walletRepo,
                                UserRepo userRepo,
                                CurrencyRepo currencyRepo) {
        this.walletRepo = walletRepo;
        this.currencyRepo = currencyRepo;
        this.userRepo = userRepo;
    }

    @GetMapping("/get")
    public WalletCurrencyDTO getWalletsAndCurrency(HttpServletRequest request) {
        Users user = userRepo.findByLogin(request.getUserPrincipal().getName());

        WalletCurrencyDTO dto = new WalletCurrencyDTO();
        dto.setWallets(walletRepo.findByUser_Id(user.getId()));
        dto.setCurrencies(currencyRepo.findAll());

        return dto;
    }
    @PostMapping("/create")
    public void postCreateWallet(@RequestBody WalletDTO wallet, HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            log.info("CREATE WALLET");

            Wallet newWallet = Wallet.builder()
                    .name(wallet.getName())
                    .currency(currencyRepo.findById(wallet.getCurrencyId()))
                    .balance(wallet.getBalance())
                    .user(userRepo.findByLogin(request.getUserPrincipal().getName()))
                    .build();

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
            log.info("UPDATE WALLET");

            Wallet wallet = walletRepo.findById(newWallet.getId())
                    .orElseThrow(NoSuchElementException::new);
            wallet.setName(newWallet.getName().equals("") ? wallet.getName() : newWallet.getName());

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
            log.info("DELETE WALLET");

            walletRepo.delete(
                    walletRepo.findById(wallet.getId())
                    .orElseThrow(NoSuchElementException::new)
            );
        }
        catch (Exception e) {
            e.printStackTrace();
            response.sendError(400, "Bad Request");
        }
    }

}

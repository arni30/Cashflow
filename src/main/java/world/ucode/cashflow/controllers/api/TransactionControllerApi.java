package world.ucode.cashflow.controllers.api;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import world.ucode.cashflow.models.dao.Tag;
import world.ucode.cashflow.models.dao.Transaction;
import world.ucode.cashflow.models.dao.Users;
import world.ucode.cashflow.models.dao.Wallet;
import world.ucode.cashflow.models.dto.GeneralDTO;
import world.ucode.cashflow.models.dto.TransactionDTO;
import world.ucode.cashflow.models.dto.WalletDTO;
import world.ucode.cashflow.repositories.*;
import org.springframework.web.bind.annotation.*;
import world.ucode.cashflow.repositories.TransactionRepo;
import world.ucode.cashflow.repositories.UserRepo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;

import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;

@Slf4j
@RestController
@RequestMapping("/api/transaction")
public class TransactionControllerApi {
    private final UserRepo userRepo;
    private final WalletRepo walletRepo;
    private final CurrencyRepo currencyRepo;
    private final TransactionRepo transactionRepo;
    private final CategoryRepo categoryRepo;
    private final TagRepo tagRepo;
    @Autowired
    public TransactionControllerApi(TransactionRepo transactionRepo,
                                    WalletRepo walletRepo,
                                    UserRepo userRepo,
                                    CurrencyRepo currencyRepo,
                                    CategoryRepo categoryRepo,
                                    TagRepo tagRepo) {
        this.userRepo = userRepo;
        this.transactionRepo = transactionRepo;
        this.walletRepo = walletRepo;
        this.currencyRepo = currencyRepo;
        this.categoryRepo = categoryRepo;
        this.tagRepo = tagRepo;
    }

    @GetMapping("/get")
    public GeneralDTO getTransactions(HttpServletRequest request) {
        Users user = userRepo.findByLogin(request.getUserPrincipal().getName());

        GeneralDTO dto = new GeneralDTO();

        dto.setTransactions(transactionRepo.findByWallet_User_Id(user.getId()));
        dto.setWallets(walletRepo.findByUser_Id(user.getId()));
        dto.setCurrencies(currencyRepo.findAll());
        dto.setCategories(categoryRepo.findAll());
        dto.setTags(tagRepo.findAll());

        return dto;
    }

    @PostMapping("/create")
    @Transactional
    public void createTransaction(@RequestBody TransactionDTO transaction, HttpServletResponse response) throws IOException {
        try {
            log.info("Create transaction");

            changeWalletBalance(transaction);

            Transaction newTransaction = new Transaction(transaction);
            newTransaction.setCategory(categoryRepo.findById(transaction.getCategoryId()));
            newTransaction.setWallet(walletRepo.findById(transaction.getWalletId())
                    .orElseThrow(NoSuchElementException::new));
            newTransaction.setTag(tagRepo.findById(transaction.getTagId()));
            transactionRepo.save(newTransaction);
        }
        catch (Exception e) {
            log.info("Cannot create transaction");
            e.printStackTrace();
            response.sendError(400, "Bad Request");
        }
    }

    @PostMapping("/update")
    @Transactional
    public void updateTransaction(@RequestBody TransactionDTO newTransaction, HttpServletResponse response) throws IOException {
        try {
            log.info("Update transaction");
            Transaction transaction = transactionRepo.findById(newTransaction.getId());
//            transaction.setWallet(newTransaction.getWalletId() == 0 ? transaction.getWallet() :
//                    walletRepo.findById(newTransaction.getWalletId()).orElseThrow(NoSuchElementException::new));
            transaction.setCategory(newTransaction.getCategoryId() == 0 ? transaction.getCategory() :categoryRepo.findById(newTransaction.getCategoryId()));
            transaction.setDate(newTransaction.getDate() == null ? transaction.getDate() : newTransaction.getDate());
            transaction.setDescription(newTransaction.getDescription().equals("") ? transaction.getDescription() : newTransaction.getDescription());

//            transaction.setType(newTransaction.getType().equals("") ? transaction.getType() : newTransaction.getType());
            recountBalanceIfTypeChanged(newTransaction, transaction);

//            transaction.setTag(newTransaction.getTagId() == 0 ? transaction.getTag() : tagRepo.findById(newTransaction.getTagId()));
            recountBalanceIfTagChanged(newTransaction, transaction);

            transactionRepo.save(transaction);
        }
        catch (Exception e) {
            log.info("Cannot update transaction, check rollback");
            e.printStackTrace();
            response.sendError(400, "Bad Request");
        }
    }

    @PostMapping("/delete")
    @Transactional
    public void deleteTransaction(@RequestBody TransactionDTO transaction, HttpServletResponse response) throws IOException {
        try {
            log.info("Delete one transaction");
            changeWalletBalanceOnDelete(transaction);
            transactionRepo.deleteById(transaction.getId().longValue());
        }
        catch (Exception e) {
            e.printStackTrace();
            response.sendError(400, "Bad Request");
        }
    }

    @PostMapping("/deleteAllByWallet")
    @Transactional
    public void deleteAllTransactionByWallet(@RequestBody WalletDTO wallet, HttpServletResponse response) throws IOException {
        try {
            log.info("Delete all transactions with their wallet");
            log.info(wallet.toString());
            transactionRepo.deleteTransactionsByWallet_Id(wallet.getId());
            walletRepo.deleteById(wallet.getId());
        }
        catch (Exception e) {
            e.printStackTrace();
            response.sendError(400, "Bad Request");
        }
    }

    private void changeWalletBalance(TransactionDTO transaction) {
        Wallet wallet = walletRepo.findById(transaction.getWalletId())
                .orElseThrow(NoSuchElementException::new);
        Tag tag = tagRepo.findById(transaction.getTagId());

        BigDecimal priceAccordingToWalletCurrency = tag.getPrice().multiply(
                BigDecimal.valueOf(wallet.getCurrency().getTicker()));

        BigDecimal countNewBalance = (transaction.getType().equals("income")) ?
                wallet.getBalance().add(priceAccordingToWalletCurrency) :
                wallet.getBalance().subtract(priceAccordingToWalletCurrency);
        wallet.setBalance(countNewBalance);
        walletRepo.save(wallet);
    }

    private void changeWalletBalanceOnDelete(TransactionDTO transaction) {
        Wallet wallet = walletRepo.findById(transaction.getWalletId())
                .orElseThrow(NoSuchElementException::new);
        Tag tag = tagRepo.findById(transaction.getTagId());

        BigDecimal priceAccordingToWalletCurrency = tag.getPrice().multiply(
                BigDecimal.valueOf(wallet.getCurrency().getTicker()));

        BigDecimal countNewBalance = (transaction.getType().equals("income")) ?
                wallet.getBalance().subtract(priceAccordingToWalletCurrency) :
                wallet.getBalance().add(priceAccordingToWalletCurrency);
        wallet.setBalance(countNewBalance);
        walletRepo.save(wallet);
    }

    private void recountBalanceIfTypeChanged(TransactionDTO newTransaction, Transaction transaction) {
        if (!newTransaction.getType().equals("") &&
                !newTransaction.getType().equals(transaction.getType())) {

            transaction.setType(newTransaction.getType());
            Wallet wallet = transaction.getWallet();
            Tag tag = transaction.getTag();

            BigDecimal priceAccordingToWalletCurrency = tag.getPrice()
                    .multiply(BigDecimal.valueOf(wallet.getCurrency().getTicker()))
                    .multiply(BigDecimal.valueOf(2));

            BigDecimal countNewBalance = (transaction.getType().equals("income")) ?
                    wallet.getBalance().add(priceAccordingToWalletCurrency) :
                    wallet.getBalance().subtract(priceAccordingToWalletCurrency);
            wallet.setBalance(countNewBalance);
            walletRepo.save(wallet);
        }
    }

    private void recountBalanceIfTagChanged(TransactionDTO newTransaction, Transaction transaction) {
        if (newTransaction.getTagId() != 0 &&
                !newTransaction.getTagId().equals(transaction.getTag().getId())) {

            Wallet wallet = transaction.getWallet();
            Tag oldTag = transaction.getTag();
            Tag newTag = tagRepo.findById(newTransaction.getTagId());

            transaction.setTag(newTag);

            BigDecimal oldPriceAccordingToWalletCurrency = oldTag.getPrice()
                    .multiply(BigDecimal.valueOf(wallet.getCurrency().getTicker()));
            BigDecimal newPriceAccordingToWalletCurrency = newTag.getPrice()
                    .multiply(BigDecimal.valueOf(wallet.getCurrency().getTicker()));

            BigDecimal countNewBalance = (transaction.getType().equals("income")) ?
                    wallet.getBalance()
                            .subtract(oldPriceAccordingToWalletCurrency)
                            .add(newPriceAccordingToWalletCurrency) :
                    wallet.getBalance()
                            .add(oldPriceAccordingToWalletCurrency)
                            .subtract(newPriceAccordingToWalletCurrency);
            wallet.setBalance(countNewBalance);
            walletRepo.save(wallet);
        }
    }

}

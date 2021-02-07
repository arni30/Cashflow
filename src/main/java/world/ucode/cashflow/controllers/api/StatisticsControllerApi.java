package world.ucode.cashflow.controllers.api;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;

import org.springframework.web.bind.annotation.*;
import world.ucode.cashflow.models.dao.Transaction;
import world.ucode.cashflow.models.dao.Users;
import world.ucode.cashflow.models.dao.Wallet;
import world.ucode.cashflow.models.dto.StatisticDTO;
import world.ucode.cashflow.models.dto.TotalBalanceDTO;
import world.ucode.cashflow.repositories.TransactionRepo;
import world.ucode.cashflow.repositories.WalletRepo;
import world.ucode.cashflow.service.UsersDetails;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("/api/stat")
public class StatisticsControllerApi {
    private final TransactionRepo transactionRepo;
    private final WalletRepo walletRepo;

    @Autowired
    public StatisticsControllerApi(TransactionRepo transactionRepo,
                                   WalletRepo walletRepo) {
        this.transactionRepo = transactionRepo;
        this.walletRepo = walletRepo;
    }

    @GetMapping("/get30")
    public StatisticDTO getTransactionsForLast30Days
            (HttpServletRequest request, HttpServletResponse response,
             @RequestParam (value = "walletId") Integer walletId,
             @RequestParam (value = "startDate") String startDate,
             @RequestParam (value = "endDate") String endDate) throws IOException {

        log.info("GET STATS for user: " + request.getUserPrincipal().getName()) ;

        Users user = ((UsersDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUser();

        List<Transaction> transactions = null;
        try {
            if (walletId == 0) {
                transactions = transactionRepo.findByWallet_User_IdAndDateBetween(
                        user.getId(),
                        new Timestamp(stringToMillis(startDate)),
                        new Timestamp(stringToMillis(endDate) + 24 * 60 * 60 * 1000));
            } else {
                transactions = transactionRepo.findByWallet_IdAndWallet_User_IdAndDateBetween(
                        walletId, user.getId(),
                        new Timestamp(stringToMillis(startDate)),
                        new Timestamp(stringToMillis(endDate) + 24 * 60 * 60 * 1000));
            }
//            if (transactions == null || transactions.isEmpty()) {
//                throw new NoSuchElementException("Empty transaction list");
//            }

        } catch (Exception e) {
            log.info("Cannot get transactions, incorrect date");
            e.printStackTrace();
            response.sendError(400, "Bad Request");
        }
        List<Wallet> wallets = walletRepo.findByUser_Id(user.getId());
//        List<TotalBalanceDTO> total = countTotal(wallets, transactions);

        List<TotalBalanceDTO> total = (walletId == 0)
                ? countTotal(wallets, transactions)
                : countTotal(
                wallets.stream()
                        .filter(w -> w.getId().intValue() == walletId)
                        .collect(Collectors.toList()), transactions);

        return StatisticDTO.builder()
                .transactions(transactions)
                .wallets(wallets)
//                .currencies(currencyRepo.findAll())
//                .categories(categoryRepo.findAll())
//                .tags(tagRepo.findAll())
                .total(total)
                .build();
    }

    private long stringToMillis(String income) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = simpleDateFormat.parse(income);
        return date.getTime();
    }

    private List<TotalBalanceDTO> countTotal(List<Wallet> wallets, List<Transaction> transactions) {
        List<TotalBalanceDTO> total = new ArrayList<>();

        for (Wallet w : wallets) {
            BigDecimal totalIncomes = new BigDecimal(0);
            BigDecimal totalExpenses = new BigDecimal(0);

            for (Transaction t : transactions) {
                if (t.getWallet().equals(w)) {
                    if (t.getType().equals("income")) {
                        totalIncomes = totalIncomes.add(t.getTag().getPrice());
                    } else {
                        totalExpenses = totalExpenses.add(t.getTag().getPrice());
                    }
                }
            }
//            if (!(totalExpenses.intValue() == 0  && totalIncomes.intValue() == 0)) {
                total.add(TotalBalanceDTO.builder()
                        .wallet(w)
                        .totalIncomes(totalIncomes)
                        .totalExpenses(totalExpenses)
                        .totalBalance(totalIncomes.subtract(totalExpenses))
                        .build());
//            }
        }

        return total;
    }

}

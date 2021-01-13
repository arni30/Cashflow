package world.ucode.cashflow.controllers.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import world.ucode.cashflow.models.dao.Transaction;
import world.ucode.cashflow.models.dto.TransactionDTO;
import world.ucode.cashflow.repositories.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequestMapping("/api/transaction")
public class TransactionControllerApi {
    @Autowired
    private TransactionRepo transactionRepo;
    @Autowired
    private WalletRepo walletRepo;
    @Autowired
    private TagRepo tagRepo;
    @Autowired
    private CategoryRepo categoryRepo;

    @PostMapping("/create")
    public void createTransaction(@RequestBody Transaction transaction, HttpServletResponse response) throws IOException {
        try {
            System.out.println("HALLO");
            transactionRepo.save(transaction);
        }
        catch (Exception e) {
            e.printStackTrace();
            response.sendError(400, "Bad Request");
        }
    }

    @PostMapping("/update")
    public void updateTransaction(@RequestBody TransactionDTO newTransaction, HttpServletResponse response) throws IOException {
        try {
            Transaction transaction = transactionRepo.findById(newTransaction.getId());
            transaction.setWallet(newTransaction.getWalletId() == 0 ? transaction.getWallet() : walletRepo.findById(newTransaction.getWalletId()));
            transaction.setCategory(newTransaction.getCategoryId() == 0 ? transaction.getCategory() :categoryRepo.findById(newTransaction.getCategoryId()));
            transaction.setType(newTransaction.getType() == null ? transaction.getType() : newTransaction.getType());
            transaction.setTag(newTransaction.getTagId() == 0 ? transaction.getTag() : tagRepo.findById(newTransaction.getTagId()));
            transaction.setDate(newTransaction.getDate() == null ? transaction.getDate() : newTransaction.getDate());
            transaction.setDescription(newTransaction.getDescription() == null ? transaction.getDescription() : newTransaction.getDescription());
            transactionRepo.save(transaction);
        }
        catch (Exception e) {
            e.printStackTrace();
            response.sendError(400, "Bad Request");
        }
    }

    @PostMapping("/delete")
    public void deleteTransaction(@RequestBody Transaction transaction, HttpServletResponse response) throws IOException {
        try {
            transactionRepo.delete(transaction);
        }
        catch (Exception e) {
            e.printStackTrace();
            response.sendError(400, "Bad Request");
        }
    }
}

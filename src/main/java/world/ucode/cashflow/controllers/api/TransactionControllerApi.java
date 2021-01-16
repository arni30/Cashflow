package world.ucode.cashflow.controllers.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import world.ucode.cashflow.models.Transaction;
import world.ucode.cashflow.models.Users;
import world.ucode.cashflow.models.Wallet;
import world.ucode.cashflow.repositories.TransactionRepo;
import world.ucode.cashflow.repositories.UserRepo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/transaction")
public class TransactionControllerApi {
    @Autowired
    private TransactionRepo transactionRepo;

    @GetMapping("/get")
    public List<Transaction> getTransactions() {
        return transactionRepo.findAll();
    }

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
    public void updateTransaction(@RequestBody Transaction newTransaction, HttpServletResponse response) throws IOException {
        try {
            Transaction transaction = transactionRepo.findById(newTransaction.getId());
            transaction.setWallet(newTransaction.getWallet() == null ? transaction.getWallet() : newTransaction.getWallet());
            transaction.setCategory(newTransaction.getCategory() == null ? transaction.getCategory() : newTransaction.getCategory());
            transaction.setType(newTransaction.getType() == null ? transaction.getType() : newTransaction.getType());
            transaction.setTag(newTransaction.getTag() == null ? transaction.getTag() : newTransaction.getTag());
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

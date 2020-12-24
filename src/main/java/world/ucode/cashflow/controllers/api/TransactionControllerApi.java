package world.ucode.cashflow.controllers.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import world.ucode.cashflow.models.Transaction;
import world.ucode.cashflow.models.Users;
import world.ucode.cashflow.models.Wallet;
import world.ucode.cashflow.repositories.TransactionRepo;
import world.ucode.cashflow.repositories.UserRepo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequestMapping("/api/transaction")
public class TransactionControllerApi {
    @Autowired
    private TransactionRepo transactionRepo;

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
            transaction.setWallet(newTransaction.getWallet() == null ? newTransaction.getWallet() : transaction.getWallet());
            transaction.setCategory(newTransaction.getCategory() == null ? newTransaction.getCategory() : transaction.getCategory());
            transaction.setType(newTransaction.getType() == null ? newTransaction.getType() : transaction.getType());
            transaction.setTag(newTransaction.getTag() == null ? newTransaction.getTag() : transaction.getTag());
            transaction.setDate(newTransaction.getDate() == null ? newTransaction.getDate() : transaction.getDate());
            transaction.setDescription(newTransaction.getDescription() == null ? newTransaction.getDescription() : transaction.getDescription());
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

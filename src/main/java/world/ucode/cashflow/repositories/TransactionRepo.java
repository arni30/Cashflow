package world.ucode.cashflow.repositories;

import org.springframework.data.repository.CrudRepository;
import world.ucode.cashflow.models.Transaction;
import world.ucode.cashflow.models.Users;
import world.ucode.cashflow.models.Wallet;

import java.util.List;
import java.util.Optional;

public interface TransactionRepo extends CrudRepository<Transaction, Long> {
    List<Transaction> findAll();
    List<Transaction> findByWallet_User_Id(int userId);
    List<Transaction> findByWallet_Id(int walletId);
    List<Transaction> findByCategory_Id(int categoryId);
    List<Transaction> findByTag_Id(int tagId);
    Transaction findById(int id);

    void deleteAllByWalletId(int walletId);
//    void deleteTransactionByWallet_Id(int walletId);

//    void update(Transaction transaction);
}

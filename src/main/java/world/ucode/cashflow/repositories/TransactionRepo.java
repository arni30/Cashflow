package world.ucode.cashflow.repositories;

import org.springframework.data.repository.CrudRepository;
import world.ucode.cashflow.models.dao.Transaction;

import java.sql.Timestamp;
import java.util.List;

public interface TransactionRepo extends CrudRepository<Transaction, Long> {
    List<Transaction> findAll();
    List<Transaction> findByWallet_User_Id(Integer userId);

    List<Transaction> findByWallet_User_IdAndDateBetween(Integer userId,
                                                         Timestamp today,
                                                         Timestamp thirtyDaysBefore);

    List<Transaction> findByWallet_Id(Integer walletId);
    List<Transaction> findByCategory_Id(Integer categoryId);
    List<Transaction> findByTag_Id(Integer tagId);
    Transaction findById(int id);

    void deleteById(Integer id);
    void deleteTransactionsByWallet_Id(Integer walletId);
//    void deleteAllByWalletId(Long walletId);
//    void deleteTransactionByWallet_Id(int walletId);

//    void update(Transaction transaction);
}

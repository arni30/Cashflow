package world.ucode.cashflow.repositories;

import org.springframework.data.repository.CrudRepository;
import world.ucode.cashflow.models.dao.Wallet;

import java.util.List;
import java.util.Optional;

public interface WalletRepo extends CrudRepository<Wallet, Long> {
    List<Wallet> findAll();
    List<Wallet> findByCurrency_Id(int currencyId);
    List<Wallet> findByUser_Id(int userId);
    Optional<Wallet> findById(Integer id);
//    Optional<Wallet> findByName(String name);
}

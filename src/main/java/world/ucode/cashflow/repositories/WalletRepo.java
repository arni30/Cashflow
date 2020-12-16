package world.ucode.cashflow.repositories;

import org.springframework.data.repository.CrudRepository;
import world.ucode.cashflow.models.Wallet;

public interface WalletRepo extends CrudRepository<Wallet, Long> {
}

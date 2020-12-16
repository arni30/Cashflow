package world.ucode.cashflow.repositories;

import org.springframework.data.repository.CrudRepository;
import world.ucode.cashflow.models.Message;
import world.ucode.cashflow.models.Transaction;

public interface TransactionRepo extends CrudRepository<Transaction, Long> {
}

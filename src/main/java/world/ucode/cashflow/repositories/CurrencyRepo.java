package world.ucode.cashflow.repositories;

import org.springframework.data.repository.CrudRepository;
import world.ucode.cashflow.models.Message;

public interface CurrencyRepo extends CrudRepository<Message, Long> {
}

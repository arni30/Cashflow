package world.ucode.cashflow.repositories;

import org.springframework.data.repository.CrudRepository;
import world.ucode.cashflow.models.Currency;

public interface CurrencyRepo extends CrudRepository<Currency, Long> {
}

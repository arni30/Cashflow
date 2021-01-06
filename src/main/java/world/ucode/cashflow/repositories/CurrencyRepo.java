package world.ucode.cashflow.repositories;

import org.springframework.data.repository.CrudRepository;
import world.ucode.cashflow.models.Currency;
import world.ucode.cashflow.models.Tag;

public interface CurrencyRepo extends CrudRepository<Currency, Long> {
    Currency findById(int id);
}

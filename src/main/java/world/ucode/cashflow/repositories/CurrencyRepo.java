package world.ucode.cashflow.repositories;

import org.springframework.data.repository.CrudRepository;
import world.ucode.cashflow.models.dao.Currency;

import java.util.List;

public interface CurrencyRepo extends CrudRepository<Currency, Long> {
    Currency findById(Integer id);
    List<Currency> findAll();
}

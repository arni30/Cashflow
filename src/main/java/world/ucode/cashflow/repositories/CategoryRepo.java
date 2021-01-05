package world.ucode.cashflow.repositories;

import org.springframework.data.repository.CrudRepository;
import world.ucode.cashflow.models.Category;
import world.ucode.cashflow.models.Transaction;

public interface CategoryRepo extends CrudRepository<Category, Long> {
    Category findById(int id);
}

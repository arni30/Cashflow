package world.ucode.cashflow.repositories;

import org.springframework.data.repository.CrudRepository;
import world.ucode.cashflow.models.Category;
import world.ucode.cashflow.models.Transaction;

import java.util.List;

public interface CategoryRepo extends CrudRepository<Category, Long> {
    List<Category> findAll();
    Category findById(int id);
}

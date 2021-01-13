package world.ucode.cashflow.repositories;

import org.springframework.data.repository.CrudRepository;
import world.ucode.cashflow.models.dao.Tag;

public interface TagRepo extends CrudRepository<Tag, Long> {
    Tag findById(int id);
}

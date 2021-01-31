package world.ucode.cashflow.repositories;

import org.springframework.data.repository.CrudRepository;
import world.ucode.cashflow.models.dao.Tag;

import java.util.List;

public interface TagRepo extends CrudRepository<Tag, Long> {
    Tag findById(int id);
    List<Tag> findAll();
}

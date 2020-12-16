package world.ucode.cashflow.repositories;

import org.springframework.data.repository.CrudRepository;
import world.ucode.cashflow.models.Message;

public interface UserRepo extends CrudRepository<Message, Long> {
}

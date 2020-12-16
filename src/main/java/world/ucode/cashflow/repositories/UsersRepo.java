package world.ucode.cashflow.repositories;

import org.springframework.data.repository.CrudRepository;
import world.ucode.cashflow.models.Message;
import world.ucode.cashflow.models.Users;

public interface UsersRepo extends CrudRepository<Users, Long> {
}

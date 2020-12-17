package world.ucode.cashflow.repositories;

import org.springframework.data.repository.CrudRepository;
import world.ucode.cashflow.models.Users;

import java.util.List;

public interface UserRepo extends CrudRepository<Users, Long> {
    List<Users> findByLogin(String login);
}

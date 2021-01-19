package world.ucode.cashflow.repositories;

import org.springframework.data.repository.CrudRepository;
import world.ucode.cashflow.models.dao.Users;

import java.util.List;

public interface UserRepo extends CrudRepository<Users, Long> {
    Users findByLogin(String login);
    Users findById(int id);
    Users findByLoginAndValidationStatus(String login, int status);
    Users findByLoginAndValidationStatus(String login, int status);
    List<Users> findByToken(String token);
}

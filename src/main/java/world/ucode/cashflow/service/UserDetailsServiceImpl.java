package world.ucode.cashflow.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import world.ucode.cashflow.models.dao.Users;
import world.ucode.cashflow.repositories.UserRepo;

public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepo userRepository;

    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {
        System.out.println(username);
        Users user = userRepository.findByLogin(username);
        if (user == null || user.getValidationStatus() == 0) {
            throw new UsernameNotFoundException("Could not find user");
        }

        return new UsersDetails(user);
    }
}
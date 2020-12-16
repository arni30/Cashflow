package world.ucode.cashflow.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import world.ucode.cashflow.models.Users;
import world.ucode.cashflow.repositories.UserRepo;

public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepo userRepository;

    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {
        Users user = userRepository.findByLogin(username).get(0);

        if (user == null) {
            throw new UsernameNotFoundException("Could not find user");
        }

        return new UsersDetails(user);
    }
}
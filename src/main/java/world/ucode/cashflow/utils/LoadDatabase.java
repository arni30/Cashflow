package world.ucode.cashflow.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import world.ucode.cashflow.models.Users;
import world.ucode.cashflow.repositories.UserRepo;

@Configuration
class LoadDatabase {
    @Autowired
    private UserRepo userRepo;

    @Value("${mail.username}")
    private String adminEmail;
    @Value("${admin.name}")
    private String name;
    @Value("${admin.password}")
    private String password;

    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
    CommandLineRunner initDatabase() {
        Users user = new Users(1, name, password, adminEmail);
        return args -> {
            log.info("Preloading " + userRepo.save(user));
        };
    }
}

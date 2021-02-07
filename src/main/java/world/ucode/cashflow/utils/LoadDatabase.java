package world.ucode.cashflow.utils;

import lombok.Getter;
import lombok.Setter;
import lombok.Value;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;
import world.ucode.cashflow.models.Role;
import world.ucode.cashflow.models.dao.Category;
import world.ucode.cashflow.models.dao.Currency;
import world.ucode.cashflow.models.dao.Tag;
import world.ucode.cashflow.models.dao.Users;
import world.ucode.cashflow.repositories.CategoryRepo;
import world.ucode.cashflow.repositories.CurrencyRepo;
import world.ucode.cashflow.repositories.TagRepo;
import world.ucode.cashflow.repositories.UserRepo;

import java.io.File;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Slf4j
@Setter
@Getter
@Component
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "application")
public class LoadDatabase {
    private List<List<String>> users;
    private List<Currency> currency;
    private List<Category> categories;
    private List<Tag> tags;

    private Token token;
    private final UserRepo userRepo;
    private final CurrencyRepo currencyRepo;
    private final CategoryRepo categoryRepo;
    private final TagRepo tagRepo;
    @Autowired
    public LoadDatabase(UserRepo userRepo, Token token,
                        CurrencyRepo currencyRepo,
                        CategoryRepo categoryRepo,
                        TagRepo tagRepo) {
        this.userRepo = userRepo;
        this.token = token;
        this.currencyRepo = currencyRepo;
        this.categoryRepo = categoryRepo;
        this.tagRepo = tagRepo;
    }

    @Bean
    CommandLineRunner initDatabase() {
        if (users == null) {
            return args -> log.info("Preloading database unable");
        }
        users.forEach(u -> {
            if (userRepo.findByLogin(u.get(1)) == null) {
                Users user = new Users();
                user.setLogin(u.get(1));
                user.setEmail(u.get(0));
                user.setRoles(Collections.singleton(Role.USER));
                user.setPassword(BCrypt.hashpw(u.get(2), BCrypt.gensalt()));
                user.setToken(token.getJWTToken(u.get(1)));
                user.setValidationStatus(1);
                userRepo.save(user);
            }
        });
        currency.forEach(currencyRepo::save);
        categories.forEach(categoryRepo::save);
        tags.forEach(tagRepo::save);
        deletePreloadInfo();
        return args -> log.info("Preloading database done.");
    }

    /**
     * need to change(delete file for init)
     * to prevent second initialization if rerun spring
     */
    private void deletePreloadInfo() {
        File file = new File("src/main/resources/application.yaml");
        try (PrintWriter writer = new PrintWriter(file)) {
            writer.print("");
        } catch (java.io.FileNotFoundException e) {
            System.err.println("Can't free file");
        }
    }

//    @Value("${mail.username}")
//    private String adminEmail;
//    @Value("${admin.name}")
//    private String name;
//    @Value("${admin.password}")
//    private String password;

    //    @Autowired
//    private DataSource dataSource;
//
//    @EventListener(ApplicationReadyEvent.class)
//    public void loadData() {
//        ResourceDatabasePopulator resourceDatabasePopulator =
//                new ResourceDatabasePopulator(false, false,
//                        "UTF-8", new ClassPathResource("sql/insert_test.sql"));
//        resourceDatabasePopulator.execute(dataSource);
//    }
}

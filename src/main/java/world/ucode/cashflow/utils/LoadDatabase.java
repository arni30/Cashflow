package world.ucode.cashflow.utils;

import lombok.Getter;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.EventListener;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;
import world.ucode.cashflow.models.dao.Users;
import world.ucode.cashflow.repositories.UserRepo;

import javax.sql.DataSource;
import java.io.File;
import java.io.PrintWriter;
import java.util.List;
import lombok.Data;

@Setter
@Getter
@Component
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "application")
public class LoadDatabase {
    @Autowired
    private UserRepo userRepo;
    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    private List<List<String>> users;

    @Bean
    CommandLineRunner initDatabase() {
        if (users == null) {
            return args -> log.info("Preloading unable");
        }
        for (List<String> u : users) {
            Users user = new Users(u.get(0), u.get(1), u.get(2));
            userRepo.save(user);
        }

        deletePreloadInfo();
        return args -> log.info("Preloading done: " + users);
    }

    private void deletePreloadInfo() {
        // need to change(delete file for init) to prevent second initialization if rerun spring
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

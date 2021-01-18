package world.ucode.cashflow.utils;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;

import javax.sql.DataSource;

@Component
public class LoadDatabase {
    @Autowired
    private DataSource dataSource;

    @EventListener(ApplicationReadyEvent.class)
    public void loadData() {
        ResourceDatabasePopulator resourceDatabasePopulator =
                new ResourceDatabasePopulator(false, false,
                        "UTF-8", new ClassPathResource("sql/insert_test.sql"));
        resourceDatabasePopulator.execute(dataSource);
    }
}

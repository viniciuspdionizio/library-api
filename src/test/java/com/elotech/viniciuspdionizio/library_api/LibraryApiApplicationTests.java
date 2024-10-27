package com.elotech.viniciuspdionizio.library_api;

import org.flywaydb.core.Flyway;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

@SpringBootTest
@Profile("test")
class LibraryApiApplicationTests {
    @Autowired
    private Flyway flyway;

    @Test
    void contextLoads() {
    }

    @Bean
    public void resetDatabase() {
        flyway.clean();
        flyway.migrate();
    }

}

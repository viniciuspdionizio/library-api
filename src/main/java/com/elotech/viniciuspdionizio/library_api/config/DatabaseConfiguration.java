package com.elotech.viniciuspdionizio.library_api.config;

import org.springframework.boot.autoconfigure.flyway.FlywayMigrationStrategy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
public class DatabaseConfiguration {

    @Bean
    @Profile("test")
    public FlywayMigrationStrategy migrationStrategyTest() {
        return flyway -> {
            flyway.clean();
            flyway.migrate();
        };
    }

}

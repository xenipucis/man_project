package at.tiby.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import at.tiby.repository.DogeRepository;
import at.tiby.repository.ProductionDogeRepository;

@Configuration
public class DogeConfig {

    @Bean
    public DogeRepository dogeRepository() {
        return new ProductionDogeRepository();
    }
}

package at.tiby

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

import at.tiby.repository.DogeRepository;
import at.tiby.repository.TestDogeRepository;

@Profile("test")
@SpringBootApplication
public class TestConfig {

	public static void main(final String[] args) {
		SpringApplication.run(TestConfig.class, args);
	}
	
	@Bean
	public DogeRepository dogeRepository() {
		return new TestDogeRepository();
	}
	
	
}

package at.tiby;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

import at.tiby.repository.DogeRepository;
import at.tiby.repository.TestDogeRepository;
import at.tiby.repository.TestUserRepository;
import at.tiby.repository.IUserRepository;

@Profile("test")
@SpringBootApplication
public class TestApplication {

	public static void main(final String[] args) {
		SpringApplication.run(TestApplication.class, args);
	}
	
	@Bean
	public DogeRepository dogeRepository() {
		return new TestDogeRepository();
	}
	
	@Bean
	public IUserRepository userRepository() {
		return new TestUserRepository();
	}
	
	
}

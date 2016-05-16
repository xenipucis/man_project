package at.tiby.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import at.tiby.TestApplication;
import at.tiby.model.User;

@ActiveProfiles("test")
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(TestApplication.class)
@IntegrationTest
public class JUnitExampleTest {
	
	@Autowired
	IUserRepository userRepository;

	@Test
	public void findAllUsers() {
		Set<User> users = userRepository.findAll();
		assertNotNull(users);
		assertTrue(userRepository != null);
	}
	
	@Test
	public void findUserById() {
		User user = userRepository.findUserById(1);
		assertNotNull(user);
	}
	
	@Test
	public void createUser() {
		User user = User
						.builder()
						.id(0)
						.name("Siva")
						.email("siva@gmail.com")
						.build();
		User savedUser = userRepository.create(user);
		User newUser = userRepository.findUserById(savedUser.getId());
		assertEquals(user.getName(), newUser.getName());
		assertEquals(user.getEmail(), newUser.getEmail());
		
	}
}

package at.tiby

import at.tiby.model.User
import at.tiby.repository.IUserRepository
import at.tiby.repository.UserRepository
import spock.lang.Shared;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertNotNull;

import org.junit.Test
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.SpringApplicationContextLoader;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@ActiveProfiles("test")
@SpringApplicationConfiguration(TestApplication.class)
@IntegrationTest
class DbTestSpec extends spock.lang.Specification {

	@Autowired
	IUserRepository userRepository
	
	def "find user by Id"() {
		given: "an id"
			def id = 2
		when: "lookup in db to retrieve information about this id"
			User user = userRepository.findUserById(id)
		then: "information about this id exists"
			user
			user.id == 2
	}
	
	def "create user"() {
		given: "A new User" 
			User user = User
							.builder()
							.name("mumu")
							.email("mumu@yahoo.com")
							.build() 
		expect: "we have userRepository not null"
			userRepository
		when: "save user in database"
			User savedUser = userRepository.create(user)
		and: "try to retrieve the saved user from database"
			User newUser = userRepository.findUserById(savedUser.getId())
		then: "verify user id, name and email"
			user == newUser
	}
	
	def "find all users"() {
		when: "lookup for all users in db"
			Set<User> users = userRepository.findAll();
		then: "we have users"
			users
			!users.isEmpty()
	}
}

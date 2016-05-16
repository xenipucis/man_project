package at.tiby.repository;

import java.util.Set;

import org.springframework.test.context.ActiveProfiles;

import at.tiby.model.User;

@ActiveProfiles("test")
public class TestUserRepository extends UserRepository {

	@Override
	public Set<User> findAll() {
		return super.findAll();
	}

	@Override
	public User findUserById(int id) {
		return super.findUserById(id);
	}

	@Override
	public User create(User user) {
		return super.create(user);
	}

}

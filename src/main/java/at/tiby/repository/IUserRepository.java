package at.tiby.repository;

import java.util.Set;

import at.tiby.model.User;

public interface IUserRepository {

	Set<User> findAll();
	
	User findUserById(int id);
	
	User create(final User user);
}

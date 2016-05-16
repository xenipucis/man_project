package at.tiby.repository;

import java.util.List;

import at.tiby.model.User;

public interface IUserRepository {

	List<User> findAll();
	
	User findUserById(int id);
	
	User create(final User user);
}

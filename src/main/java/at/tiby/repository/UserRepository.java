package at.tiby.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import at.tiby.model.User;

@Repository
public class UserRepository implements IUserRepository {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	
	@Transactional(readOnly=true)
	public Set<User> findAll() {
		final String SELECT_ALL_USERS = "select * from users";
		return jdbcTemplate.query(SELECT_ALL_USERS,
				new UserRowMapper()).parallelStream().collect(Collectors.toSet());
	}
	
	
	/*
	@Transactional(readOnly=true)
	public Set<User> findAll() {
		final String SELECT_ALL_USERS = "select * from users";
		return JdbcStreams.streamQuery(jdbcTemplate
				, SELECT_ALL_USERS
				, stream -> stream
				            .map(srs -> User
								.builder()
								.id(srs.getInt("id"))
								.name(srs.getString("name"))
								.email(srs.getString("email"))
								.build()
							)
			    .collect(Collectors.toSet())
			    );
		
		
	}
	*/
	
	/*
	@Transactional(readOnly=true)
	public User findUserById(int id) {
		return jdbcTemplate.queryForObject("select * from users where id = ?"
				, new Object[]{id}, new UserRowMapper());
	}*/
	
	
	
	
	@Transactional(readOnly=true)
	public User findUserById(final int id) {
		final String SELECT_USER_BY_ID = "select * from users where id = ?";
		return jdbcTemplate.queryForObject(
				SELECT_USER_BY_ID
				, (rs, rowNum) -> {
					return User
							.builder()
							.id(rs.getInt("id"))
							.name(rs.getString("name"))
							.email(rs.getString("email"))
							.build();
				},
				id);
	}
	
	public User create(final User user) {
		final String sql = "insert into users(name, email) values(?,?)";
		
		KeyHolder holder = new GeneratedKeyHolder();
		jdbcTemplate.update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
				ps.setString(1, user.getName());
				ps.setString(2,  user.getEmail());
				return ps;
			}
		}, holder);
		
		int newUserId = holder.getKey().intValue();
		user.setId(newUserId);
		return user;
	}

	
	class UserRowMapper implements RowMapper<User> {
		@Override
		public User mapRow(ResultSet rs, int rowNum) throws SQLException {
			User user = User
						.builder()
						.id(rs.getInt("id"))
						.name(rs.getString("name"))
						.email(rs.getString("email")).build();
			return user;
		}
	}
	
}

package com.java.repository;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.tomcat.dbcp.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.java.components.User;

@Repository("userrep")
//@DependsOn("flyway")
public class UserRepositoryImpl implements UserRepository {

	@Autowired
	BasicDataSource ds;

	@Autowired
	JdbcTemplate template;

	private static final class UserMapper implements RowMapper<User> {
		@Override
		public User mapRow(ResultSet rs, int arg) throws SQLException {
			User user = new User();
			user.setFirstName(rs.getString("firstName"));
			user.setLastName(rs.getString("lastName"));
			user.setPassword(rs.getString("password"));
			user.setUserName(rs.getString("userName"));
			user.setUserId(rs.getInt("userId"));
			if (rs.getString("userRole").equalsIgnoreCase("Admin")) {
				user.setRole(User.Role.ADMIN);
			} else {
				user.setRole(User.Role.CUSTOMER);
			}
			return user;
		}
	}

	public User getUser(String userName) {
		return template.queryForObject("select *  from userLogin where userName=? ", new Object[] { userName },
				new UserMapper());
	}

	public void addUser(User user) {
		String statement = "insert into userLogin(userName, password, firstName, lastName, userRole) values (?,?,?,?,?)";
		template.update(statement, user.getUserName(), user.getPassword(), user.getFirstName(), user.getLastName(),
				user.getRole());
	}

}

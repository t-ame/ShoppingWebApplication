package com.java.repository;

import org.apache.tomcat.dbcp.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Repository;

@Repository("userrep")
@DependsOn("flyway")
public class UserRepositoryImpl implements UserRepository{

	@Autowired
	BasicDataSource ds;

}

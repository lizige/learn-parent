package com.wht.system.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.wht.system.model.UrlPermission;
import com.wht.system.model.User;

public interface UserRepository extends JpaRepository<User, Long>,UserDAO {

	
	@Query(" from User o where o.name=:name")
	public User getByName(@Param("name")String name);
	
	@Query(" from UrlPermission o ")
	public List<UrlPermission> getAllUrlPermission();
	
	
	public User getByLoginName(String loginName);
	
	
	
	
}

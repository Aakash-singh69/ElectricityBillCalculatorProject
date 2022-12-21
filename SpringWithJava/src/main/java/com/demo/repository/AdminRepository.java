package com.demo.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.demo.pojo.Admin;
@Repository
public interface AdminRepository extends CrudRepository<Admin, Integer>{
	
	@Query(value = "select * from admin where user_name=:username and password=:password", nativeQuery = true)
	Admin validateAdmin(@Param("username") String username, @Param("password") String password);

}

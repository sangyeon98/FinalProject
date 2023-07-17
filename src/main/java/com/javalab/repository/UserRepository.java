package com.javalab.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.javalab.dto.UserDTO;
import com.javalab.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	User findByUserName(String username);
	
	User findByUserId(String userId);

	@Query("select u from User u where u.userId = :#{#user.userId} and u.userPassword = :#{#user.userPassword}")
	User findById(@Param("user") UserDTO userDTO);
    
	void deleteByUserId(String userId);
}

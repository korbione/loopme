package com.dakor.app.data.dao;

import com.dakor.app.data.entity.UserEntity;
import com.dakor.app.data.entity.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * .
 *
 * @author dkor
 */
public interface IUserDao extends JpaRepository<UserEntity, Integer> {

	UserEntity findByName(String name);

	@Query(value = "select u from UserEntity u where u.role < :role")
	List<UserEntity> findUsers(@Param("role") UserRole role);
}

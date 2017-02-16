package com.dakor.app.data.dao;

import com.dakor.app.data.entity.UserEntity;
import com.dakor.app.data.entity.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.List;

/**
 * .
 *
 * @author dkor
 */
@PreAuthorize("hasRole('ADMIN') or hasRole('ADOPS')")
public interface IUserDao extends CrudRepository<UserEntity, Integer> {

	UserEntity findByName(String name);

	@PreAuthorize("hasRole('ADMIN') or hasRole('ADOPS')")
	@Query(value = "select u from UserEntity u where u.role <= :role")
	List<UserEntity> findUsers(@Param("role") UserRole role);
}

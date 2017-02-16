package com.dakor.app.data.dao;

import com.dakor.app.data.entity.UserEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.security.access.prepost.PostAuthorize;

import java.util.List;

/**
 * .
 *
 * @author dkor
 */
@PostAuthorize("hasRole('ADMIN') or hasRole('ADOPS')")
public interface ICustomizedUserDao extends Repository<UserEntity, Integer> {

	@Query(value = "select u from UserEntity u where u.role < ?#{principal.role.ordinal()}")
	List<UserEntity> findUsers();
}

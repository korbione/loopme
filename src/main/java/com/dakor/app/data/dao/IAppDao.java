package com.dakor.app.data.dao;

import com.dakor.app.data.entity.AppEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * .
 *
 * @author dkor
 */
public interface IAppDao extends JpaRepository<AppEntity, Integer> {
}

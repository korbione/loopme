package com.dakor.app.service.impl.assembler;

import com.dakor.app.data.entity.UserEntity;
import com.dakor.app.model.UserDto;

/**
 * .
 *
 * @author dkor
 */
public interface IUserAssembler {
	UserDto assembly(UserEntity entity);
	UserEntity assembly(UserDto dto);
}

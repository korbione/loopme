package com.dakor.app.service.impl.assembler;

import com.dakor.app.data.entity.UserEntity;
import com.dakor.app.service.dto.UserDto;
import org.springframework.stereotype.Component;

/**
 * .
 *
 * @author dkor
 */
@Component
public class UserAssembler implements IUserAssembler {

	@Override
	public UserDto assembly(UserEntity entity) {
		UserDto dto = null;
		if (entity != null) {
			dto = new UserDto();
			dto.setId(entity.getId());
			dto.setUserName(entity.getName());
			dto.setPassword(entity.getPassword());
			dto.setEmail(entity.getEmail());
			dto.setRole(entity.getRole());
		}

		return dto;
	}

	@Override
	public UserEntity assembly(UserEntity orig, UserDto dto) {
		UserEntity entity = orig != null ? orig : null;
		if (dto != null) {
			if (entity == null) {
				entity = new UserEntity();
			}
			entity.setName(dto.getUserName());
			entity.setPassword(dto.getPassword());
			entity.setEmail(dto.getEmail());
			entity.setRole(dto.getRole());
		}

		return entity;
	}

	@Override
	public UserEntity assembly(UserDto dto) {
		UserEntity entity = null;
		if (dto != null) {
			entity = new UserEntity();
			entity.setId(dto.getId());
			entity.setName(dto.getUserName());
			entity.setPassword(dto.getPassword());
			entity.setEmail(dto.getEmail());
			entity.setRole(dto.getRole());
		}

		return entity;
	}
}

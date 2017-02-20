package com.dakor.app.service.impl.assembler;

import com.dakor.app.data.entity.AppEntity;
import com.dakor.app.data.entity.ContentType;
import com.dakor.app.service.dto.ProductDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * .
 *
 * @author dkor
 */
@Component
public class AppAssembler implements IAppAssembler {
	private IUserAssembler userAssembler;

	@Autowired
	public void setUserAssembler(IUserAssembler userAssembler) {
		this.userAssembler = userAssembler;
	}

	@Override
	public ProductDto assembly(AppEntity entity) {
		ProductDto dto = null;
		if (entity != null) {
			dto = new ProductDto();
			dto.setId(entity.getId());
			dto.setName(entity.getName());
			dto.setType(entity.getType());
			dto.setContentTypes(entity.getContentTypes());
			dto.setOwner(userAssembler.assembly(entity.getUser()));
		}

		return dto;
	}

	@Override
	public AppEntity assembly(ProductDto dto) {
		AppEntity entity = null;
		if (dto != null) {
			entity = new AppEntity();
			entity.setId(dto.getId());
			entity.setName(dto.getName());
			entity.setType(dto.getType());
			List<ContentType> contentTypes = new ArrayList<>();
			contentTypes.addAll(dto.getContentTypes());
			entity.setContentTypes(contentTypes);
			entity.setUser(userAssembler.assembly(dto.getOwner()));
		}

		return entity;
	}
}

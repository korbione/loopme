package com.dakor.app.service.impl.assembler;

import com.dakor.app.data.entity.AppEntity;
import com.dakor.app.service.dto.ProductDto;

/**
 * .
 *
 * @author dkor
 */
public interface IAppAssembler {

	ProductDto assembly(AppEntity entity);

	AppEntity assembly(ProductDto dto);

	AppEntity assembly(AppEntity origAppEntity, ProductDto dto);
}

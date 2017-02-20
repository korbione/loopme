package com.dakor.app.service;

import com.dakor.app.service.dto.ProductDto;

import java.util.List;

/**
 * .
 *
 * @author dkor
 */
public interface IProductService {
	List<ProductDto> getProducts();

	ProductDto save(ProductDto product);

	void deleteById(Integer productId);
}

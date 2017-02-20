package com.dakor.app.service.impl;

import com.dakor.app.data.dao.IAppDao;
import com.dakor.app.data.entity.AppEntity;
import com.dakor.app.data.entity.UserEntity;
import com.dakor.app.data.entity.UserRole;
import com.dakor.app.service.AppContext;
import com.dakor.app.service.IProductService;
import com.dakor.app.service.UserDetailsService;
import com.dakor.app.service.dto.ProductDto;
import com.dakor.app.service.impl.assembler.IAppAssembler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * .
 *
 * @author dkor
 */
@Service
@SuppressWarnings("unused")
public class ProductService implements IProductService {
	private AppContext appContext;
	private IAppDao appDao;
	private IAppAssembler assembler;

	@Autowired
	public void setAppContext(AppContext appContext) {
		this.appContext = appContext;
	}

	@Autowired
	public void setAppDao(IAppDao appDao) {
		this.appDao = appDao;
	}

	@Autowired
	public void setAssembler(IAppAssembler assembler) {
		this.assembler = assembler;
	}

	@Transactional(readOnly = true)
	@Override
	public List<ProductDto> getProducts() {
		List<AppEntity> entities;
		UserDetailsService.User currentUser = appContext.getCurrentUser();
		if (currentUser.getRole() == UserRole.PUBLISHER) {
			UserEntity userEntity = new UserEntity();
			userEntity.setName(currentUser.getUsername());
			AppEntity appEntity = new AppEntity();
			appEntity.setUser(userEntity);
			entities = appDao.findAll(Example.of(appEntity));
		} else {
			entities = appDao.findAll();
		}

		return entities.stream().map(entity -> assembler.assembly(entity)).collect(Collectors.toList());
	}

	@Transactional
	@Override
	public ProductDto save(ProductDto product) {
		if (product != null) {
			AppEntity origEntity = null;
			if (product.getId() != null) {
				// for update the entity
				origEntity = appDao.getOne(product.getId());
			}

			AppEntity appEntity = assembler.assembly(origEntity, product);
			AppEntity savedUserEntity = appDao.saveAndFlush(appEntity);
			product = assembler.assembly(savedUserEntity);
		}

		return product;
	}

	@Transactional
	@Override
	public void deleteById(Integer productId) {
		appDao.delete(productId);
	}
}

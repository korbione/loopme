package com.dakor.app.mvc.controller;

import com.dakor.app.data.entity.AppType;
import com.dakor.app.data.entity.ContentType;
import com.dakor.app.data.entity.UserRole;
import com.dakor.app.mvc.model.OwnerModel;
import com.dakor.app.mvc.model.ProductModel;
import com.dakor.app.service.IProductService;
import com.dakor.app.service.IUserService;
import com.dakor.app.service.UserDetailsService;
import com.dakor.app.service.dto.ProductDto;
import com.dakor.app.service.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * .
 *
 * @author dkor
 */
@Controller
@RequestMapping("app/products")
public class ProductsController extends AbstractController {
	private IProductService productService;
	private IUserService userService;

	@Autowired
	public void setProductService(IProductService productService) {
		this.productService = productService;
	}

	@Autowired
	public void setUserService(IUserService userService) {
		this.userService = userService;
	}

	@GetMapping
	public String getProducts(Model model) {
		List<ProductModel> products = productService.getProducts().stream().map(this::convert).collect(Collectors.toList());
		Collections.reverse(products);
		model.addAttribute("products", products);

		return "fragments/products :: products-table";
	}

	@PostMapping(value = "form")
	public String showDialog(@RequestBody(required = false) ProductModel product, Model model) {
		if (product == null) {
			product = new ProductModel();
			UserDetailsService.User user = getAppContext().getCurrentUser();
			product.setOwner(new OwnerModel(user.getId(), user.getUsername()));
		}
		model.addAttribute("product", product);

		return "fragments/product_dialog :: form";
	}

	@DeleteMapping("{id}")
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void removeProduct(@PathVariable("id") int productId) {
		productService.deleteById(productId);
	}

	@PostMapping("save")
	public String saveProduct(@Valid @ModelAttribute("product") ProductModel productModel, BindingResult bindingResult,
			Model model) {
		if (bindingResult.hasErrors()) {
			return "fragments/product_dialog :: product-form";
		}

		ProductDto dto = new ProductDto();
		dto.setId(productModel.getId());
		dto.setName(productModel.getName());
		dto.setType(productModel.getType());
		dto.setContentTypes(productModel.getContentTypes());
		OwnerModel owner = productModel.getOwner();
		UserDto user = new UserDto();
		user.setId(owner.getId());
		user.setUserName(owner.getName());
		dto.setOwner(user);
		ProductDto savedProduct = productService.save(dto);

		model.addAttribute("products", Collections.singletonList(convert(savedProduct)));

		return "fragments/products :: row";
	}

	@ModelAttribute("allOwners")
	public List<OwnerModel> createAllOwnersList() {
		UserDetailsService.User currentUser = getAppContext().getCurrentUser();
		if (currentUser.getRole() == UserRole.PUBLISHER) {
			return Collections.singletonList(new OwnerModel(currentUser.getId(), currentUser.getUsername()));
		} else {
			return userService.getUsers().stream()
					.map(user -> new OwnerModel(user.getId(), user.getUserName())).collect(Collectors.toList());
		}
	}

	@ModelAttribute("allTypes")
	public List<AppType> createAppTypesList() {
		return Arrays.asList(AppType.values());
	}

	@ModelAttribute("allContentTypes")
	public List<ContentType> createContentTypesList() {
		return Arrays.asList(ContentType.values());
	}

	private ProductModel convert(ProductDto productDto) {
		ProductModel model = null;
		if (productDto != null) {
			model = new ProductModel();
			model.setId(productDto.getId());
			model.setName(productDto.getName());
			model.setType(productDto.getType());
			model.setContentTypes(productDto.getContentTypes());

			if (productDto.getOwner() != null) {
				OwnerModel owner = new OwnerModel();
				owner.setId(productDto.getOwner().getId());
				owner.setName(productDto.getOwner().getUserName());
				model.setOwner(owner);
			}
		}

		return model;
	}
}

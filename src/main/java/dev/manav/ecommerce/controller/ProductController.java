package dev.manav.ecommerce.controller;

import dev.manav.ecommerce.dto.ErrorDto;
import dev.manav.ecommerce.dto.ProductRequestDto;
import dev.manav.ecommerce.exceptions.ProductNotFoundException;
import dev.manav.ecommerce.model.Product;
import dev.manav.ecommerce.model.ProductCategory;
import dev.manav.ecommerce.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class ProductController {
    @Autowired
    @Qualifier("FakeStoreProductService")
    private ProductService productService;

    @GetMapping("/product/{id}")
    public ResponseEntity getProduct(@PathVariable("id") int id) {
        Product currentProduct = productService.getSingleProduct(id);
        ResponseEntity<Product> res = new ResponseEntity<>(currentProduct, HttpStatus.OK);
        return res;
        //return ResponseEntity.ok(ProductService.getSingleProduct(id));
    }
    @PostMapping("/product")
    public ResponseEntity addProduct(@RequestBody ProductRequestDto product) {
        Product newProd = new Product();
        newProd.setTitle(product.getTitle());
        newProd.setPrice(product.getPrice());
        newProd.setDescription(product.getDescription());
        ProductCategory category = new ProductCategory();
        category.setCategoryName(product.getCategory());
        newProd.setCategory(category);
        newProd.setPrice(product.getPrice());
        newProd.setImageUrl(product.getImageUrl());
        Boolean b = productService.addProduct(newProd);
        return new ResponseEntity<>(b, HttpStatus.OK);
    }
    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ErrorDto> handleProductNotFoundException(Exception e) {
        ErrorDto errorDto = new ErrorDto();
        errorDto.setMessage(e.getMessage());

        return new ResponseEntity<>(errorDto, HttpStatus.NOT_FOUND);
    }
}

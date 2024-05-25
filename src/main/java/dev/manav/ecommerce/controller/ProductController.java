package dev.manav.ecommerce.controller;

import dev.manav.ecommerce.model.Product;
import dev.manav.ecommerce.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

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
}

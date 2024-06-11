package dev.manav.ecommerce.service;

import dev.manav.ecommerce.model.Product;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductService {

    public Product getSingleProduct(int id);
    public List<Product> getAllProducts();
    public Product addProduct(Product product);
    public Product updateProduct(Product product);
    public Product deleteProduct(int id);
    public List<Product> getProductsByCategory(String category);
}

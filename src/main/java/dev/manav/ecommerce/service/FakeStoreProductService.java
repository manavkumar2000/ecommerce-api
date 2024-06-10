package dev.manav.ecommerce.service;

import dev.manav.ecommerce.dto.FakeStoreProductDto;
import dev.manav.ecommerce.exceptions.ProductNotFoundException;
import dev.manav.ecommerce.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service("FakeStoreProductService")
public class FakeStoreProductService implements ProductService {
    @Autowired
    private RestTemplate restTemplate;
    @Override
    public Product getSingleProduct(int id) {
        FakeStoreProductDto fakeStoreProductDto = restTemplate.getForObject
                ("https://fakestoreapi.com/products/" + id,
                        FakeStoreProductDto.class);

        if(fakeStoreProductDto == null) {
            throw new ProductNotFoundException("Product not found " +
                    "with id "+ id);
        }
        return fakeStoreProductDto.toProduct();

    }

    @Override
    public List<Product> getAllProducts() {
        return List.of();
    }

    @Override
    public boolean addProduct(Product product) {

        return false;
    }

    @Override
    public boolean updateProduct(Product product) {
        return false;
    }

    @Override
    public boolean deleteProduct(int id) {
        return false;
    }

    @Override
    public List<Product> getProductsByCategory(String category) {
        return List.of();
    }
}

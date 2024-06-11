package dev.manav.ecommerce.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import dev.manav.ecommerce.dto.FakeStoreProductDto;
import dev.manav.ecommerce.exceptions.ProductNotAdded;
import dev.manav.ecommerce.exceptions.ProductNotFoundException;
import dev.manav.ecommerce.exceptions.ProductNotUpdated;
import dev.manav.ecommerce.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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
        ObjectMapper mapper = new ObjectMapper();
        FakeStoreProductDto[] listOfFakeStoreProduct = restTemplate.getForObject
                ("https://fakestoreapi.com/products/",
                        FakeStoreProductDto[].class);
        if(listOfFakeStoreProduct == null) {
            throw new ProductNotFoundException("Products not found");
        }

        List<Product> res = new ArrayList();
        for(FakeStoreProductDto fakeStoreProductDto : listOfFakeStoreProduct) {
            res.add(fakeStoreProductDto.toProduct());
        }
        return res;
    }

    @Override
    public Product addProduct(Product product) {
        FakeStoreProductDto fakeStoreProductDtoRequest = FakeStoreProductDto.toFakeStoreProductDto(product);
        FakeStoreProductDto fakeStoreProductDto = restTemplate.postForObject
                ("https://fakestoreapi.com/products/", fakeStoreProductDtoRequest,
                        FakeStoreProductDto.class);

        if(fakeStoreProductDto == null) {
            throw new ProductNotAdded("Product was not added");
        }
        return fakeStoreProductDto.toProduct();
    }

    @Override
    public Product updateProduct(Product product) {
        FakeStoreProductDto fakeStoreProductDtoRequest = FakeStoreProductDto.toFakeStoreProductDto(product);
        FakeStoreProductDto fakeStoreProductDto = restTemplate.patchForObject
                ("https://fakestoreapi.com/products/"+ product.getId(), fakeStoreProductDtoRequest,
                        FakeStoreProductDto.class);

        if(fakeStoreProductDto == null) {
            throw new ProductNotUpdated("Product was not updated");
        }
        return fakeStoreProductDto.toProduct();
    }

    @Override
    public Product deleteProduct(int id) {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<Product> entity = new HttpEntity<Product>(headers);

        FakeStoreProductDto fakeStoreProductDto = restTemplate.exchange("https://fakestoreapi.com/products/"+id,
                HttpMethod.DELETE, entity,FakeStoreProductDto.class).getBody();

        if(fakeStoreProductDto == null) {
            throw new ProductNotFoundException("Product not found " +
                    "with id "+ id);
        }
        return fakeStoreProductDto.toProduct();
        //return null;
    }

    @Override
    public List<Product> getProductsByCategory(String category) {
        FakeStoreProductDto[] listOfFakeStoreProduct = restTemplate.getForObject
                ("https://fakestoreapi.com/products/category/" + category,
                        FakeStoreProductDto[].class);
        if(listOfFakeStoreProduct == null) {
            throw new ProductNotFoundException("Products not found");
        }
        List<Product> res = new ArrayList();
        for(FakeStoreProductDto fakeStoreProductDto : listOfFakeStoreProduct) {
            res.add(fakeStoreProductDto.toProduct());
        }
        return res;

    }
}

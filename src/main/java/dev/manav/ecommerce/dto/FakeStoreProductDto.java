package dev.manav.ecommerce.dto;

import dev.manav.ecommerce.model.Product;
import dev.manav.ecommerce.model.ProductCategory;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FakeStoreProductDto {
    private int id;
    private String title;
    private String description;
    private double price;
    private String image;
    private String category;

    public Product toProduct()
    {
        Product product = new Product();
        product.setId(this.id);
        product.setTitle(title);
        product.setDescription(description);
        product.setPrice(price);
        product.setImageUrl(image);
        ProductCategory productCategory = new ProductCategory();
        productCategory.setCategoryName(this.category);
        product.setCategory(productCategory);
        return product;
    }

    public static FakeStoreProductDto toFakeStoreProductDto( Product product)
    {
        FakeStoreProductDto fakeStoreProductDto = new FakeStoreProductDto();
        fakeStoreProductDto.setId(product.getId());
        fakeStoreProductDto.setTitle(product.getTitle());
        fakeStoreProductDto.setDescription(product.getDescription());
        fakeStoreProductDto.setPrice(product.getPrice());
        fakeStoreProductDto.setImage(product.getImageUrl());
        fakeStoreProductDto.setCategory(product.getCategory().getCategoryName());
        return fakeStoreProductDto;
    }
}

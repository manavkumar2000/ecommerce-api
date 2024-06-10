package dev.manav.ecommerce.dto;

import dev.manav.ecommerce.model.ProductCategory;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductResponseDto {
    private int id;
    private String title;
    private String description;
    private double price;
    private String imageUrl;
    private ProductCategory category;
}

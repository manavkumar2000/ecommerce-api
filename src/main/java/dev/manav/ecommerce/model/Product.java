package dev.manav.ecommerce.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Product extends BaseModel {
    private String title;
    private String description;
    private double price;
    private String imageUrl;
    private ProductCategory category;
}

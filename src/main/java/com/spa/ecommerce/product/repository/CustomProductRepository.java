package com.spa.ecommerce.product.repository;

import com.spa.ecommerce.product.dto.ProductSearchRequest;
import com.spa.ecommerce.product.entity.Product;
import io.micrometer.common.lang.Nullable;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
@RequiredArgsConstructor
public class CustomProductRepository {
    private final ProductRepository productRepository;

    public Page<Product> searchProduct(ProductSearchRequest searchRequest, Pageable pageable) {
        Specification<Product> specs = Specification
                .where(categoryEqual(searchRequest.getCategory()))
                .and(priceGreaterThanEqual(searchRequest.getMinPrice()))
                .and(priceLessThanEqual(searchRequest.getMaxPrice()))
                .and(brandEqual(searchRequest.getBrand()))
                .and(latestArrivals(searchRequest.getNewArrival()))
                .and(colorEqual(searchRequest.getColor()))
                .and(materialEqual(searchRequest.getMaterial()));

        return productRepository.findAll(specs, pageable);
    }

    static Specification<Product> categoryEqual(String category) {
        return (root, query, criteriaBuilder) -> {
            if (category == null) {
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.equal(root.get("category"), category);
        };
    }

    static Specification<Product> priceGreaterThanEqual(Double minPrice) {
        return (root, query, criteriaBuilder) -> {
            if (minPrice == null) {
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.greaterThanOrEqualTo(root.get("price"), minPrice);
        };
    }

    static Specification<Product> priceLessThanEqual(Double maxPrice) {
        return (root, query, criteriaBuilder) -> {
            if (maxPrice == null) {
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.lessThanOrEqualTo(root.get("price"), maxPrice);
        };
    }

    static Specification<Product> brandEqual(String brand) {
        return (root, query, criteriaBuilder) -> {
            if (brand == null) {
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.equal(root.get("brand"), brand);
        };
    }

    static Specification<Product> latestArrivals(Boolean newArrival) {
        return (root, query, criteriaBuilder) -> {
            if (newArrival == null) {
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.greaterThan(root.get("postedDate"), LocalDate.now().minusWeeks(1));
        };
    }

    static Specification<Product> colorEqual(String color) {
        return (root, query, criteriaBuilder) -> {
            if (color == null) {
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.equal(root.get("color"), color);
        };
    }

    static Specification<Product> materialEqual(String material) {
        return (root, query, criteriaBuilder) -> {
            if (material == null) {
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.equal(root.get("material"), material);
        };
    }
}
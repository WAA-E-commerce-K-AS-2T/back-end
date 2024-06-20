package com.spa.ecommerce.product.dto;

import com.spa.ecommerce.common.ProductStatusEnum;
import lombok.Data;

@Data
public class ProductStatusUpdateDTO {
    private ProductStatusEnum status;
}

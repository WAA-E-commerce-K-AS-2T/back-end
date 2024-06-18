package com.spa.ecommerce.common;

public enum ProductSizeEnum {
    XS("Extra Small"),
    S("Small"),
    M("Medium"),
    L("Large"),
    XL("Extra Large"),
    XXL("Extra Extra Large");

    private String productSize;

    ProductSizeEnum(String productSize){
        this.productSize = productSize;
    }

    public String getProductSize(){
        return productSize;
    }
}

package com.spa.ecommerce.common;

public enum ProductStatusEnum {
    APPROVED("Approved"),
    IN_REVIEW("In Review"),
    REJECTED("Rejected"),
    DELETE("Delete");

    private String status;
    private ProductStatusEnum(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}

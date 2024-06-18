package com.spa.ecommerce.security;

public enum AuthType {
    ADMIN,
    SELLER,
    BUYER;

    @Override
    public String toString() {
        // This will return the enum constant name as a String
        return name();
    }
}

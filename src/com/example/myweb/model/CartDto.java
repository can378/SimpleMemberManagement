package com.example.myweb.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class CartDto {
    private Long cartId;      // 장바구니 ID
    private Long cartCount;   // 장바구니에 담긴 상품 수량
    private Long custId;      // 고객 ID
    private Long prodId;      // 상품 ID
    private ProductDto product;  // 추가된 필드: 상품 정보

    // getters and setters for product
    public ProductDto getProduct() {
        return product;
    }

    public void setProduct(ProductDto product) {
        this.product = product;
    }

}


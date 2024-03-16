package org.scaler.ecomorderservice.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BuyProductDto {

    private Long productId;

    private Integer quantity;

    public BuyProductDto(Long productId, Integer quantity) {
        this.productId = productId;
        this.quantity = quantity;
    }
}

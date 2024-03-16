package org.scaler.ecomorderservice.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CreateOrderDto {

    private Long productId;

    private Double amount;

    private Integer quantity;

    private Double discount;
}

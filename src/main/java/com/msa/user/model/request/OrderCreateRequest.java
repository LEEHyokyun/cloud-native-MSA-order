package com.msa.user.model.request;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class OrderCreateRequest {
    private Long productId;
    private Long userId;
    private Long orderQty;
    private Long orderUnitPrice;
    private Long orderTotalPrice;
}

package com.example.orderservice.dto.order;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class PurchaseOrderResponseDto {
	private long orderId;
	private long userId;
	private String productId;
	private int amount;
	private OrderStatus status;
}

package com.example.orderservice.dto.order;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@ToString
public class PurchaseOrderRequestDto {
	private long userId;
	private String productId;
}

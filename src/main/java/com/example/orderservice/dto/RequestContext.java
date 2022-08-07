package com.example.orderservice.dto;

import com.example.orderservice.dto.order.PurchaseOrderRequestDto;
import com.example.orderservice.dto.product.ProductDto;
import com.example.orderservice.dto.users.UserTransactionRequestDto;
import com.example.orderservice.dto.users.UserTransactionResponseDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class RequestContext {
	private PurchaseOrderRequestDto orderRequestDto;
	private ProductDto productDto;
	private UserTransactionRequestDto transactionRequestDto;
	private UserTransactionResponseDto transactionResponseDto;

	public RequestContext(PurchaseOrderRequestDto orderRequestDto) {
		this.orderRequestDto = orderRequestDto;
	}
}

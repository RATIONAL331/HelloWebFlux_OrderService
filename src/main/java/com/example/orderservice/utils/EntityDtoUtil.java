package com.example.orderservice.utils;

import com.example.orderservice.dto.RequestContext;
import com.example.orderservice.dto.order.OrderStatus;
import com.example.orderservice.dto.order.PurchaseOrderResponseDto;
import com.example.orderservice.dto.users.UserTransactionRequestDto;
import com.example.orderservice.dto.users.UserTransactionStatus;
import com.example.orderservice.entity.PurchaseOrder;
import org.springframework.beans.BeanUtils;

public class EntityDtoUtil {
	public static PurchaseOrderResponseDto getPurchaseOrderResponseDto(PurchaseOrder purchaseOrder) {
		PurchaseOrderResponseDto purchaseOrderResponseDto = new PurchaseOrderResponseDto();
		BeanUtils.copyProperties(purchaseOrder, purchaseOrderResponseDto);
		purchaseOrderResponseDto.setOrderId(purchaseOrder.getId()); // manually
		return purchaseOrderResponseDto;
	}

	public static void setTransactionRequestDto(RequestContext requestContext) {
		UserTransactionRequestDto userTransactionRequestDto = new UserTransactionRequestDto();
		userTransactionRequestDto.setUserId(requestContext.getOrderRequestDto().getUserId());
		userTransactionRequestDto.setAmount(requestContext.getProductDto().getPrice());
		requestContext.setTransactionRequestDto(userTransactionRequestDto);
	}

	public static PurchaseOrder getPurchaseOrder(RequestContext requestContext) {
		PurchaseOrder purchaseOrder = new PurchaseOrder();
		purchaseOrder.setUserId(requestContext.getOrderRequestDto().getUserId());
		purchaseOrder.setProductId(requestContext.getProductDto().getId());
		purchaseOrder.setAmount(requestContext.getProductDto().getPrice());

		UserTransactionStatus status = requestContext.getTransactionResponseDto().getStatus();
		purchaseOrder.setStatus(UserTransactionStatus.APPROVED.equals(status) ? OrderStatus.COMPLETED : OrderStatus.FAILED);

		return purchaseOrder;
	}
}

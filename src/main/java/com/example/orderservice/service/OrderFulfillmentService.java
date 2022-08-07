package com.example.orderservice.service;

import com.example.orderservice.client.ProductClient;
import com.example.orderservice.client.UserClient;
import com.example.orderservice.dto.RequestContext;
import com.example.orderservice.dto.order.PurchaseOrderRequestDto;
import com.example.orderservice.dto.order.PurchaseOrderResponseDto;
import com.example.orderservice.repository.PurchaseOrderRepository;
import com.example.orderservice.utils.EntityDtoUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

@Service
@RequiredArgsConstructor
public class OrderFulfillmentService {
	private final ProductClient productClient;
	private final UserClient userClient;
	private final PurchaseOrderRepository repository;

	public Mono<PurchaseOrderResponseDto> processOrder(Mono<PurchaseOrderRequestDto> requestDtoMono) {
		return requestDtoMono.map(RequestContext::new)
		                     .flatMap(this::productRequestResponse)
		                     .doOnNext(EntityDtoUtil::setTransactionRequestDto)
		                     .flatMap(this::userRequestResponse)
		                     .map(EntityDtoUtil::getPurchaseOrder)
		                     .map(repository::save) // blocking call
		                     .map(EntityDtoUtil::getPurchaseOrderResponseDto)
		                     .subscribeOn(Schedulers.boundedElastic()); // very important!!!
	}

	private Mono<RequestContext> productRequestResponse(RequestContext requestContext) {
		return productClient.getProductById(requestContext.getOrderRequestDto().getProductId())
		                    .doOnNext(requestContext::setProductDto)
		                    .thenReturn(requestContext);
	}

	private Mono<RequestContext> userRequestResponse(RequestContext requestContext) {
		return userClient.authorizeTransaction(requestContext.getTransactionRequestDto())
		                 .doOnNext(requestContext::setTransactionResponseDto)
		                 .thenReturn(requestContext);
	}
}

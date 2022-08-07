package com.example.orderservice.controller;

import com.example.orderservice.dto.order.PurchaseOrderRequestDto;
import com.example.orderservice.dto.order.PurchaseOrderResponseDto;
import com.example.orderservice.service.OrderFulfillmentService;
import com.example.orderservice.service.OrderQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClientRequestException;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("order")
@RequiredArgsConstructor
public class PurchaseOrderController {
	private final OrderFulfillmentService orderFulfillmentService;
	private final OrderQueryService orderQueryService;

	@PostMapping
	public Mono<ResponseEntity<PurchaseOrderResponseDto>> order(@RequestBody Mono<PurchaseOrderRequestDto> requestDtoMono) {
		return orderFulfillmentService.processOrder(requestDtoMono)
		                              .map(ResponseEntity::ok)
		                              .onErrorReturn(WebClientResponseException.class, ResponseEntity.badRequest().build())
		                              .onErrorReturn(WebClientRequestException.class, ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).build());
	}

	@GetMapping("user/{userId}")
	public Flux<PurchaseOrderResponseDto> getOrderByUserId(@PathVariable int userId) {
		return orderQueryService.getProductsByUserId(userId);
	}
}

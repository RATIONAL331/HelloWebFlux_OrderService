package com.example.orderservice.controller;

import com.example.orderservice.dto.order.PurchaseOrderRequestDto;
import com.example.orderservice.dto.order.PurchaseOrderResponseDto;
import com.example.orderservice.service.OrderFulfillmentService;
import com.example.orderservice.service.OrderQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("order")
@RequiredArgsConstructor
public class PurchaseOrderController {
	private final OrderFulfillmentService orderFulfillmentService;
	private final OrderQueryService orderQueryService;

	@PostMapping
	public Mono<PurchaseOrderResponseDto> order(@RequestBody Mono<PurchaseOrderRequestDto> requestDtoMono) {
		return orderFulfillmentService.processOrder(requestDtoMono);
	}

	@GetMapping("user/{userId}")
	public Flux<PurchaseOrderResponseDto> getOrderByUserId(@PathVariable int userId) {
		return orderQueryService.getProductsByUserId(userId);
	}
}

package com.example.orderservice;

import com.example.orderservice.client.ProductClient;
import com.example.orderservice.client.UserClient;
import com.example.orderservice.dto.order.PurchaseOrderRequestDto;
import com.example.orderservice.dto.product.ProductDto;
import com.example.orderservice.dto.users.UserDto;
import com.example.orderservice.service.OrderFulfillmentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@SpringBootTest
class OrderServiceApplicationTests {
	@Autowired
	private UserClient userClient;
	@Autowired
	private ProductClient productClient;
	@Autowired
	private OrderFulfillmentService orderFulfillmentService;

	@Test
	void contextLoads() {
		Flux.zip(userClient.getAllUsers(), productClient.getAllProducts())
		    .map(tuple -> this.buildDto(tuple.getT1(), tuple.getT2()))
		    .flatMap(dto -> this.orderFulfillmentService.processOrder(Mono.just(dto)))
		    .doOnNext(System.out::println)
		    .as(StepVerifier::create)
		    .expectNextCount(0)
		    .verifyComplete();
	}

	private PurchaseOrderRequestDto buildDto(UserDto userDto, ProductDto productDto) {
		return PurchaseOrderRequestDto.builder()
		                              .userId(userDto.getId())
		                              .productId(productDto.getId())
		                              .build();
	}

}

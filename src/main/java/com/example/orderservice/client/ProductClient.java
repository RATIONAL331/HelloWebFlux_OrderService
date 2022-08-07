package com.example.orderservice.client;

import com.example.orderservice.dto.product.ProductDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class ProductClient {
	private final WebClient webClient;

	public ProductClient(@Value("${product.service.url}") String productServiceUrl) {
		this.webClient = WebClient.builder()
		                          .baseUrl(productServiceUrl)
		                          .build();
	}

	public Mono<ProductDto> getProductById(String productId) {
		return webClient.get()
		                .uri("{id}", productId)
		                .retrieve()
		                .bodyToMono(ProductDto.class);
	}
}

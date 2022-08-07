package com.example.orderservice.client;

import com.example.orderservice.dto.users.UserTransactionRequestDto;
import com.example.orderservice.dto.users.UserTransactionResponseDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class UserClient {
	private final WebClient webClient;

	public UserClient(@Value("${user.service.url}") String userServiceUrl) {
		this.webClient = WebClient.builder()
		                          .baseUrl(userServiceUrl)
		                          .build();
	}

	public Mono<UserTransactionResponseDto> authorizeTransaction(UserTransactionRequestDto requestDto) {
		return webClient.post()
		                .uri("transaction")
		                .bodyValue(requestDto)
		                .retrieve()
		                .bodyToMono(UserTransactionResponseDto.class);
	}
}

package com.example.orderservice.dto.users;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UserTransactionResponseDto {
	private long userId;
	private int amount;
	private UserTransactionStatus status;

}

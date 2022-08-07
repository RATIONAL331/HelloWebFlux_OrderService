package com.example.orderservice.dto.users;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UserDto {
	private long id;
	private String name;
	private int balance;
}

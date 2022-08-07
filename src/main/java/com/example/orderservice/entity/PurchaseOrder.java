package com.example.orderservice.entity;

import com.example.orderservice.dto.order.OrderStatus;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@ToString
public class PurchaseOrder {
	@Id
	@GeneratedValue
	private long id;
	private String productId;
	private long userId;
	private int amount;
	private OrderStatus status;
}

package com.example.orderservice.service;

import com.example.orderservice.dto.order.PurchaseOrderResponseDto;
import com.example.orderservice.repository.PurchaseOrderRepository;
import com.example.orderservice.utils.EntityDtoUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

@Service
@RequiredArgsConstructor
public class OrderQueryService {
	private final PurchaseOrderRepository purchaseOrderRepository;

	public Flux<PurchaseOrderResponseDto> getProductsByUserId(int userId) {
		return Flux.fromStream(() -> purchaseOrderRepository.findAllByUserId(userId).stream()) // blocking
		           .map(EntityDtoUtil::getPurchaseOrderResponseDto)
		           .subscribeOn(Schedulers.boundedElastic());
	}
}

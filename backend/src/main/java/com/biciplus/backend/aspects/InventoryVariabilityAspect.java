package com.biciplus.backend.aspects;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import com.biciplus.backend.model.Inventory;
import com.biciplus.backend.model.Product;

@Aspect
@Component
public class InventoryVariabilityAspect extends ComposedProductVariabilityHelper {

	@Around("execution(* com.biciplus.backend.repositories.InventoryRepository.save(..))")
	public Inventory save(ProceedingJoinPoint joinPoint) throws Throwable {
		for (Product product: ((Inventory) joinPoint.getArgs()[0]).getProducts()) {
			checkIfComposedProductIsEnabled(product);
		}
		
		return (Inventory) joinPoint.proceed();
	}
	
	@SuppressWarnings("unchecked")
	@Around("execution(* com.biciplus.backend.repositories.InventoryRepository.findAll(..))")
	public List<Inventory> findAll(ProceedingJoinPoint joinPoint) throws Throwable {
		List<Inventory> inventories = (List<Inventory>) joinPoint.proceed();
		if(!configuration.featureIsActive("ComposedECommerce")) {
			for (Inventory inventory : inventories) {
				inventory.setProducts(new ArrayList<>(inventory.getProducts()).stream().filter(x -> !x.getType().equals("composed")).collect(Collectors.toSet()));
			}
		}
		return inventories;
	}

	@Around("execution(* com.biciplus.backend.repositories.InventoryRepository.findEntityById(..))")
	public Inventory findById(ProceedingJoinPoint joinPoint) throws Throwable {
		Inventory inventory = (Inventory) joinPoint.proceed();
		if(!configuration.featureIsActive("ComposedECommerce")) {
			inventory.setProducts(new ArrayList<>(inventory.getProducts()).stream().filter(x -> !x.getType().equals("composed")).collect(Collectors.toSet()));
		}
		return inventory;
	}
}

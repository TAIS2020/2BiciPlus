package com.biciplus.backend.aspects;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import com.biciplus.backend.model.Product;

@Aspect
@Component
public class ProductVariabilityAspect<T extends Product> extends ComposedProductVariabilityHelper{
	
	@SuppressWarnings("unchecked")
	@Around("execution(* com.biciplus.backend.repositories.ProductRepository.save(..))")
	public T save(ProceedingJoinPoint joinPoint) throws Throwable {
		checkIfComposedProductIsEnabled(((Product) joinPoint.getArgs()[0]));
		return (T) joinPoint.proceed();
	}
	
	@SuppressWarnings("unchecked")
	@Around("execution(* com.biciplus.backend.repositories.ProductRepository.findAll(..))")
	public List<T> findAll(ProceedingJoinPoint joinPoint) throws Throwable {
		List<T> products = (List<T>) joinPoint.proceed();
		if(!configuration.featureIsActive("ComposedECommerce")) {
			products = new ArrayList<>(products);
			products = products.stream().filter(x -> !x.getType().equals("composed")).collect(Collectors.toList());
		}
		return products;
	}

	@SuppressWarnings("unchecked")
	@Around("execution(* com.biciplus.backend.repositories.ProductRepository.findEntityById(..))")
	public T findById(ProceedingJoinPoint joinPoint) throws Throwable {
		T product = (T) joinPoint.proceed();
		checkIfComposedProductIsEnabled(product);
		return product;
	}
}

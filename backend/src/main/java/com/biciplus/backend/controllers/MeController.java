package com.biciplus.backend.controllers;

import java.util.Date;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.biciplus.backend.CustomExceptionHandler;
import com.biciplus.backend.components.SplConfigurationComponent;
import com.biciplus.backend.controllers.util.Response;
import com.biciplus.backend.exceptions.FeatureDisabledException;
import com.biciplus.backend.model.Customer;
import com.biciplus.backend.model.OrderHistory;
import com.biciplus.backend.model.Person;
import com.biciplus.backend.model.Product;
import com.biciplus.backend.model.Question;
import com.biciplus.backend.model.Seller;
import com.biciplus.backend.model.ShoppingCart;
import com.biciplus.backend.model.ShoppingCartEntry;
import com.biciplus.backend.repositories.OrderHistoryRepository;
import com.biciplus.backend.repositories.PersonRepository;
import com.biciplus.backend.repositories.QuestionRepository;
import com.biciplus.backend.repositories.ShoppingCartEntryRepository;
import com.biciplus.backend.repositories.ShoppingCartRepository;
import com.biciplus.backend.services.AuthenticationService;

@RestController
@RequestMapping("api/me")
public class MeController<T extends Person, Y extends Product> extends CustomExceptionHandler {
	
	@Autowired
	PersonRepository<T> personRepository;
	@Autowired
	ShoppingCartRepository shoppingCartRepository;
	@Autowired
	ShoppingCartEntryRepository shoppingCartEntryRepository;
	@Autowired
	OrderHistoryRepository orderHistoryRepository;
	@Autowired
	QuestionRepository questionRepository;
	@Autowired
	AuthenticationService<T> authenticationService;
	@Autowired 
	SplConfigurationComponent configuration;

	@RequestMapping(method = RequestMethod.GET)
	public Response getMe(@RequestHeader (name="Authorization") String token) throws Exception {
		return new Response(Response.Status.OK, getPersonFromToken(token));
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/shoppingCart", method = RequestMethod.GET)
	public Response getShoppingCart(@RequestHeader (name="Authorization") String token) throws Exception {
		Customer customer = getCustomerFromToken(token);
		if(customer.getShoppingCart() == null) {
			ShoppingCart auxiliarShoppingCart = new ShoppingCart();
			auxiliarShoppingCart.setProducts(new HashSet<ShoppingCartEntry>());
			customer.setShoppingCart(shoppingCartRepository.save(auxiliarShoppingCart));
			personRepository.save((T) customer);
		}
		return new Response(Response.Status.OK, customer.getShoppingCart());
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/shoppingCart", method = RequestMethod.DELETE)
	public Response deleteShoppingCart(@RequestHeader (name="Authorization") String token) throws Exception {
		Customer customer = getCustomerFromToken(token);
		if(customer.getShoppingCart() != null) {
			ShoppingCart auxiliarShoppingCart = customer.getShoppingCart();
			customer.setShoppingCart(null);
			personRepository.save((T) customer);
			shoppingCartRepository.delete(auxiliarShoppingCart);
		}
		return new Response(Response.Status.OK);
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/shoppingCart/product", method = RequestMethod.POST)
	public Response postProduct(@RequestHeader (name="Authorization") String token, @RequestBody Y product) throws Exception {
		Customer customer = getCustomerFromToken(token);
		if(customer.getShoppingCart() == null) {
			ShoppingCart auxiliarShoppingCart = new ShoppingCart();
			auxiliarShoppingCart.setProducts(new HashSet<ShoppingCartEntry>());
			customer.setShoppingCart(shoppingCartRepository.save(auxiliarShoppingCart));
			personRepository.save((T) customer);
		}
		Set<ShoppingCartEntry> products = customer.getShoppingCart().getProducts();

		boolean alreadyAdded = false;
		
		for(ShoppingCartEntry shoppingCartEntry : products) {
	        if(shoppingCartEntry.getProduct().getId().equals(product.getId())) {
	        	products.remove(shoppingCartEntry);
	        	shoppingCartEntry.setQuantity(shoppingCartEntry.getQuantity() + 1);
	        	products.add(shoppingCartEntry);
	        	alreadyAdded = true;
	            break;
	        }
		}
		
		if(!alreadyAdded) {
			ShoppingCartEntry shoppingCartEntry = new ShoppingCartEntry();
			shoppingCartEntry.setQuantity(new Long(1));
			shoppingCartEntry.setProduct(product);
        	products.add(shoppingCartEntry);
		}
		
		customer.getShoppingCart().setProducts(products);
		personRepository.save((T) customer);
		return new Response(Response.Status.OK, customer.getShoppingCart());
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/shoppingCart/product", method = RequestMethod.DELETE)
	public Response deleteProduct(@RequestHeader (name="Authorization") String token, @RequestBody Y product) throws Exception {
		Customer customer = getCustomerFromToken(token);
		if(customer.getShoppingCart() == null) {
			ShoppingCart auxiliarShoppingCart = new ShoppingCart();
			auxiliarShoppingCart.setProducts(new HashSet<ShoppingCartEntry>());
			customer.setShoppingCart(shoppingCartRepository.save(auxiliarShoppingCart));
			personRepository.save((T) customer);
		}
		Set<ShoppingCartEntry> products = customer.getShoppingCart().getProducts();

		
		for(ShoppingCartEntry shoppingCartEntry : products) {
	        if(shoppingCartEntry.getProduct().getId().equals(product.getId())) {
	        	products.remove(shoppingCartEntry);
	        	if(shoppingCartEntry.getQuantity() > 1) {
		        	shoppingCartEntry.setQuantity(shoppingCartEntry.getQuantity() - 1);
		        	products.add(shoppingCartEntry);
	        	} else {
	        		shoppingCartEntryRepository.delete(shoppingCartEntry);
	        	}
	            break;
	        }
		}
		
		customer.getShoppingCart().setProducts(products);
		personRepository.save((T) customer);
		return new Response(Response.Status.OK, customer.getShoppingCart());
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/shoppingCart/order", method = RequestMethod.POST)
	public Response postOrder(@RequestHeader (name="Authorization") String token) throws Exception {
		Customer customer = getCustomerFromToken(token);
		if(customer.getShoppingCart() == null || customer.getShoppingCart().getProducts().size() == 0) throw new Exception("There are no products on the shopping cart");
				
		OrderHistory order = new OrderHistory();
		order.setPaymentDate(new Date());
		order.setShoppingCart(customer.getShoppingCart());
		order = orderHistoryRepository.save(order);

		Set<OrderHistory> orderHistory = customer.getOrderHistory();
		orderHistory.add(order);
		customer.setOrderHistory(orderHistory);
		customer.setShoppingCart(shoppingCartRepository.save(new ShoppingCart()));	

		personRepository.save((T) customer);
		
		return new Response(Response.Status.OK, order);
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/question", method = RequestMethod.POST)
	public Response postQuestion(@RequestHeader (name="Authorization") String token, @RequestBody Question question) throws Exception {
		if(!configuration.featureIsActive("MessageBasedCustomerSupport")) throw new FeatureDisabledException("Message based customer support is not enabled");
		if(question.getQuestion() == null || question.getQuestion().isEmpty()) throw new Exception("Invalid question");
		Customer customer = getCustomerFromToken(token);
		question.setId(null);
		question.setAnswerer(null);
		question.setAnswerer(null);
		HashSet<Question> questions = new HashSet<Question>(customer.getQuestions());
		questions.add(questionRepository.save(question));
		customer.setQuestions(questions);
		personRepository.save((T) customer);
		return new Response(Response.Status.OK, question);
	}

	@RequestMapping(value = "/questions/{id}/answer", method = RequestMethod.POST)
	public Response postQuestionAnswer(@RequestHeader (name="Authorization") String token, @PathVariable("id") Long id, @RequestBody Question question) throws Exception {
		if(!configuration.featureIsActive("MessageBasedCustomerSupport")) throw new FeatureDisabledException("Message based customer support is not enabled");
		if(question.getAnswer() == null || question.getAnswer().isEmpty()) throw new Exception("Invalid answer");
		String answer = question.getAnswer();
		question = questionRepository.findEntityById(id);
		if(question == null) throw new Exception("Question does not exist");
		if(question.getAnswer() != null) throw new Exception("Question already answered");
		
		question.setAnswer(answer);
		question.setAnswerer(getSellerFromToken(token));
		return new Response(Response.Status.OK, questionRepository.save(question));
	}

	@SuppressWarnings("unused")
	private Seller getSellerFromToken(String token) throws Exception {
		Person person = getPersonFromToken(token);
		if(!(person instanceof Seller)) throw new Exception("Person is not a seller");
		return (Seller) person;
	}
	
	private Customer getCustomerFromToken(String token) throws Exception {
		Person person = getPersonFromToken(token);
		if(!(person instanceof Customer)) throw new Exception("Person is not a customer");
		return (Customer) person;
	}
	
	private Person getPersonFromToken(String token) throws Exception {
		token = StringUtils.removeStart(token != null ? token : "", "Bearer").trim();
		Optional<T> optionalPerson = personRepository.findByToken(token);
		if (!optionalPerson.isPresent()) throw new Exception("Unauthorized User");
		return optionalPerson.get();
	}
}

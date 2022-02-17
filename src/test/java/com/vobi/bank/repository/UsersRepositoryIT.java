package com.vobi.bank.repository;

import static org.junit.jupiter.api.Assertions.*;

//import java.util.List;
//import java.util.Optional;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.vobi.bank.domain.UserType;
import com.vobi.bank.domain.Users;

//import com.vobi.bank.domain.Customer;
//import com.vobi.bank.domain.DocumentType;

//import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
//@Slf4j
class UsersRepositoryIT {
	
	@Autowired
	UsersRepository usersRepository;
	
	@Autowired
	UserTypeRepository userTypeRepository;

	@Test
	@Order(1)
	void debeValidarLasDependencias() {
		assertNotNull(usersRepository);
		assertNotNull(userTypeRepository);
	}
	
	@Test
	@Order(2)
	void debeCrearUnUsuario() {
		//Arrange
		Integer idUserType=3;
		String idUser="cfrancobedoya@gmail.com";
		
		Users user=null;
		UserType userType=userTypeRepository.findById(idUserType).get();
		
		user=new Users();
		user.setUserEmail(idUser);
		user.setName("Cristian Franco");
		user.setToken(null);
		user.setEnable("Y");
		user.setUserType(userType);
		
		//Act
		
		user=usersRepository.save(user);
		
		//Assert
		
		assertNotNull(user,"El usuario es nulo no se pudo grabar");
	}
	
	@Test
	@Order(3)
	void debeModificarUnUsuario() {
		//Arrange
		String idUser="cfrancobedoya@gmail.com";
		Users user=null;
		
		user=usersRepository.findById(idUser).get();
		user.setEnable("N");
		
		//Act
		
		user=usersRepository.save(user);
		
		//Assert
		
		assertNotNull(user,"El user es nulo no se pudo modificar");
	}
	
//	@Test
//	@Order(4)
//	void debeBorrarUnCustomer() {
//		//Arrange
//		
//		Integer idCustomer=14836554;
//		Customer customer=null;
//		Optional<Customer> customerOptional=null;
//		
//		assertTrue(customerRepository.findById(idCustomer).isPresent(),"No encontro el customer");
//		
//		customer=customerRepository.findById(idCustomer).get();
//		
//		//Act
//		customerRepository.delete(customer);
//		customerOptional=customerRepository.findById(idCustomer);
//		
//		//Assert
//		
//		assertFalse(customerOptional.isPresent(),"No pudo borrar el customer");
//	}
	
//	@Test
//	@Order(5)
//	void debeConsultarTodosLosCustomers() {
//		//Arrange
//		List<Customer> customers=null;
//		
//		//Act
//		
//		customers=customerRepository.findAll();
//		
//		customers.forEach(customer->log.info(customer.getName()));		
//		
//		//Assert
//		
//		assertFalse(customers.isEmpty(),"No consulto Customers");	
//	}
	
	
	
}
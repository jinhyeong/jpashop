package jpabook.jpashop.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import javax.validation.Validator;
import java.util.Arrays;

@SpringBootTest
public class ValidatorTest {
//	@Autowired
//	private Validator validator;

	@Autowired
	private ApplicationContext applicationContext;

	@Test
	void name() {
		// given
//		Arrays.stream(applicationContext.getBeanDefinitionNames())
//				.forEach(System.out::println);

		Object mvcValidator = applicationContext.getBean("mvcValidator");
		System.out.println("mvcValidator = " + mvcValidator);

		// when

		// then
	}
}

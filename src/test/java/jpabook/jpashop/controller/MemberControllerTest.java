package jpabook.jpashop.controller;

import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import javax.validation.executable.ExecutableValidator;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class MemberControllerTest {
	@Test
	void name() {
		// given
		ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
		Validator validator = validatorFactory.getValidator();

		MemberForm memberForm = new MemberForm();
		// validation 및 출력
		Set<ConstraintViolation<MemberForm>> validate = validator.validate(memberForm);
		validate.forEach(System.out::println);


		// when

		// then
	}
}

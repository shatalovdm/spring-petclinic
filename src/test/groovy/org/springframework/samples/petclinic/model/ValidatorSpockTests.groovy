package org.springframework.samples.petclinic.model;

//import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;

import java.util.Locale;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import spock.lang.Specification;

/**
 * @author Chris Jones
 *         Simple tests adapted to use the Spock acceptance test framework.
 */
public class ValidatorSpockTests extends Specification {

    private Validator createValidator() {
        LocalValidatorFactoryBean localValidatorFactoryBean = new LocalValidatorFactoryBean();
        localValidatorFactoryBean.afterPropertiesSet();
        return localValidatorFactoryBean;
    }
	
	// 1. validate against blank first name
	def "first name cannot be empty"() {
	  setup:
	  def person = new Person();
	  def validator = createValidator();
	  
	  when:
	  person.setFirstName("")
	  person.setLastName("smith")
      Set<ConstraintViolation<Person>> constraintViolations = validator.validate(person);
	  
	  then:
      constraintViolations.size() == 1;
      ConstraintViolation<Person> violation = constraintViolations.iterator().next();
	  violation.getPropertyPath().toString().equals("firstName");
	  violation.getMessage().equals("may not be empty");
	}
	
	// 2. TODO: validate against null first name.
	def "first name cannot be null"() {
		setup:
		def person = new Person();
		def validator = createValidator();

		when:
		person.setFirstName(null)
	  	person.setLastName("smith")
      	Set<ConstraintViolation<Person>> constraintViolations = validator.validate(person);
		
		then:
		constraintViolations.size() == 1;
      	ConstraintViolation<Person> violation = constraintViolations.iterator().next();
	  	violation.getPropertyPath().toString().equals("firstName");
	  	violation.getMessage().equals("may not be empty");
	}
	
	// 3. TODO: validate a against a valid first name (non-empty, non-null value).
	def "first name is valid"() {
		setup:
		def person = new Person();
		def validator = createValidator();

		when:
		person.setFirstName("john")
	  	person.setLastName("smith")
		
		then:
		assertEquals( 0, validator.validate(person).size() );
	}
	// 4. TODO: validate against empty last name
	def "last name cannot be empty"() {
	  setup:
	  def person = new Person();
	  def validator = createValidator();
	  
	  when:
	  person.setFirstName("john")
	  person.setLastName("")
      Set<ConstraintViolation<Person>> constraintViolations = validator.validate(person);
	  
	  then:
      constraintViolations.size() == 1;
      ConstraintViolation<Person> violation = constraintViolations.iterator().next();
	  violation.getPropertyPath().toString().equals("lastName");
	  violation.getMessage().equals("may not be empty");
	}

	// 5. TODO: validate against null last name
	def "last name cannot be null"() {
		setup:
		def person = new Person();
		def validator = createValidator();

		when:
		person.setFirstName("john")
	  	person.setLastName(null)
      	Set<ConstraintViolation<Person>> constraintViolations = validator.validate(person);
		
		then:
		constraintViolations.size() == 1;
      	ConstraintViolation<Person> violation = constraintViolations.iterator().next();
	  	violation.getPropertyPath().toString().equals("lastName");
	  	violation.getMessage().equals("may not be empty");
	}
	
	// 6. TODO: validate a against a valid last name (non-empty, non-null value).
	def "last name is valid"() {
		setup:
		def person = new Person();
		def validator = createValidator();

		when:
		person.setFirstName("john")
	  	person.setLastName("smith")
		
		then:
		assertEquals( 0, validator.validate(person).size() );
	}
}

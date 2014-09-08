package com.aristideniyungeko;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * Handles contact data errors.
 */
@Component("contactFormValidator")
public class ContactFormValidator implements Validator {
   @Override
   public boolean supports(Class clazz) {
      return Contact.class.isAssignableFrom(clazz);
   }

   @Override
   public void validate(Object model, Errors errors) {
      ValidationUtils.rejectIfEmptyOrWhitespace(errors,
                                                "name",
                                                "required.name",
                                                "Name is required.");

   }

}

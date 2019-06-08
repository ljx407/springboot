package com.ljx.chapter10springmvc.validations;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.util.ArrayList;
import java.util.List;

@Component
public class MvcUploadValidation implements Validator {

    private List<String> types = new ArrayList<String>() {
        {
            this.add("multi");
            this.add("req");
            this.add("part");
        }
    };

    @Override
    public boolean supports(Class<?> clazz) {
        return String.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        String type = (String)target;
        ValidationUtils.rejectIfEmpty(errors,type,null,"type cannot empty");

        if(!types.contains(type)) {
            errors.rejectValue("type",null,"An illegal type value!");
        }
    }
}

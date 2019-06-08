package com.ljx.chapter10.validators;

import com.ljx.chapter10.model.Student;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class StrudentValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return Student.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        if(null == target) {
            ValidationUtils.rejectIfEmpty(errors, target.toString(),null,"Student不能为空");
        }
        Student student = (Student)target;
        ValidationUtils.rejectIfEmpty(errors,"userName",null,"username不能为空");
        ValidationUtils.rejectIfEmpty(errors,"password",null,"密码不能为空");
        if(!"123".equalsIgnoreCase(student.getPassword())) {
            errors.rejectValue("password",null,"密码不匹配");
        }
    }
}

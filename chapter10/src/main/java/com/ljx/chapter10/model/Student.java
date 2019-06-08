package com.ljx.chapter10.model;

import com.ljx.chapter10.enums.AgeEnum;
import com.ljx.chapter10.enums.SexEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.*;
import java.math.BigDecimal;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student {
    @NotNull(message = "id 不能为null")
    private Long id ;
    @Min(1)
    @Max(110)
    private Integer age ;
    private String email ;
    @Length(min = 1,max = 10,message = "username 错误")
    private String userName ;
    private String password ;
    private SexEnum sex ;
    private AgeEnum ageDisplay ;
    @DecimalMin("0.1")
    @DecimalMax("100.0")
    private BigDecimal money ;
}

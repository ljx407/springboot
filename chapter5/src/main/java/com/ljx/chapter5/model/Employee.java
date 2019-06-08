package com.ljx.chapter5.model;

import com.ljx.chapter5.converter.SexEnumConvert;
import com.ljx.chapter5.enums.SexEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "t_employee")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id ;

    @Column(name = "emp_name")
    private String empName;

    private String note ;

    @Convert(converter = SexEnumConvert.class)
    private SexEnum sex ;
}

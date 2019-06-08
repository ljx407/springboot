package com.ljx.chapter9react.entity;

import com.ljx.chapter9react.enums.SexEnum;
import com.ljx.chapter9react.typeconverts.SexEnumConvert;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.Alias;

import javax.persistence.*;

@Alias("user")
@Entity(name = "user")
@Table(name = "t_user")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;

    @Column(name = "user_name")
    private String userName ;

    @Convert(converter = SexEnumConvert.class)
    private SexEnum sex ;
    private String memo ;
}

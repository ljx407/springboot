package com.ljx.chapter10.model;


import com.ljx.chapter10.enums.SexEnum;
import com.ljx.chapter10.jpaconvert.SexEnumJapConvert;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity(name = "user")
@Table(name = "t_user")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;
    @Column(name = "user_name")
    private String userName;
    @Convert(converter = SexEnumJapConvert.class)
    private SexEnum sex ;
    private String memo ;
}

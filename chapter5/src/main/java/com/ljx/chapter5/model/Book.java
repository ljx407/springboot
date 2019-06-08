package com.ljx.chapter5.model;


import com.ljx.chapter5.enums.BookTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.Alias;

@Alias("book")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Book {
    private Integer id ;
    private String name ;
    private String memo ;
    private BookTypeEnum type ;
}

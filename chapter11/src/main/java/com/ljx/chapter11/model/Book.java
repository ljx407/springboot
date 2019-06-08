package com.ljx.chapter11.model;

import com.ljx.chapter11.enums.BookEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.Alias;

@Alias("Book")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Book {

    private Long id ;
    private BookEnum bookType;
    private String name;
}

package com.ljx.chapter15.dao;

import com.ljx.chapter15.model.Product;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ProductDao {

    Product findProductById(Long id);

    List<Product> findProductListByIds(@Param("ids") List<Long> ids);

}

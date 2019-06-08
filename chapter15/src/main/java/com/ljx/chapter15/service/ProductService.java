package com.ljx.chapter15.service;

import com.ljx.chapter15.model.Product;

import java.util.List;

public interface ProductService {

    Product findProductById(Long id);

    List<Product> findProductListByIds(List<Long> ids) ;
}

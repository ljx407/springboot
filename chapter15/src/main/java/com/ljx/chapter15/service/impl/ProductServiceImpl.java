package com.ljx.chapter15.service.impl;

import com.ljx.chapter15.dao.ProductDao;
import com.ljx.chapter15.model.Product;
import com.ljx.chapter15.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductDao productDao ;

    @Override
    @Cacheable(cacheNames = "chapter15" , key = "'product_' + #id")
    public Product findProductById(Long id) {
        return productDao.findProductById(id);
    }

    @Override
    public List<Product> findProductListByIds(List<Long> ids) {
        if(CollectionUtils.isEmpty(ids)) {
            return new ArrayList<>();
        }
        return productDao.findProductListByIds(ids);
    }
}

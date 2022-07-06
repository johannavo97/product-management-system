package com.johanna.productmanagementproject.services;

import com.johanna.productmanagementproject.data.ProductRepository;
import com.johanna.productmanagementproject.data.UserRepository;
import com.johanna.productmanagementproject.models.Product;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
@Transactional(rollbackOn = {DataAccessException.class})
public class ProductService {
    UserRepository userRepository;

    ProductRepository productRepository;

    @Autowired

    public ProductService(UserRepository userRepository, ProductRepository productRepository) {
        this.userRepository = userRepository;
        this.productRepository = productRepository;
    }

    @Transactional(rollbackOn = {NoSuchElementException.class})
    public Product findById(int id) throws NoSuchElementException{
        return productRepository.findById(id).orElseThrow();
    }

    public void saveOrUpdate(Product p){
        productRepository.save(p);
        log.info(String.format("Product ID Generated: %d Product Name: %s Category: %s", p.getProductId(), p.getProductName(),p.getCategory()));
    }

    public List<Product> getAllProducts(){
        return productRepository.findAll();
    }


    public Product updateProduct(Product product) {
        return productRepository.save(product);
    }


    public void deleteProductById(Integer id) {
        productRepository.deleteById(id);
    }

}

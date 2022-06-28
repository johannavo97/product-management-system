package com.student.productmanagementproject.services;

import com.student.productmanagementproject.data.ProductRepository;
import com.student.productmanagementproject.data.UserRepository;
import com.student.productmanagementproject.models.Product;
import com.student.productmanagementproject.models.User;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Transactional(rollbackOn = {DataAccessException.class})
public class UserService {

    UserRepository userRepository;

    ProductRepository productRepository;

    @Autowired
    public UserService(UserRepository userRepository, ProductRepository productRepository) {
        this.userRepository = userRepository;
        this.productRepository = productRepository;
    }

    public List<User> findAll(){
        return userRepository.findAll();
    }

    @Transactional(rollbackOn = {NoSuchElementException.class})
    public User findByEmail(String email) throws NoSuchElementException{
        return userRepository.findById(email).orElseThrow();
    }

    public void delete(User u){
        userRepository.delete(u);
    }

    @Transactional(rollbackOn = {NoSuchElementException.class})
    public void addProduct(String email, Product p) throws NoSuchElementException{

        User u = userRepository.findById(email).orElseThrow();
        p = productRepository.save(p);
        u.addProduct(p);
        userRepository.save(u);
    }

    public void saveOrUpdate(User user){
        log.info(user.toString());
        userRepository.save(user);

    }

    public List<User> findAllSortedBy(Sort sort){
        return userRepository.findAll(sort);
    }
}

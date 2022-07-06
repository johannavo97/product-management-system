package com.johanna.productmanagementproject;


import com.johanna.productmanagementproject.data.RoleRepository;
import com.johanna.productmanagementproject.models.*;
import com.johanna.productmanagementproject.data.ProductRepository;
import com.johanna.productmanagementproject.services.ProductService;
import com.johanna.productmanagementproject.services.UserService;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.NoSuchElementException;

@Component @Slf4j
@FieldDefaults(makeFinal = true,level = AccessLevel.PRIVATE)
public class ApplicationCommandLineRunner implements CommandLineRunner {

    UserService userService;
    
    ProductService productService;
    
    RoleRepository roleRepository;
    
    ProductRepository productRepository;
    static final  String PASSWORD = "password";
    static final  String JOHANNAID = "johanna@gmail.com";
    static final  String KEVINID ="kevin@gmail.com";
    static final  String ROGERID = "roger@gmail.com";
    static final  String ROLE_ADMIN = "ROLE_ADMIN";
    static final  String ROLE_USER = "ROLE_USER";

    @Autowired
    public ApplicationCommandLineRunner(UserService userService, ProductService productService, RoleRepository roleRepository, ProductRepository productRepository) {
        this.userService = userService;
        this.productService = productService;
        this.roleRepository = roleRepository;
        this.productRepository = productRepository;
    }

    @PostConstruct
    public void postConstruct(){
        log.warn("============ Application CommandLine Runner ============");
    }

    @Override
    public void run(String... args) throws Exception {


        userService.saveOrUpdate(new User(JOHANNAID, "Johanna", PASSWORD));
        userService.saveOrUpdate(new User(KEVINID, "Kevin", PASSWORD));
        userService.saveOrUpdate(new User(ROGERID, "roger", PASSWORD));

        roleRepository.save(new Role(JOHANNAID,ROLE_ADMIN));
        roleRepository.save(new Role(JOHANNAID,ROLE_USER));
        roleRepository.save(new Role(KEVINID,ROLE_USER));
        roleRepository.save(new Role(ROGERID,ROLE_USER));


        productService.saveOrUpdate(new Product(1,"Addidas shoe",new Category("Shoe")));
        productService.saveOrUpdate(new Product(2, "Gucci Womens's Bags",50,new Category("Bag"),4.00,4.99,new Customer("Jafer", Customer.type.CUSTOMER)));
        productService.saveOrUpdate(new Product(3, "Vuitton Sunglasses",20,new Category("Sunglass"),4.50,5.99,new Customer("Alex", Customer.type.WHOLESALER)));
        productService.saveOrUpdate(new Product(4, "Hourglass Jeans",50,new Category("Jean"),4.00,4.99,new Customer("David", Customer.type.CUSTOMER)));
        productService.saveOrUpdate(new Product(5, "Nike Sport Shoe",50,new Category("Shoe"),4.00,4.99,new Customer("Martin", Customer.type.WHOLESALER)));
        productService.saveOrUpdate(new Product(6, "Succi Ladies Bag",50,new Category("Bag"),4.00,4.99,new Customer("Doe", Customer.type.CUSTOMER)));

        try {
            userService.addProduct(JOHANNAID, productService.findById(1));
            userService.addProduct(JOHANNAID, productService.findById(2));
            userService.addProduct(JOHANNAID, productService.findById(3));
            userService.addProduct(KEVINID, productService.findById(1));
            userService.addProduct(KEVINID, productService.findById(2));
            userService.addProduct(KEVINID, productService.findById(3));
            userService.addProduct(KEVINID, productService.findById(4));
            userService.addProduct(KEVINID, productService.findById(5));
            userService.addProduct(ROGERID, productService.findById(1));
            userService.addProduct(ROGERID, productService.findById(2));
        } catch (NoSuchElementException ex){
            log.error("Couldn't add Product to User!");
            ex.printStackTrace();
        } catch (RuntimeException e){
            log.error("Couldn't add Products!");
            e.printStackTrace();
        }
        log.info("Find All Users Sorted By Name Desc");
        log.warn(userService.findAllSortedBy(Sort.by(Sort.Direction.DESC, "name")).toString());


    }
}

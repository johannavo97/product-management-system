package com.johanna.productmanagementproject.models;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@ToString
@NoArgsConstructor
@Table(name= "customer")
@RequiredArgsConstructor
public class Customer{

    public enum  type {
        CUSTOMER, WHOLESALER
    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NonNull
    private String name;
    private String address;
    private String phone;
    private String email;
    @NonNull
    @Enumerated(EnumType.STRING)
    private type type;


    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "customer_id")
    private List<Product> cusProductList;

    public void addProduct(Product p){
        cusProductList.add(p);
        p.getCustomer();
    }
}
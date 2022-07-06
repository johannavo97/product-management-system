package com.johanna.productmanagementproject.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;


import javax.persistence.*;
import java.util.LinkedHashSet;

import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
@Slf4j
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "product")
@Entity
public class Product {
    @NonNull
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    int productId;
    @NonNull
    String productName;

    Integer qty;

    @NonNull
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "category_id")
    Category category;

    Double purchasePrice;

    Double salePrice;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "customer_id")
    Customer customer;

    public Product(@NonNull int productId, @NonNull String productName, Integer qty, @NonNull Category category, Double purchasePrice, Double salePrice, Customer customer) {
        this.productId = productId;
        this.productName = productName;
        this.qty = qty;
        this.category = category;
        this.purchasePrice = purchasePrice;
        this.salePrice = salePrice;
        this.customer = customer;
    }

    @ToString.Exclude
    @ManyToMany(mappedBy = "products", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH}, fetch = FetchType.EAGER)
    Set<User> users = new LinkedHashSet<>();


    public void addUser(User user){
        users.add(user);
        user.getProducts().add(this);
    }

}

package com.student.productmanagementproject.models;

import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Slf4j
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "product")
@Entity
public class Product {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    int productId;
    @NonNull
    String productName;
    @NonNull
    String productCategory;
    @NonNull
    Double productPrice;

    @ToString.Exclude
    @ManyToMany(mappedBy = "products", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH}, fetch = FetchType.EAGER)
    Set<User> users = new LinkedHashSet<>();

    public void addUser(User user){
        users.add(user);
        user.getProducts().add(this);
    }

    public Product(int productId, @NonNull String productName, @NonNull String productCategory, @NonNull Double productPrice) {
        this.productId = productId;
        this.productName = productName;
        this.productCategory = productCategory;
        this.productPrice = productPrice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return productId == product.productId && productName.equals(product.productName) && productCategory.equals(product.productCategory) && productPrice == product.productPrice;
    }

    @Override
    public int hashCode() {
        return Objects.hash(productId,productName, productCategory, productPrice);

    }
}

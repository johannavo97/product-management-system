package com.johanna.productmanagementproject.controllers;

import com.johanna.productmanagementproject.models.Product;
import com.johanna.productmanagementproject.services.ProductService;
import com.johanna.productmanagementproject.services.UserService;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@Slf4j
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@RequestMapping("products")
public class ProductController {
    UserService userService;

    ProductService productService;

    @Autowired
    public ProductController(UserService userService, ProductService productService) {
        this.userService = userService;
        this.productService = productService;
    }

    @GetMapping("/products")
    public String listProducts(Model model) {
        model.addAttribute("products", productService.getAllProducts());
        return "products";
    }

    @GetMapping("/products/new")
    public String createProductForm(Model model) {

        // create product object to hold product form data
        Product product = new Product();
        model.addAttribute("product", product);
        return "create_product";

    }

    @PostMapping("/products")
    public String saveProduct(@ModelAttribute("product") Product product) {
        productService.saveOrUpdate(product);
        return "redirect:/products";
    }

    @GetMapping("/products/edit/{id}")
    public String editProductForm(@PathVariable Integer id, Model model) {
        model.addAttribute("product", productService.findById(id));
        return "edit_product";
    }

    @PostMapping("/products/{id}")
    public String updateProduct(@PathVariable Integer id,
                                @ModelAttribute("product") Product product,
                                Model model) {

        // get product from database by id
        Product existingProduct = productService.findById(id);
        existingProduct.setProductId(id);
        existingProduct.setProductName(product.getProductName());
        existingProduct.setQty(product.getQty());
        existingProduct.setCategory(product.getCategory());
        existingProduct.setPurchasePrice(product.getPurchasePrice());
        existingProduct.setSalePrice(product.getSalePrice());
        existingProduct.setCustomer(product.getCustomer());

        // save updated product object
        productService.updateProduct(existingProduct);
        return "redirect:/products";
    }

    // handler method to handle delete product request

    @GetMapping("/products/{id}")
    public String deleteProduct(@PathVariable Integer id) {
        productService.deleteProductById(id);
        return "redirect:/products";
    }


}

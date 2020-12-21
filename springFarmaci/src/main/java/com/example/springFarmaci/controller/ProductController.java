package com.example.springFarmaci.controller;

import com.example.springFarmaci.dto.ProductDTO;
import com.example.springFarmaci.models.Cart_Items;
import com.example.springFarmaci.models.Product;
import com.example.springFarmaci.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {
    @Autowired
    private ProductService productService;

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String Greeting(){
        return "Hello Boss";
    }

    @GetMapping("/products")
    public List<Product> getAllProducts() {
        return productService.findAll();
    }

    @RequestMapping(value = "/cart/delete{id}", method = RequestMethod.DELETE)
    public void deleteCartItem(@PathVariable Long id) {
        productService.deleteCartItem(id);
    }

    @GetMapping(value = "/cart/increment{id}")
    public void incrementQuantity(@PathVariable Long id) {
        productService.incrementQuantity(id);
    }

    @GetMapping(value = "/cart/decremet{id}")
    public void decrementQuantity(@PathVariable Long id) {
        productService.decrementQuantity(id);
    }

    @RequestMapping(value = "/products/delete{id}", method = RequestMethod.DELETE)
    public void deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
    }

    @RequestMapping(value = "/products/update{id}", method = RequestMethod.PUT)
    public void updateProduct(@RequestBody ProductDTO productDTO,
            @PathVariable Long id) {
        productService.updateProduct(productDTO, id);
    }

    @PostMapping(value = "/cart/add")
    public Cart_Items addToCart(@RequestBody Long id) {
        return productService.addToCart(id);
    }


}

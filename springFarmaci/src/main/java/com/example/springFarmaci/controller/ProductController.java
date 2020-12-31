package com.example.springFarmaci.controller;

import com.example.springFarmaci.dto.CartItemDTO;
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

    //Kthen te gjitha produktet
    @GetMapping("/products")
    public List<Product> getAllProducts() {
        return productService.findAll();
    }

    //Shton produkt te ri
    @PostMapping("/products/add")
    public Product addProduct(@RequestBody ProductDTO productDTO) {
        return productService.addProduct(productDTO);
    }

    //Update nje produkt
    @RequestMapping(value = "/products/update{id}", method = RequestMethod.PUT)
    public void updateProduct(@RequestBody ProductDTO productDTO,
                              @PathVariable("id") Long id) {
        productService.updateProduct(productDTO, id);
    }

    //Fshin nje produkt
    @RequestMapping(value = "/products/delete{id}", method = RequestMethod.DELETE)
    public void deleteProduct(@PathVariable("id") Long id) {
        productService.deleteProduct(id);
    }

    //Fshin nje produkt nga karta
    @RequestMapping(value = "/cart/delete{id}", method = RequestMethod.DELETE)
    public void deleteCartItem(@PathVariable Long id) {
        productService.deleteCartItem(id);
    }

    //Shton sasin ne karte
    @GetMapping(value = "/cart/increment{id}")
    public void incrementQuantity(@PathVariable Long id) {
        productService.incrementQuantity(id);
    }

    //Zbret sasin ne karte
    @GetMapping(value = "/cart/decremet{id}")
    public void decrementQuantity(@PathVariable Long id) {
        productService.decrementQuantity(id);
    }

    //Shton nje produkt ne karte
    @PostMapping(value = "/cart/add{id}")
    public Cart_Items addToCart(@RequestBody Long id) {
        return productService.addToCart(id);
    }

    @GetMapping("users/cart")
    public List<CartItemDTO> getCartItems() {
        return productService.getCurrentUserItems();
    }
}

package com.example.springFarmaci.service;

import com.example.springFarmaci.dto.ProductDTO;
import com.example.springFarmaci.dto.mappers.DTOMappers;
import com.example.springFarmaci.models.Cart_Items;
import com.example.springFarmaci.models.Product;
import com.example.springFarmaci.repository.CartItemsRepository;
import com.example.springFarmaci.repository.ProductRepository;
import com.example.springFarmaci.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CartItemsRepository cartItemsRepository;

    @Autowired
    private DTOMappers dtoMappers;

    @Autowired
    private MyUserDetailService myUserDetailService;

    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public Cart_Items addToCart(Long productId) {
        Cart_Items existingCartItem = cartItemsRepository.isPresent(productId);
        return cartItemsRepository.save(existingCartItem);
    }

    public void deleteProduct(Long id) {
        Product product = productRepository.findById(id).orElse(null);
        product.setToDate(new Date());
        productRepository.save(product);
    }

    public Product addProduct(ProductDTO productDTO) {
        Product product = new Product();
        product.setName(productDTO.getName());
        product.setPrice((int) productDTO.getPrice());
        product.setImgPath(productDTO.getImgPath());
        product.setQuantity(productDTO.getQuantity());
        product.setToDate(null);
        return productRepository.save(product);
    }

    public void updateProduct(ProductDTO productDTO, Long id) {
        Product product = productRepository.findById(id).orElse(null);
        product.setName(product.getName());
        product.setPrice((int) product.getPrice());
        product.setImgPath(productDTO.getImgPath());
        product.setQuantity(productDTO.getQuantity());
        product.setToDate(null);
    }

    @Transactional
    public void deleteCartItem(Long cartItemId) {
        cartItemsRepository.deleteItem(cartItemId);
    }

    public void incrementQuantity(Long cartItemId) {
        Cart_Items cart_items = cartItemsRepository.getOne(cartItemId);
        Integer newQuantity = cart_items.getQuantity() + 1;
        cart_items.setQuantity(newQuantity);
        cartItemsRepository.save(cart_items);
    }

    public void decrementQuantity(Long cartItemId) {
        Cart_Items cart_items = cartItemsRepository.getOne(cartItemId);
        Integer newQuantity = cart_items.getQuantity() - 1;
        cart_items.setQuantity(newQuantity);
        cartItemsRepository.save(cart_items);
    }
}
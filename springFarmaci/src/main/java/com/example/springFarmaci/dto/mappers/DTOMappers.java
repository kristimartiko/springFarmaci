package com.example.springFarmaci.dto.mappers;

import com.example.springFarmaci.dto.CartItemDTO;
import com.example.springFarmaci.models.Cart_Items;
import org.springframework.stereotype.Service;

@Service
public class DTOMappers {
    public CartItemDTO cartItemToMapper(Cart_Items cart_items) {
        Integer quantityAvailableForCurrentUsers = cart_items.getProduct().getQuantity() - cart_items.getQuantity();

        return new CartItemDTO( cart_items.getId(),
                cart_items.getProduct().getName(),
                cart_items.getProduct().getPrice(),
                cart_items.getQuantity(),
                cart_items.getProduct().getImgPath(),
                quantityAvailableForCurrentUsers
                );
    }
}

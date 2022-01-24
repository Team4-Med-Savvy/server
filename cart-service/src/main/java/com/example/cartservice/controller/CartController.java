package com.example.cartservice.controller;

import com.example.cartservice.dto.CartDto;
import com.example.cartservice.entity.Cart;
import com.example.cartservice.service.CartService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value="/cart")
public class CartController {

    @Autowired
    CartService cartService;

    @RequestMapping(method={RequestMethod.POST,RequestMethod.PUT})
    void save(@RequestBody CartDto cartDto) {
        cartService.save(copyDtoToEntity(cartDto));
    }


    @GetMapping(value="/id/{id}")
    CartDto getCartById(@PathVariable("id") String id){
        Cart cart=cartService.findById(id);
        return copyEntityToDto(cart);
    }

    @GetMapping(value="/email/{email}")
    CartDto getCartByEmail(@PathVariable("email") String email){
        Cart cart=cartService.findByEmail(email);
        return copyEntityToDto(cart);
    }

    private Cart copyDtoToEntity(CartDto cartDto){
        Cart cart = new Cart();
        BeanUtils.copyProperties(cartDto, cart);
        return cart;
    }

    private CartDto copyEntityToDto(Cart cart){
        CartDto cartDto = new CartDto();
        BeanUtils.copyProperties(cart, cartDto);
        return cartDto;
    }
}

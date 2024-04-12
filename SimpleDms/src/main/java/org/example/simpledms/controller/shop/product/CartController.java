package org.example.simpledms.controller.shop.product;

import lombok.extern.slf4j.Slf4j;
import org.example.simpledms.model.entity.shop.product.Cart;
import org.example.simpledms.service.shop.product.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * packageName : org.example.simpledms.controller.shop.product
 * fileName : CartController
 * author : PC
 * date : 2024-04-10
 * description :
 * 요약 :
 * <p>
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-04-10         PC          최초 생성
 */
@RestController
@Slf4j
@RequestMapping("/api/shop")
public class CartController {

    private final CartService cartService;

    @Autowired
    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @PostMapping("/cart")
    public ResponseEntity<Object> saveCart(@RequestBody Cart cart){
        try {
            Cart cart1 = cartService.save(cart);
            if (cart1 == null) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }else {
                return new ResponseEntity<>(cart1,HttpStatus.OK);
            }
        }catch (Exception e){
            log.debug(cart.toString());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

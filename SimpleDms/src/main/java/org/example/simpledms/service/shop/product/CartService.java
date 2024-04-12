package org.example.simpledms.service.shop.product;

import org.example.simpledms.model.entity.shop.product.Cart;
import org.example.simpledms.model.entity.shop.product.Product;
import org.example.simpledms.repository.shop.product.CartRepository;
import org.example.simpledms.repository.shop.product.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * packageName : org.example.simpledms.service.shop.product
 * fileName : CartService
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
@Service
public class CartService {

    private final CartRepository cartRepository;

    @Autowired
    public CartService(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }


    public Cart save(Cart cart){
        Cart cart1 = cartRepository.save(cart);
        return cart1;
    }
}

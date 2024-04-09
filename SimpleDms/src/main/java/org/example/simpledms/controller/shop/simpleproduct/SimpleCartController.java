package org.example.simpledms.controller.shop.simpleproduct;

import lombok.extern.slf4j.Slf4j;
import org.example.simpledms.model.entity.shop.simpleproduct.SimpleCart;
import org.example.simpledms.service.shop.simpleproduct.SimpleCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * packageName : org.example.simpledms.controller.shop.simpleproduct
 * fileName : SimpleCartContoller
 * author : PC
 * date : 2024-04-09
 * description :
 * 요약 :
 * <p>
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-04-09         PC          최초 생성
 */
@Slf4j
@Controller
@RequestMapping("/api/shop")
public class SimpleCartController {

    private final SimpleCartService simpleCartService;
    @Autowired
    public SimpleCartController(SimpleCartService simpleCartService) {
        this.simpleCartService = simpleCartService;
    }

    @PostMapping("/simple-cart")
    public ResponseEntity<Object> saveCart(@RequestBody SimpleCart cart){
        try {
            SimpleCart simpleCart = simpleCartService.save(cart);
            if (cart == null) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }else {
                return new ResponseEntity<>(simpleCart,HttpStatus.OK);
            }
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}

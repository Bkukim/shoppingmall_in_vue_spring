package org.example.simpledms.controller.shop.simpleproduct;

import lombok.extern.slf4j.Slf4j;
import org.example.simpledms.model.dto.shop.simleproduct.SimpleCartDto;
import org.example.simpledms.model.entity.shop.simpleproduct.SimpleCart;
import org.example.simpledms.model.entity.shop.simpleproduct.SimpleProduct;
import org.example.simpledms.service.shop.simpleproduct.SimpleCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

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
@RestController
@RequestMapping("/api/shop")
public class SimpleCartController {

    private final SimpleCartService simpleCartService;
    @Autowired
    public SimpleCartController(SimpleCartService simpleCartService) {
        this.simpleCartService = simpleCartService;
    }

    @GetMapping("/simple-cart")
    public ResponseEntity<Object> getAll(@RequestParam(defaultValue = "") String title,
                                         @RequestParam(defaultValue = "3") int page,
                                         @RequestParam(defaultValue = "1") int size){
        try {
            Pageable pageable = PageRequest.of(page,size);
            Page<SimpleCartDto> pageList = simpleCartService.findAll(title, pageable);
            Map<String, Object> response = new HashMap<>();

            response.put("simpleCart", pageList.getContent());
            response.put("currentPage", pageList.getNumber());
            response.put("totalItems", pageList.getTotalElements());
            response.put("totalPages", pageList.getTotalPages());

            if (response.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }else {
                return new ResponseEntity<>(response,HttpStatus.OK);
            }
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
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

    @DeleteMapping("/simple-cart/deletion/{scno}")
    public ResponseEntity<Object> deleteCart(@PathVariable int scno){
        try {
            boolean success = simpleCartService.delete(scno);
            if (success) {
                return new ResponseEntity<>(HttpStatus.OK);
            }else{
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

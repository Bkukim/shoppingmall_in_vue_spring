package org.example.simpledms.controller.shop.simpleproduct;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.simpledms.model.dto.shop.simleproduct.SimpleOrderDto;
import org.example.simpledms.model.entity.shop.simpleproduct.SimpleOrder;
import org.example.simpledms.model.entity.shop.simpleproduct.SimpleProduct;
import org.example.simpledms.service.shop.simpleproduct.SimpleOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

/**
 * packageName : org.example.simpledms.controller.shop.simpleproduct
 * fileName : SimpleOrderController
 * author : PC
 * date : 2024-04-11
 * description :
 * 요약 :
 * <p>
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-04-11         PC          최초 생성
 */
@Slf4j
@RestController
@RequestMapping("/api/shop")
@RequiredArgsConstructor
public class SimpleOrderController {

    private final SimpleOrderService simpleOrderService;
    //    TODO: 저장 함수
    @PostMapping("/simple-order")
//    저장(insert) -> post 방식 -> @PostMapping
    public ResponseEntity<Object> create(
            @RequestBody SimpleOrderDto simpleOrderDto
    ) {
        try {
//            저장 서비스 실행
            SimpleOrder simpleOrder = simpleOrderService.insert(simpleOrderDto);

            return new ResponseEntity<>(simpleOrder, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/simple-order/{sono}")
    public ResponseEntity<Object> getById(@PathVariable int sono){
        try {
            Optional<SimpleOrder> simpleOrder = simpleOrderService.findById(sono);
            if (simpleOrder.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }else{
                return new ResponseEntity<>(simpleOrder, HttpStatus.OK);
            }
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}


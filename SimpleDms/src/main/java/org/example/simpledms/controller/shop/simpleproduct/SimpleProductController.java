package org.example.simpledms.controller.shop.simpleproduct;

import lombok.extern.slf4j.Slf4j;
import org.example.simpledms.model.entity.shop.simpleproduct.SimpleProduct;
import org.example.simpledms.service.shop.simpleproduct.SimpleProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * packageName : org.example.simpledms.controller.shop.simpleproduct
 * fileName : SimpleProductController
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
public class SimpleProductController {

    private final SimpleProductService simpleProductService;

    @Autowired
    public SimpleProductController(SimpleProductService simpleProductService) {
        this.simpleProductService = simpleProductService;
    }

    @GetMapping("/simple-product")
    public ResponseEntity<Object> getAll(@RequestParam(defaultValue = "") String title,
                                         @RequestParam(defaultValue = "3") int page,
                                         @RequestParam(defaultValue = "1") int size){
        try {
            Pageable pageable = PageRequest.of(page,size);
            Page<SimpleProduct> pageList = simpleProductService.findAll(title, pageable);
            Map<String, Object> response = new HashMap<>();

            response.put("simpleProduct", pageList.getContent());
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

    @GetMapping("/simple-product/{spno}")
    public ResponseEntity<Object> getById(@PathVariable int spno){
        try {
            Optional<SimpleProduct> simpleProduct = simpleProductService.findById(spno);
            if (simpleProduct.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }else{
                return new ResponseEntity<>(simpleProduct, HttpStatus.OK);
            }
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

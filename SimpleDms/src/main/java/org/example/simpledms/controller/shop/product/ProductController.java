package org.example.simpledms.controller.shop.product;

import lombok.extern.slf4j.Slf4j;
import org.example.simpledms.model.entity.shop.product.Product;
import org.example.simpledms.model.entity.shop.simpleproduct.SimpleProduct;
import org.example.simpledms.service.shop.product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.Map;

/**
 * packageName : org.example.simpledms.controller.shop.product
 * fileName : ProductController
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
@Controller
@Slf4j
@RequestMapping("/api/shop")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/product")
    public ResponseEntity<Object> getAll(@RequestParam(defaultValue = "") String title,
                                         @RequestParam(defaultValue = "3") int page,
                                         @RequestParam(defaultValue = "1") int size){
        try {
            Pageable pageable = PageRequest.of(page,size);
            Page<Product> pageList = productService.findAll(title, pageable);
            Map<String, Object> response = new HashMap<>();

            response.put("product", pageList.getContent());
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

}

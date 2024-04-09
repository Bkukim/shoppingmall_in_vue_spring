package org.example.simpledms.service.shop.product;

import lombok.extern.slf4j.Slf4j;
import org.example.simpledms.model.entity.shop.product.Product;
import org.example.simpledms.repository.shop.product.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * packageName : org.example.simpledms.service.shop.product
 * fileName : ProductService
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
@Service
public class ProductService {

    private final ProductRepository productRepository;
    @Autowired

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Page<Product> findAll(String pname, Pageable pageable){
        Page<Product> page= productRepository.findAllByPnameContaining(pname, pageable);
        return page;
    }
}

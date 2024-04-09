package org.example.simpledms.service.shop.simpleproduct;

import org.example.simpledms.model.entity.shop.simpleproduct.SimpleProduct;
import org.example.simpledms.repository.shop.simpleproduct.SimpleProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * packageName : org.example.simpledms.service.shop
 * fileName : SimpleProduct
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
@Service
public class SimpleProductService {
    private final SimpleProductRepository simpleProductRepository;

    @Autowired

    public SimpleProductService(SimpleProductRepository simpleProductRepository) {
        this.simpleProductRepository = simpleProductRepository;
    }

    //
    public Page<SimpleProduct>findAll(String title, Pageable pageable){
        Page<SimpleProduct> page = simpleProductRepository.findAllByTitleContaining(title,pageable);
        return page;
    }

    public Optional<SimpleProduct> findById(int spno){
        Optional<SimpleProduct> simpleProduct = simpleProductRepository.findById(spno);
        return simpleProduct;
    }
}

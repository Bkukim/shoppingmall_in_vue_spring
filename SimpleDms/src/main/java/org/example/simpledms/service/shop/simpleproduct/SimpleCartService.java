package org.example.simpledms.service.shop.simpleproduct;

import org.example.simpledms.model.dto.shop.simleproduct.SimpleCartDto;
import org.example.simpledms.model.entity.shop.simpleproduct.SimpleCart;
import org.example.simpledms.model.entity.shop.simpleproduct.SimpleProduct;
import org.example.simpledms.repository.shop.simpleproduct.SimpleCartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * packageName : org.example.simpledms.service.shop.simpleproduct
 * fileName : SimpleCartService
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
public class SimpleCartService {

    private final SimpleCartRepository simpleCartRepository;

    @Autowired

    public SimpleCartService(SimpleCartRepository simpleCartRepository) {
        this.simpleCartRepository = simpleCartRepository;
    }

    // 전체조회
    public Page<SimpleCartDto> findAll(String title, Pageable pageable) {
        Page<SimpleCartDto> page = simpleCartRepository.selectByTitleContaining(title, pageable);
        return page;
    }

    public SimpleCart save(SimpleCart simpleCart){
        SimpleCart simpleCart1 = simpleCartRepository.save(simpleCart);
        return simpleCart1;
    }

    public boolean delete(int scno){
        if (simpleCartRepository.existsById(scno)) {
            simpleCartRepository.deleteById(scno);
            return true;
        }else {
            return false;
        }
    }
}

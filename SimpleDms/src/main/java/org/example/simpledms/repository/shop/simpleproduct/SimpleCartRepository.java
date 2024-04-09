package org.example.simpledms.repository.shop.simpleproduct;

import org.example.simpledms.model.entity.shop.simpleproduct.SimpleCart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * packageName : org.example.simpledms.repository.shop.simpleproduct
 * fileName : SimpleCartRepository
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
@Repository
public interface SimpleCartRepository extends JpaRepository<SimpleCart, Integer> {

}

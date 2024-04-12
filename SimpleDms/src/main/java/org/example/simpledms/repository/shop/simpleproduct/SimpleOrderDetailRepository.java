package org.example.simpledms.repository.shop.simpleproduct;

import org.example.simpledms.model.common.SonoSpnoPk;
import org.example.simpledms.model.entity.shop.simpleproduct.SimpleOrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * packageName : org.example.simpledms.repository.shop.simpleproduct
 * fileName : SimpleOrderDetailRepository
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
@Repository
public interface SimpleOrderDetailRepository extends JpaRepository<SimpleOrderDetail, SonoSpnoPk> { // 복합키를 사용하는 애는 복합키 정의 클래스를 기본키의 자료형으로 해주어야한다.
}

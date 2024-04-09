package org.example.simpledms.repository.shop.simpleproduct;

import org.example.simpledms.model.entity.shop.simpleproduct.SimpleProduct;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * packageName : org.example.simpledms.repository.product
 * fileName : SimpleProductRepository
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
public interface SimpleProductRepository extends JpaRepository<SimpleProduct,Integer>  {
    @Query(value = "SELECT * FROM TB_SIMPLE_PRODUCT\n" +
            "WHERE TITLE LIKE '%'|| :title ||'%'"
            ,countQuery = "SELECT count(*) FROM TB_SIMPLE_PRODUCT\n" +
            "WHERE TITLE LIKE '%'|| :title ||'%'"
            ,nativeQuery = true)
    public Page<SimpleProduct> findAllByTitleContaining(@Param("title") String title, Pageable pageable);
}

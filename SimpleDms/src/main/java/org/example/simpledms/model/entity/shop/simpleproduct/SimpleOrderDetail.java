package org.example.simpledms.model.entity.shop.simpleproduct;

import jakarta.persistence.*;
import lombok.*;
import org.example.simpledms.model.common.SonoSpnoPk;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

/**
 * packageName : org.example.simpledms.model.entity.shop.simpleproduct
 * fileName : SimpleOrderDetail
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
@Entity
@Table(name = "TB_SIMPLE_ORDER_DETAIL")
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@DynamicInsert
@DynamicUpdate
@IdClass(SonoSpnoPk.class) // 이걸 붙여야 복합키 사용이 가능하다.
public class SimpleOrderDetail {

    @Id
    private  int sono; // (PK 복합키)
    @Id
    private  int spno; // (PK2 복합키) 이렇게 기본키가 두개면 복합키를 정의하는 클래스를 따로 만들어야한다. 공통에서 패키지이서 SonoSpnoPk 를 만들어보자.
    private  int productCount;
}

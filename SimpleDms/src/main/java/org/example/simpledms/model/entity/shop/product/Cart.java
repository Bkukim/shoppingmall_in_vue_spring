package org.example.simpledms.model.entity.shop.product;

import jakarta.persistence.*;
import lombok.*;
import org.example.simpledms.model.common.BaseTimeEntity2;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

/**
 * packageName : org.example.simpledms.model.entity.shop.product
 * fileName : Cart
 * author : PC
 * date : 2024-04-10
 * description :
 * 요약 :
 * <p>
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-04-10         PC          최초 생성
 */
@Entity
@Getter
@Setter
@Builder
@DynamicInsert
@DynamicUpdate
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "TB_CART")
@SequenceGenerator(
        name = "SQ_CART_GENERATOR"
        , sequenceName = "SQ_CART"
        , initialValue = 1
        , allocationSize = 1
)
public class Cart extends BaseTimeEntity2 {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE
            ,generator = "SQ_CART_GENERATOR"
    )
    private int cno;
    private int pno;
    private int amount;

}

package org.example.simpledms.model.entity.shop.simpleproduct;

import jakarta.persistence.*;
import lombok.*;
import org.example.simpledms.model.common.BaseTimeEntity2;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

/**
 * packageName : org.example.simpledms.model.entity.shop.simpleproduct
 * fileName : SimpleCart
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
@Entity
@Getter
@Setter
@DynamicInsert
@DynamicUpdate
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "TB_SIMPLE_CART")
@SequenceGenerator(
        name = "SQ_SIMPLE_CART_GENERATOR"
        , sequenceName = "SQ_SIMPLE_CART"
        , initialValue = 1
        , allocationSize = 1
)
@Where(clause = "DELETE_YN = 'N'")
@SQLDelete(sql = "UPDATE TB_SIMPLE_CART SET DELETE_YN = 'Y', DELETE_TIME=TO_CHAR(SYSDATE, 'YYYY-MM-DD HH24:MI:SS') WHERE SCNO = ?")
public class SimpleCart extends BaseTimeEntity2 {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE
            ,generator = "SQ_SIMPLE_CART_GENERATOR"
    )
    private int scno;
    private int spno;
    private int cartCount;

}

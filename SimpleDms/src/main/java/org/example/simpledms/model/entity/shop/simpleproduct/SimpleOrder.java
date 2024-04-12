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
 * fileName : SimpleOrder
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
@Getter
@Setter
@DynamicInsert
@DynamicUpdate
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "TB_SIMPLE_ORDER")
@SequenceGenerator(
        name = "SQ_SIMPLE_ORDER_GENERATOR"
        , sequenceName = "SQ_SIMPLE_ORDER"
        , initialValue = 1
        , allocationSize = 1
)
@Where(clause = "DELETE_YN = 'N'")
@SQLDelete(sql = "UPDATE TB_SIMPLE_ORDER SET DELETE_YN = 'Y', DELETE_TIME=TO_CHAR(SYSDATE, 'YYYY-MM-DD HH24:MI:SS') WHERE SCNO = ?")
public class SimpleOrder extends BaseTimeEntity2 {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE
            ,generator = "SQ_SIMPLE_ORDER_GENERATOR"
    )
    private int sono;
    private String orderDate;
    private int orderStatus;
    private int productAmount;
    private int deliveryAmount;
    private int orderAmount;
    private String deliveryAddr;
    private String deliveryMsg;
}

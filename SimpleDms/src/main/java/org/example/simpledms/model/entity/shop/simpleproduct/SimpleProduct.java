package org.example.simpledms.model.entity.shop.simpleproduct;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

/**
 * packageName : org.example.simpledms.model.entity.shop.simpleproduct
 * fileName : simpleProduct
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
@Builder
@DynamicInsert
@DynamicUpdate
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "TB_SIMPLE_PRODUCT")
@SequenceGenerator(
        name = "SQ_SIMPLE_PRODUCT_GENERATOR"
        , sequenceName = "SQ_SIMPLE_PRODUCT"
        , initialValue = 1
        , allocationSize = 1
)


public class SimpleProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE
            ,generator = "SQ_SIMPLE_PRODUCT_GENERATOR"
    )
    private int spno;
    private int codeId;
    private String title;
    private String imgPath;
    private int unitPrice;
    private int inventoryCount;
    private String useYn;
}

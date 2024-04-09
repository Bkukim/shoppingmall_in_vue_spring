package org.example.simpledms.model.entity.shop.product;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

/**
 * packageName : org.example.simpledms.model.entity.shop.product
 * fileName : Product
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
@Table(name = "TB_PRODUCT")
@SequenceGenerator(
        name = "SQ_PRODUCT_GENERATOR"
        , sequenceName = "SQ_PRODUCT"
        , initialValue = 1
        , allocationSize = 1
)

public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE
            ,generator = "SQ_PRODUCT_GENERATOR"
    )
    private int pno;
    private int kindCode;
    private String pname;
    private String image;
    private int unitPrice;
    private int inventoryCount;
    private String useYn;
}

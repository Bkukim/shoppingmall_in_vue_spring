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
 * fileName : SimpleApproval
 * author : PC
 * date : 2024-04-12
 * description :
 * 요약 :
 * <p>
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-04-12         PC          최초 생성
 */
@Entity
@Getter
@Setter
@DynamicInsert
@DynamicUpdate
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "TB_SIMPLE_APPROVAL")
@SequenceGenerator(
        name = "SQ_SIMPLE_APPROVAL_GENERATOR"
        , sequenceName = "SQ_SIMPLE_APPROVAL"
        , initialValue = 1
        , allocationSize = 1
)
public class SimpleApproval {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE
            ,generator = "SQ_SIMPLE_APPROVAL_GENERATOR"
    )
    private int sano;
    private int sono;
    private String approvalDate;
    private int approvalAmount;
}

package org.example.simpledms.model.dto.shop.simleproduct;

import lombok.*;
import org.example.simpledms.model.entity.shop.simpleproduct.SimpleOrderDetail;

import java.util.List;

/**
 * packageName : org.example.simpledms.model.dto.shop.simleproduct
 * fileName : SimpleOrderDto
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
@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class SimpleOrderDto { // 프론트에서 보내주는 배열은 백엔드의 entity 의 타입에는 없다. 그래서 Dto로 받아서 service 에서 이것을 다시 entity에 넣어줘야한다.
    private Integer sono; // 기본키, 시퀀스
    private List<SimpleOrderDetail> simpleOrderDetailList; // 주문상세 객체배열
    private String orderDate;                              // 주문날짜
    private Integer orderStatus;        // 주문상태(50001: 주문완료, 50002: 결재완료, 50011:결재취소)
    private Integer productAmount;      // 상품 총금액
    private Integer deliveryAmount;      // 배달비(3000 : 하드코딩)
    private Integer orderAmount;          // 주문금액 = 총금액 + 배달비
    private String deliveryAddr;          // 배달 주소
    private String deliveryMsg;           // 배달 메세지
}

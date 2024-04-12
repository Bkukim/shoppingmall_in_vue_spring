package org.example.simpledms.service.shop.kafka.simpleproduct;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.simpledms.model.entity.shop.simpleproduct.SimpleOrder;
import org.example.simpledms.service.shop.simpleproduct.SimpleOrderService;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Optional;

/**
 * packageName : org.example.simpledms.service.shop.kafka.simpleproduct
 * fileName : SimpleApprovalConsumer
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
@Slf4j
@Service
@RequiredArgsConstructor
public class SimpleApprovalConsumer { // 메세지가 왔는지를 계속 감시하는 프로그램이다. -> 구독하고 있다고 말한다.

    // 1) 주문완료(50001) -> 결제완료(50002)로 변경해야함
    private final SimpleOrderService simpleOrderService; // di

    // 2) 카프카에서 보내줄 메세지 감시하는 함수 정의
    @KafkaListener(topics = "simple-approval", groupId = "academy") // 감시할 주제를 적어주고, application.properties에 소비자 그룹을 academy로 정해주었다. 여기는 소비자 기능이므로 그걸 groupId로 설정해준다.
    public void consume(String message) throws IOException{
        log.debug("SimpleConsumer(소비자): " + message); // 여기부터 소비자 로그인것을 나타내기 위함

        if(message.contains(":")){
            String[] token = message.split(":");
            int sono = Integer.parseInt(token[0]);          // 주문 번호
            int orderStatus = Integer.parseInt(token[1]);   // 주문 상태

            log.debug("주문 번호:" + sono);
            log.debug("주문 상태:" + orderStatus);

            //           TODO: 50001(주문완료) -> 50002(결재완료) update
//           TODO: 1) 주문 상세조회(주문번호(sono))
            Optional<SimpleOrder> optionalSimpleOrder
                    = simpleOrderService.findById(sono);
            SimpleOrder simpleOrder = optionalSimpleOrder.get();
//           TODO: 50001(주문완료) -> 50002(결재완료) 객체의 속성 수정
            simpleOrder.setOrderStatus(orderStatus); // 주문객체의 주문상태값 수정

            simpleOrderService.update(simpleOrder);

        }
    }
}

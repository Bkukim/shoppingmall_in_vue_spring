package org.example.simpledms.service.shop.kafka.simpleproduct;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

/**
 * packageName : org.example.simpledms.service.shop.kafka.simpleproduct
 * fileName : SimpleApprovalProducer
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
public class SimpleApprovalProducer {

    private static final String TOPIC = "simple-approval"; // 주제를 정해주어야한다.

    private final KafkaTemplate<String, String> kafkaTemplate;

    public void sendMessage(String message){
        log.debug("SimpleApprovalProducer(결제 생산자):" + message); // 카프카는 전송시 에러가 많이 발생하기에 로그를 해주는 것이 좋다.
        this.kafkaTemplate.send(TOPIC,message); // 카프카로 매개변수의 값 보내기
    }
}

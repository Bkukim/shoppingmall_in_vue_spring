package org.example.simpledms.service.shop.simpleproduct;

import lombok.RequiredArgsConstructor;
import org.example.simpledms.model.entity.shop.simpleproduct.SimpleApproval;
import org.example.simpledms.repository.shop.simpleproduct.SimpleApprovalRepository;
import org.example.simpledms.service.shop.kafka.simpleproduct.SimpleApprovalProducer;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * packageName : org.example.simpledms.service.shop.simpleproduct
 * fileName : SimpleApprovalService
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
// todo 카프카 사용해서 주문상태를 50001(주문완료) -> 50002(결제완료)변경해보자
//  => 카프가 : 메세징관리 프로그램으로 수신자와 송신자 사이에서 메세지 전달.
//      1) 송신자 : 50001 -> 50002로 변경하라고 카프카에 메세지 전송을하면
//      2) 수신자 : 주문테이블에 주문상태 컬럼 값을 변경한다.
//      이런 방식으로 msa에서 메세지를 전달하며 통신한다. msa환경에서는 백엔드 서버가 여러개여서 송수신자가 낱개로 떨어져 있기에 이렁 통신 시스템이 필요하다.
@Service
public class SimpleApprovalService {
    @Autowired
    private SimpleApprovalRepository simpleApprovalRepository; // DI

    @Autowired
    private SimpleApprovalProducer producer;     // 카프카 생산자(송신자) DI

    //    TODO: 저장함수
//     DTO : 1) 클래스 DTO : 프론트에서 객체 전송시 적당한 엔티티가 없으면 DTO 정의해서 사용
//           2) 인터페이스 DTO : 레파지토리에서 sql 결과로 담은 엔티티가 없으면 DTO 정의해서 사용
    public SimpleApproval save(SimpleApproval simpleApproval) {
//        DB 저장 함수 실행
        SimpleApproval simpleApproval2
                = simpleApprovalRepository.save(simpleApproval);

//        TODO: 카프카 사용 : 주문상태 변경용도 사용 - 50001(주문완료) -> 50002(결재완료)
//          => 카프카 : 메세징 관리 프로그램 ( 수신자 <-> 송신자 )
//          1) 송신자 : 50001(주문완료) -> 50002(결재완료) 변경하라고 카프카에 메세지 전송
//          2) 수신자 : 주문테이블에 주문상태 컬럼 값을 변경(update)
//          => MSA 환경에서는 송신자(spring:생산자(Producer), 수신자(spring:소비자(Consumer)
//          메세지 형태 설계 - 주문번호:주문상태
//          카프카 메세지 함수 : sendMessage(메세지) => 예) sendMessage("1:50002")
        producer.sendMessage(simpleApproval2.getSono() + ":" + "50002");

        return simpleApproval2;
    }
}






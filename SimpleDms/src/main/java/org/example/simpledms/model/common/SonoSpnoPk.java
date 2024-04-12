package org.example.simpledms.model.common;

import lombok.*;

import java.io.Serializable;

/**
 * packageName : org.example.simpledms.model.common
 * fileName : SonoSpnoPk
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
@EqualsAndHashCode // 롬북 어노테이션으로 equals와 hashcode를 재정의 해주는 어노테이션이다.
public class SonoSpnoPk implements Serializable { // jpa에서 복하ㅂ키를 사용하기 위해서는 복합키 정의 클래스를 따로 만들어주어야한다.
    // 복합키랑 컬럼 두개
    private int sono;
    private int spno;
}

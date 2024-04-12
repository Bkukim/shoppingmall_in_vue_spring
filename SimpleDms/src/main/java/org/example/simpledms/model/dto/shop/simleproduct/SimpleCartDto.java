package org.example.simpledms.model.dto.shop.simleproduct;

/**
 * packageName : org.example.simpledms.model.dto.shop.simleproduct
 * fileName : SimpleCartDto
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
public interface SimpleCartDto {
    Integer getScno();       // 기본키, 시퀀스, 장바구니 번호

    Integer getSpno();       // 기본키, 시퀀스, 상품 번호
    Integer getCodeId();     // 코드번호

    String getTitle();
    String getImgPath();
    Integer getUnitPrice();
    Integer getCartCount();
}

// SimpleProductService.js
// axios CRUD 공통 함수
// 상품 공통 함수
import http from "@/utils/http-common";

class SimpleProductService {
    // TODO: 상품 전체 조회
    // 조회(select) -> get 방식 -> @GetMapping
    getAll(title, page, size) {
        return http.get(`/shop/simple-product?title=${title}&page=${page}&size=${size}`);
    }
    // TODO: 상품 상세 조회 : 기본키(상품번호(spno))
    // 조회(select) -> get 방식 -> @GetMapping
    get(spno) {
        return http.get(`/shop/simple-product/${spno}`);
    }

}

export default new SimpleProductService();
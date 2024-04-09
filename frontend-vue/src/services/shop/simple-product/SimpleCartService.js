// SimpleCartService.js
// 장바구니 공통 CRUD 함수
import http from "@/utils/http-common";

class SimpleCartService {
    // TODO: 장바구니 전체조회
    getAll(title, page, size) {
        return http.get(`/shop/simple-cart?title=${title}&page=${page}&size=${size}`);
    }
    // TODO: 장바구니 저장함수
    create(cart) {
        return http.post("/shop/simple-cart", cart);
    }

}

export default new SimpleCartService();
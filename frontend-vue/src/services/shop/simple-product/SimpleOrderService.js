
import http from "@/utils/http-common";

class SimpleOrderService {
    // todo 주문 상세 함수
    get(sono){
        return http.get(`/shop/simple-order/${sono}`)
    }

    // TODO: 주문 저장함수
    create(data) {
        return http.post("/shop/simple-order", data);
    }
}

export default new SimpleOrderService();
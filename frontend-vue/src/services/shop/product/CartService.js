import http from '../../../utils/http-common'

class CartService{
    // 장바구니 전체 조회
    getAll(title,page,size){
        return http.get(`/shop/cart?title=${title}&page=${page}&size=${size}`);
    }

    // 장바구니 저장
    save(cart){
        return http.post("/shop/cart", cart);
    }
}

export default new CartService();
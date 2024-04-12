import http from '../../../utils/http-common'

class ProductService{
    // 상품 전체 조회
    getAll(title,page,size){
        return http.get(`/shop/product?title=${title}&page=${page}&size=${size}`);
    }

    // 상품 상세 조회
    get(pno){
        return http.get(`/shop/product/${pno}`);
    }
}

export default new ProductService();
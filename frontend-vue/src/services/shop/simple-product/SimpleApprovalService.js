
import http from "@/utils/http-common";

class SimpleApprovalService {
    

    // TODO: 결제 저장함수
    create(data) {
        return http.post("/shop/simple-approval", data);
    }
}

export default new SimpleApprovalService();
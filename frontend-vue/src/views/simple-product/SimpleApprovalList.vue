<template>
  <div>
    <div>
      <h1>SimpleApproval</h1>

      <div class="col-md-12">
        <!-- {/* table start */} -->
        <table class="table">
          <thead>
            <tr>
              <th scope="col">sono<br />&nbsp;</th>
              <th scope="col">order<br />Date</th>
              <th scope="col">order<br />Status</th>
              <th scope="col">product<br />Amount</th>
              <th scope="col">delivery<br />Amount</th>
              <th scope="col">order<br />Amount</th>
              <th scope="col">delivery<br />Addr</th>
              <th scope="col">delivery<br />Msg</th>
            </tr>
          </thead>
          <tbody>
            <tr>
              <td>{{ simpleOrder?.sono }}</td>
              <!-- 새로 나온 js문법으로 변수가 null이면 undefined로 바꿔준다. null일경울 속성을 부르면 에러가 발생하는데 이걸 막아준다.-->
              <td>{{ simpleOrder?.orderDate }}</td>
              <td>{{ simpleOrder?.orderStatus }}</td>
              <td>{{ simpleOrder?.productAmount }}</td>
              <td>{{ simpleOrder?.deliveryAmount }}</td>
              <td>{{ simpleOrder?.orderAmount }}</td>
              <td>{{ simpleOrder?.deliveryAddr }}</td>
              <td>{{ simpleOrder?.deliveryMsg }}</td>
            </tr>
          </tbody>
        </table>
        <!-- {/* table end */} -->
      </div>

      <h1>SimpleApproval Admin Panel</h1>

      <div class="row d-flex justify-content-end">
        <button
          type="button"
          class="btn btn-danger w-25 me-3"
          @click="cancelApproval"
        >
          결제 취소
        </button>

        <button
          type="button"
          class="btn btn-warning w-25"
          @click="confirmApproval"
        >
          결제
        </button>
      </div>
    </div>
  </div>
</template>
<script>
import SimpleOrderService from "@/services/shop/simple-product/SimpleOrderService";
import SimpleApprovalService from "@/services/shop/simple-product/SimpleApprovalService";

export default {
  data() {
    return {
      simpleOrder: null, // 주문 객체
      simpleApproval: null, // 결제 객체
      sono: this.$route.params.sono,
    };
  },
  methods: {
    // 주문 전체 조회 함수 - 화면이 뜰때 실행
    async retrieveSimpleOrder() {
      try {
        let response = await SimpleOrderService.get(this.sono);
        this.simpleOrder = response.data;
        console.log(response.data);
      } catch (e) {
        console.log(e);
      }
    },
    // 결제 확정 함수 - 결제 버튼에 장착
    async confirmApproval() {
      // 1) 결제 날짜 : 현재 날짜
      let now = new Date();
      let formatNow = `${now.getFullYear()}-${now.getMonth()}-${now.getDate()} ${now.getHours()}:${now.getMinutes()}:${now.getSeconds()}`;
      let data={
        sano:null,                                    // 결제 번호    
        sono:this.simpleOrder?.sono,                  // 주문 번호
        approvalData : formatNow,                     // 주문 날짜
        approvalAmount: this.simpleOrder?.orderAmount // 주문 금액
      }
      // 2) 임시 객체 정의

      // 3) 결제 테이블에 insert 실행
      try {
       let response = await SimpleApprovalService.create(data);
       this.simpleApproval = response.data; // 결제가 된 객체
       console.log(response.data);
       alert("결제가 완료되었습니다.");
       // 주문상태 변경
       // 주문 상세조회 재조회
       this.retrieveSimpleOrder();
      } catch (error) {
        console.log(error);
      }
      
    },
    // 결제 취소 함수 - 결제 취소 버튼에 장착
    cancelApproval() {},
  },
  mounted() {
    this.retrieveSimpleOrder();
  },
};
</script>
<style></style>

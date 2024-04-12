// 핵심:복잡
<template>
  <div>
    <!-- {/* dname start */} -->
    <div class="row mb-5 justify-content-center">
      <!-- {/* w-50 : 크기 조정, mx-auto : 중앙정렬(margin: 0 auto), justify-content-center */} -->
      <div class="col-12 w-50 input-group mb-3">
        <input type="text" class="form-control" placeholder="상품 입력" v-model="searchTitle"/>
        <div class="input-group-append">
          <button class="btn btn-outline-secondary" type="button" @click="retrieveSimpleCart">
            Search
          </button>
        </div>
      </div>
    </div>
    <!-- {/* dname end */} -->

    <!-- {/* paging 시작 */} -->
    <div class="mb-3">
      Items per Page:
      <select @change="pageSizeChange">
        <option  v-for="(data, index) in pageSizes" :key="index">{{ data }}</option>
      </select>
    </div>

    <b-pagination
      v-model="page"
      :total-rows="count"
      :per-page="pageSize"
      @click="retrieveSimpleCart"
    ></b-pagination>
    <!-- {/* paging 끝 */} -->

    <div class="row" >
      <div class="card mb-3" v-for="(data, index) in simpleCart" :key="index">
        <div class="row g-0 p-3">
          <div class="col-md-4 p-3 border">
            <img
                :src="data.imgPath"
              class="img-fluid rounded-start"
              alt="..."
              style="{ height: 15 + 'vh', width: 5 + 'vw' }"
            />
          </div>
          <div class="col-md-8">
            <div class="card-body">
              <h5 class="card-title">상품 : {{ data.title }}</h5>
              <h5 class="card-title">상품 가격 : {{ data.unitPrice }}</h5>
              <h5 class="card-title">상품 개수 : {{ data.cartCount }}</h5>
            </div>
          </div>
        </div>
      </div>
    </div>

    <!-- {/* 배송지 시작 */} -->
    <div class="col-12 mx-auto">
      <div class="row g-3 align-items-center mb-3">
        <div class="col-3">
          <label htmlFor="deliveryAddr" class="col-form-label">
            Delivery Address
          </label>
        </div>

        <div class="col-9">
          <input
            type="text"
            id="deliveryAddr"
            required
            class="form-control"
            placeholder="배송지 입력"
            name="deliveryAddr"
          />
        </div>
      </div>

      <div class="row g-3 align-items-center mb-3">
        <div class="col-3">
          <label htmlFor="deliveryMsg" class="col-form-label">
            Melivery Message
          </label>
        </div>
        <div class="col-9">
          <input
            type="text"
            id="deliveryMsg"
            required
            class="form-control"
            placeholder="배송 메세지 입력"
            name="deliveryMsg"
          />
        </div>
      </div>
    </div>

    <!-- {/* 배송지 끝 */} {/* 버튼 시작 */} -->
    <div class="row d-flex justify-content-end">
      <!-- {/* 취소 버튼 시작 */} -->
      <button type="button" class="btn btn-danger w-25 me-3" @click="cancelOrder">
        주문 취소
      </button>
      <!-- {/* 취소 버튼 끝 */} {/* 결재 버튼 시작 */} -->
      <button type="button" class="btn btn-warning w-25" @click="goApproval">결제</button>
      <!-- {/* 결재 버튼 끝 */} -->
    </div>
    <!-- {/* 버튼 끝 */} -->
  </div>
</template>
<script>
import SimpleCartService from "@/services/shop/simple-product/SimpleCartService";
import SimpleOrderService from "@/services/shop/simple-product/SimpleOrderService";
export default {
  data() {
    return {
      simpleCart: [],
      searchTitle: "",
      // 배송 입력 속성 정의
      deliveryAmount: 3000, // 배송비
      deliveryAddr: "", // 배송지 주소
      deliveryMsg: "",
      // 공통 페이징 속성
      page: 1, // 현재페이지번호
      count: 0, // 전체 데이터개수
      pageSize: 3, // 화면에 보여질 개수

      pageSizes: [3, 6, 9], // 화면에 보여질 개수배열
    };
  },
  methods: {
    // 장바구니 전체조회
    async retrieveSimpleCart() {
      try {
        let response = await SimpleCartService.getAll(
          this.searchTitle,
          this.page - 1,
          this.pageSize
        );
        const { simpleCart, totalItems } = response.data;
        this.simpleCart = simpleCart; // spring 전달 객체배열
        this.count = totalItems; // 전체페이지개수
        console.log(response.data);
      } catch (error) {
        console.log(error);
      }
    },

    // 페이지함수
    pageSizeChange() {
      this.page = 1; // 현재페이지번호 : 1
      this.retrieveSimpleCart(); // 재조회
    },

    // 주문 함수
    // 1) 주문 테이블 + 주문상세 테이블 insert
    // 2) 결제 페이지로 이동
    async goApproval(){
        // 1) 주문날짜 : 현재날짜
        let now = new Date();
        let formatNow = `${now.getFullYear()}-${now.getMonth()}-${now.getDate()} ${now.getHours()}:${now.getMinutes()}:${now.getSeconds()}`;
        // 2) 장바구니의 상품별 금액 = 단가 * 개수, 모든 상품 총 금액 = reduce함수를 이용한다. 내부적으로 반복문을 사용해서 배열의 매개 변수의 누적합을 구해준다.
        let totalPrice = this.simpleCart
                    .map((data)=>data.unitPrice*data.cartCount) // 상품별 금액 배열
                    .reduce((acc,cur)=>acc+cur);


           // 3) 주문상세 객체 정의 => 배열이 되어야함
        //       => 예) 주문번호(1) -> 주문상태(50001) : 주문(부모)
        //       =>     주문번호(1) -> 상품명(연필) , 상품이미지, 장바구니개수(5) 
        //       =>     주문번호(1) -> 상품명(샤프) , 상품이미지, 장바구니개수(1) 
        //       =>     주문번호(1) -> 상품명(지우개) , 상품이미지, 장바구니개수(2) 

         let simpleOrderDetail = {
            sono: null, // 주문 번호
            spno: 0, // 상품 번호
            productCount:0 // 장바구니 개수
         }
         let simpleOrderDetailList=[];

         // 주문 상세 배열 만들기
         // 반목문 : js의 향상된 for 문
         for (const data of this.simpleCart) {
            simpleOrderDetail.spno = data.spno; // 상품번호
            simpleOrderDetail.productCount = data.cartCount; // 상품개수
            simpleOrderDetailList.push(simpleOrderDetail);
         }

         // 4) 백엔드로 만든simpleOrderDetailList 배열 저장하기
         try {
            let data = { // 임시 주문 객체 : 주문 상세 객체 배열 속성이 있음
                simpleOrderDetailList: simpleOrderDetailList,  // 주문상세 객체배열 (주문상세 테이블 insert)
                orderDate: formatNow,                          // 주문 날짜 시간
                orderStatus: 50001,                            // 주문상태(50001: 주문완료 50002:결제완료 50011:결제 취소)
                productAmount: totalPrice,                     // 총 사품 금액
                deliveryAmount: this.deliveryAmount,           // 배달비
                orderAmount: totalPrice + this.deliveryAmount, // 주문 금액
                deliveryMsg: this.deliveryMsg                  // 배달 메세지
            }
            let response = await SimpleOrderService.create(data);
            console.log(response.data);
            // 결제 페이지로 이동
            this.$router.push("/simple-approval/" + response.data.sono);
         } catch (e) {
            console.log(e)
         }
    },

    // 주문취소 함수 = 장바구니 페이지로 이동
    cancelOrder(){
        this.$router.push("/simple-cart")
    },

   
  },
  mounted() {
        this.retrieveSimpleCart();
  },
};
</script>
<style></style>

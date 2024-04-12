<template>
  <div>
    <!-- {/* dname start */} -->
    <div class="row mb-5 justify-content-center">
      <!-- {/* w-50 : 크기 조정, mx-auto : 중앙정렬(margin: 0 auto), justify-content-center */} -->
      <div class="col-12 w-50 input-group mb-3">
        <input
          type="text"
          class="form-control"
          placeholder="Search by Title"
          v-model="searchTitle"
        />
        <div class="input-group-append">
          <button
            class="btn btn-outline-secondary"
            type="button"
            @click="retrieveSimpleCart"
          >
            Search
          </button>
        </div>
      </div>
    </div>
    <!-- {/* dname end */} -->

    <!-- {/* paging 시작 */} -->
    <div  class="col-12 w-25 mb-3">
      Items per Page:
      <select
        class="form-select form-select-sm"
        v-model="pageSize"
        @change="pageSizeChange"
      >
        <option v-for="(data, index) in pageSizes" :key="index" :value="data">
          {{ data }}
        </option>
      </select>
    </div>

    <b-pagination
      v-model="page"
      :total-rows="count"
      :per-page="pageSize"
      @click="retrieveSimpleCart"
    ></b-pagination>
    <!-- {/* paging 끝 */} -->

    <div class="row" v-for="(data, index) in simpleCart" :key="index">
      <div class="card mb-3">
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
              <h5 class="card-title">상품명 : {{ data.title }}</h5>
              <h5 class="card-title">가격 : {{ data.unitPrice }}</h5>
              <h5 class="card-title">상품수량 : {{ data.cartCount }}</h5>
              <div class="mt-3">
                <!-- {/* 삭제 버튼 시작 */} -->
                <button
                  type="button"
                  class="btn btn-danger w-25"
                  @click="deleteSimpleCart(data.scno)"
                >
                  장바구니에서 삭제
                </button>
                <!-- {/* 삭제 버튼 끝 */} -->
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
    <div v-if="simpleCart" class="row d-flex justify-content-end">
      <!-- {/* 주문 버튼 시작 */} -->
      <button type="button" class="btn btn-warning w-25" @click="goOrder">
        주문하기
      </button>
      <!-- {/* 주문 버튼 끝 */} -->
    </div>
  </div>
</template>
<script>
import CartService from "@/services/shop/simple-product/SimpleCartService";
export default {
  data() {
    return {
      simpleCart: [],
      searchTitle: "",
      // 공통 페이징 속성
      page: 1, // 현재페이지번호
      count: 0, // 전체 데이터개수
      pageSize: 3, // 화면에 보여질 개수

      pageSizes: [3, 6, 9], // 화면에 보여질 개수배열
    };
  },
  methods: {
    // 장바구니 전체조회 함수
    async retrieveSimpleCart() {
      try {
        let response = await CartService.getAll(
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
    // 장바구니 삭제 함수
    async deleteSimpleCart(scno) {
        try {
          let response = await CartService.remove(scno); 
            console.log(response.data);
            alert("정상적으로 삭제되었습니다.");
            this.retrieveSimpleCart();
        } catch (e) {
            console.log(e);
        }
    },
    // 주문페이지 이동 함수
    goOrder() {
        this.$router.push("/simple-order")
    },
    // 공통페이지 함수
    pageSizeChange() {
      this.page = 1; // 현재페이지번호 : 1
      this.retrieveSimpleCart(); // 재조회
    },
    
  },
  mounted() {
    this.retrieveSimpleCart();
  },
};
</script>
<style></style>

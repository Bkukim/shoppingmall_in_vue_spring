<template>
    <div class="mt-5">
      <div v-if="simpleProduct" class="card mb-3">
        <div class="row g-0">
          <div class="col-md-4">
            <img class="img-fluid rounded-start" alt="..." :src="product.image"/>
          </div>
          <div class="col-md-8">
            <div class="card-body">
              <!-- 상품명 -->
              <h5 class="card-title">{{product.pname}}</h5>
              <h5 class="card-title">₩{{product.unitPrice}}</h5>
              <p class="card-text">
                영원한 아이콘인 {{product.pname}}으로 스타일링 해보세요.
                <br />
                또한, 4계절을 함께 할 {{product.pname}}으로 여러분의 OOTD 를 표현해 보세요.
              </p>
  
              <div
                class="btn-group col"
                role="group"
                aria-label="Basic outlined example"
              ><!-- 장바구니 개수 감소 버튼 -->
                <button
                  type="button"
                  class="btn btn-outline-secondary opacity-50"
                  @click="decreaseCount"
                >
                  -
                </button>
  
                <button
                  type="button"
                  class="btn btn-outline-dark"
                  disabled
                >{{cartCount}}</button>
  
                <button
                  type="button"
                  class="btn btn-outline-secondary opacity-50"
                  @click="increaseCount"
                >
                  +
                </button>
              </div>
  
              <div class="mt-3">
                <button type="submit" class="btn btn-primary w-25" @click="saveProduct">
                  장바구니에 추가
                </button>
  
                <button type="submit" class="btn btn-success w-25 ms-2" @click="goCart">
                  장바구니 바로가기
                </button>
              </div>
  
              <div class="mt-3">
                <button type="button" class="btn btn-warning w-25" @click="goOrder">
                  주문하기
                </button>
              </div>
  
              <p v-if="message" class="alert alert-success mt-3 text-center">
                {{ message }}
              </p>
            </div>
          </div>
        </div>
      </div>
    </div>
  </template>
<script>
export default {
    data() {
        return {
            product:{},
            message:"",
            cartCount: 0,
        }
    },
    methods: {
        getProduct(){},
        saveProduct(){},
        goCart(){},
        goOrder(){},
        increaseCount(){
            this.cartCount++;
        },
        decreaseCount(){
            if (this.cartCount>0) {
            this.cartCount--;   
            }
        }
    },
    mounted() {
        
    },
}
</script>
<style >
    
</style>
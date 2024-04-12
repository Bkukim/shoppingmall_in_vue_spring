import { createRouter, createWebHistory } from 'vue-router'

const routes = [
  {
    path: '/',
    component : () => import('../views/HomeView.vue')
  },
    {
    path: '/simple-product',
    component : () => import('../views/simple-product/SimpleProductList.vue')
  },
  // 장바구니 상세 화면
  {
    path: '/simple-cart/:spno',
    component : () => import('../views/simple-product/SimpleCartDetail.vue')
  },
  {
    path: '/simple-order',
    component : () => import('../views/simple-product/SimpleOrderList.vue')
  },
  {
    path: '/product',
    component : () => import('../views/product/ProductList.vue')
  },
  {
    path: '/cart/:pno',
    component : () => import('../views/product/CartDetail.vue')
  }, 
  // 장바구니 전체 페이지
  {
    path: '/simple-cart',
    component : () => import('../views/simple-product/SimpleCartList.vue')
  },
  // 결제 페이지
  {
    path: '/simple-approval/:sono',
    component : () => import('../views/simple-product/SimpleApprovalList.vue')
  },
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router

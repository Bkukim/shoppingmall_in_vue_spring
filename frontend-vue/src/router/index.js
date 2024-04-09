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
  {
    path: '/simple-cart/:spno',
    component : () => import('../views/simple-product/SimpleCartDetail.vue')
  }, 
  {
    path: '/product',
    component : () => import('../views/product/ProductList.vue')
  },
  {
    path: '/cart/:pno',
    component : () => import('../views/product/CartDetail.vue')
  }, 
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router

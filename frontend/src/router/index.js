import { createRouter, createWebHistory } from 'vue-router'
import ViewBooks from '../views/ViewBooks.vue'
import Login from '../views/Login.vue'



const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'home',
      component: Login
    },
    {
      path: '/editbook/:id',
      name: 'editbook',
      component: () => import('../views/UpdateBook.vue')
    },
    {
      path: '/addbook',
      name: 'addbook',
      component: () => import('../views/AddBook.vue')
    },
    {
      path: '/addloan',
      name: 'addloan',
      component: () => import('../views/AddLoan.vue')
    },
    {
      path: '/loans',
      name: 'viewLoans',
      component: () => import('../views/ViewLoans.vue')
    },
    {
      path: '/editloan/:idLoan',
      name: 'returnLoan',
      component: () => import('../views/ReturnLoan.vue')
    },
    {
      path: '/login',
      name: 'login',
      component: () => import('../views/Login.vue')
    },
    {
      path: '/users',
      name: 'viewUsers',
      component: () => import('../views/ViewUsers.vue')
    },
    {
      path: '/adduser',
      name: 'adduser',
      component: () => import('../views/AddUser.vue')
    },
    {
      path: '/books',
      name: 'viewBooks',
      component: () => import('../views/ViewBooks.vue')
    }
    
  ]
})


export default router

import Vue from 'vue'
import VueRouter from 'vue-router'
//同步
//import HomeView from '../views/HomeView.vue'
//import myRouter from '../views/myRouter.vue'
Vue.use(VueRouter)
//异步
const LoginPage = () => import('../views/LoginPage.vue')
const RegisterPage = () => import('../views/RegisterPage.vue')

const mainPage = () => import('../views/mainPage.vue')
const PersonalPage = () => import('../views/Personal/index.vue')
const ManageUserPage = () => import('../views/User/index.vue')
const ManageStorePage = () => import('../views/Store/index.vue')
const ManageOrderPage = () => import('../views/Order/user.vue')
const ManageOrderPage2 = () => import('../views/Order/store.vue')
const ShopcartPage = () => import('../views/Order/shopcart.vue')

const ShoppingPage = () => import('../views/Shopping/index.vue')
const MainShopping = () => import('../views/Shopping/main.vue')
const StoreShopping = () => import('../views/Shopping/store.vue')
const GoodsShopping = () => import('../views/Shopping/goods.vue')

const Business = () => import('../views/Business/index.vue')
const Categories = () => import('../views/Categories/index.vue')




// const about = () => import(/* webpackChunkName: "about" */ '../views/AboutView.vue')
// const myRouter = () => import('../views/myRouter.vue')
// const routerLogin= () => import('../views/routerLogin.vue')
// const routerRegister = () => import('../views/routerRegister.vue')
// const myComponent = () => import('../views/myComponent.vue')
// const myDirective = () => import('../views/myDirective.vue')
const dictype = () => import('../views/dictype/index')
// const myMixin = () => import('../views/myMixin')
// const vuex = () => import('../views/vuex')



const routes = [
  {
    path: '/',
    redirect: '/LoginPage'
  },
  {
    path: '/LoginPage',
    name: 'LoginPage',
    component: LoginPage
  },
  {
    path: '/RegisterPage',
    name: 'RegisterPage',
    component: RegisterPage
  },
  {
    path: '/ShoppingPage',
    component: ShoppingPage,
    children: [
      {
        path: '/',
        component: MainShopping,
      },
      {
        path: 'MainShopping',
        name: 'MainShopping',
        component: MainShopping,
      },
      {
        path: 'StoreShopping',
        name: 'StoreShopping',
        component: StoreShopping,
      },
      {
        path: 'GoodsShopping',
        name: 'GoodsShopping',
        component: GoodsShopping,
      },
    ]
  },
  {
    path: '/mainPage',
    //name: 'mainPage',
    component: mainPage,
    children: [
      {
        path: '/',
        component: PersonalPage,
      },
      {
        path: 'PersonalPage',
        name: 'PersonalPage',
        component: PersonalPage,
      },
      {
        path: 'ManageUserPage',
        name: 'ManageUserPage',
        component: ManageUserPage,
      },
      {
        path: 'ManageStorePage',
        name: 'ManageStorePage',
        component: ManageStorePage,
      },
      {
        path: 'ManageOrderPage',
        name: 'ManageOrderPage',
        component: ManageOrderPage,
      },
      {
        path: 'ManageOrderPage2',
        name: 'ManageOrderPage2',
        component: ManageOrderPage2,
      },
      {
        path: 'ShopcartPage',
        name: 'ShopcartPagee',
        component: ShopcartPage,
      },
      {
        path: 'dictype',
        name: 'dictype',
        component: dictype,
      },
      {
        path: 'Business',
        name: 'Business',
        component: Business,
        },
        {
            path: 'Categories',
            name: 'Categories',
            component: Categories,
          },
    ]
  },

  // {
  //   path: '/myDirective',
  //   name: 'myDirective',
  //   component: myDirective
  // },
  // {
  //   path: '/myComponent',
  //   name: 'myComponent',
  //   component: myComponent
  // },
  // {
  //   path: '/about',
  //   name: 'about',
  //   alias:'/About',//path别名
  //   //redirect:"/",//内容变URL没变
  //   // route level code-splitting
  //   // this generates a separate chunk (about.[hash].js) for this route
  //   // which is lazy-loaded when the route is visited.
  //   component: about
  // },
  // {
  //   path: '/chapter3',
  //   name: 'chapter3',
  //   component: () => import('../views/Chapter3View.vue')
  // },
  // {
  //   path: '/myAxios',
  //   name: 'myAxios',
  //   component: () => import('../views/myAxios.vue')
  // },
  // {
  //   path: '/myAxios2',
  //   name: 'myAxios2',
  //   component: () => import('../views/myAxios2.vue')
  // },
  // {
  //   path: '/myPromise',
  //   name: 'myPromise',
  //   component: () => import('../views/myPromise.vue')
  // },
  // {
  //   path: '/myLogin',
  //   name: 'myLogin',
  //   component: () => import('../views/myLogin.vue')
  // },
  // {
  //   path: '/myRouter',//?可不传
  //   component: myRouter,
  //   children:[{
  //     path:'/',
  //     name: 'routerHome',
  //     component: routerLogin,
  //   },{
  //     path:'routerLogin',
  //     name: 'routerLogin',
  //     component: routerLogin,
  //   },{
  //     path:'routerRegister',
  //     name: 'routerRegister',
  //     component: routerRegister,
  //   }]
  // },
  {
    path: '/dictype',
    name: 'dictype',
    component: dictype,
  },
  // {
  //   path:'/myMixin',
  //   name: 'myMixin',
  //   component: myMixin,
  // },
  // {
  //   path:'/vuex',
  //   name: 'vuex',
  //   component: vuex,
  // },
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})


// router.beforeEach((to,from,next)=>{
//   // if(to.path=='login'||to.path=='register'){
//   //   next()
//   // }else{
//   //   let user = getStore("user")
//   //   if(user exit){
//   //     next()
//   //   }else{
//   //     this.$router.push("/logim")
//   //     next()
//   //   }
//   // }
//   console.log(to)
//   console.log(from)
//   next()
// })

// router.afterEach((to, from) => {

//   // console.log(to)
//   // console.log(from)
// });

export default router

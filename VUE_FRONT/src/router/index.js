import Vue from 'vue'
import VueRouter from 'vue-router'
import store from "@/store";
import Main from '../views/houtai/Main'
import Home from "@/views/houtai/HomeView";
import Index from "@/views/houtai/user/Index";
import Person from "../views/houtai/user/Person"
import Password from "../views/houtai/user/Password"
import Update from "@/views/houtai/user/Update";
import GoodsInfo from "@/views/houtai/goods/GoodsInfo";
import GoodsUpdate from "@/views/houtai/goods/GoodsUpdate";
import GoodsAdd from "@/views/houtai/goods/GoodsAdd";
import GoodsType from "../views/houtai/goodstype/Goodstype"
import GoodsTypeAdd from "../views/houtai/goodstype/GoodTypeAdd"
import GoodsTypeUpdate from "../views/houtai/goodstype/GoodTypeUpdate"
import CartInfo from "@/views/houtai/Cart/CartInfo";
import OrderInfo from "@/views/houtai/Order/OrderInfo";
import AddressInfo from "@/views/houtai/Address/AddressInfo";
import Register from "@/views/houtai/user/Register";
import Login from "@/views/houtai/user/Login";
import AddressAdd from "@/views/houtai/Address/AddressAdd";
import CollectInfo from "@/views/houtai/Collect/CollectInfo";
import CollectAdd from "@/views/houtai/Collect/CollectAdd";
import CollectUpdate from "@/views/houtai/Collect/CollectUpdate";
import Test from "@/views/houtai/Test";
import OrderAdd from "@/views/houtai/Order/OrderAdd";
import Orders from "@/views/houtai/Order/Orders";
import AddressUpdate from "@/views/houtai/Address/AddressUpdate";


Vue.use(VueRouter);

const routes = [
  {
    path: '/',
    name: 'FrontMain',
    component: () => import('../views/FrontMain.vue'),
    redirect:"/home",
    children:[
      {path: "/home",name:'首页',component:() => import('../views/Home.vue')},
      {path: '/error', name: 'Error', component: () => import('../components/Error.vue')},
      {path: '/goods', name: 'Goods', component: () => import('../views/Goods.vue')},
      {path: '/about', name: 'About', component: () => import('../views/About.vue')},
      {path: '/userInfo', name: 'UserInfo', component: () => import('../components/UserInfo.vue')},
      {path: '/recvAddress', name: '收货地址', component: () => import('../components/RecvAddress.vue')},
      {path: '/recvAddressList', name: '收货地址管理', component: () => import('../components/RecvAddressList.vue')},
      {path: '/goods/details', name: 'Details', component: () => import('../views/Details.vue')},
      {path: '/shoppingCart', name: 'ShoppingCart', component: () => import('../views/ShoppingCart.vue'), meta: {requireAuth: true }},
      {path: '/collect', name: 'Collect', component: () => import('../views/Collect.vue'), meta: {requireAuth: true }},
      {path: '/order', name: 'Order', component: () => import('../views/Order.vue'), meta: {requireAuth: true }},
      {path: '/confirmOrder', name: 'ConfirmOrder', component: () => import('../views/ConfirmOrder.vue'), meta: {requireAuth: true}},
      {path: '/GoodComment', name: 'GoodComment', component: () => import('../components/GoodComment'), meta: {requireAuth: true}}

    ]
  },
  {path: "/main/reg",name:'用户注册',component:Register},
  {path: "/main/login",name:'用户登录',component:Login},
  {
    path: '/main',
    component:Main,
    redirect:"/main/login",
    children:[
      {path: "/main/home",name:'首页',component:Home},
      {path: "/main/index",name:'用户列表',component:Index},
      {path: "/main/person",name:'用户详细信息',component:Person},
      {path: "/main/password",name:'修改用户密码',component:Password},
      {path: "/main/update",name:'用户信息修改',component:Update},
      {path: "/main/goodsInfo",name:'商品信息列表',component:GoodsInfo},
      {path: "/main/goodsUpdate",name:'商品信息修改',component:GoodsUpdate},
      {path: "/main/goodsAdd",name:'商品增加',component:GoodsAdd},
      {path: "/main/goodsType",name:'商品类别信息',component:GoodsType},
      {path: "/main/goodsTypeAdd",name:'商品类别增加',component:GoodsTypeAdd},
      {path: "/main/goodsTypeUpdate",name:'商品类别修改',component:GoodsTypeUpdate},
      {path: "/main/cart",name:'购物车信息列表',component:CartInfo},
      {path: "/main/orders",name:'订单列表',component:Orders},
      {path: "/main/orderItem",name:'订单信息列表',component:OrderInfo},
      {path: "/main/orderAdd",name:'新增订单信息',component:OrderAdd},
      {path: "/main/address",name:'收货地址信息列表',component:AddressInfo},
      {path: "/main/addressAdd",name:'新增收货地址',component:AddressAdd},
      {path: "/main/addressUpdate",name:'修改收货地址',component:AddressUpdate},
      {path: "/main/collectInfo",name:'商品收藏数据',component:CollectInfo},
      {path: "/main/collectAdd",name:'新增收藏',component:CollectAdd},
      {path: "/main/collectUpdate",name:'修改收藏数据',component:CollectUpdate},
      {path: "/main/test",name:'测试图片上传',component:Test},
    ]
  },

];

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
});

router.beforeEach((to, from, next) => {
  localStorage.setItem("currentPathName", to.name);  // 设置当前的路由名称
  store.commit("setPath");
  next()
});

export default router

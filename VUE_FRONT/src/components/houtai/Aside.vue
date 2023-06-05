<template>
    <div >
        <el-menu
                :default-openeds="['1','3']"
                style="min-height: 800px; overflow-x: hidden"
                background-color="rgb(48, 65, 86)"
                text-color="#fff"
                active-text-color="#ffd04b"
                 >
<!--        default-active="2"    class="el-menu-vertical-demo"    @open="handleOpen" @close="handleClose"-->
            <div style="height: 60px; line-height: 60px; text-align: center">
                <img src="../../assets/logo.png" alt="" style="width: 20px; position: relative; top: 5px;">
                <b style="color: white; margin-left: 5px" v-show="true">后台管理系统</b>
            </div>
            <el-submenu index="1">
                <template slot="title">
                    <i class="el-icon-user-solid"></i>
                    <span>用户管理</span>
                </template>
                <el-menu-item-group>
                    <el-menu-item index="1-1" @click="handleUserInfo()">用户信息管理</el-menu-item>
                </el-menu-item-group>
            </el-submenu>
            <el-submenu index="2">
                <template slot="title">
                    <i class="el-icon-menu"></i>
                    <span>商品管理</span>
                </template>
                <el-menu-item-group>
                    <el-menu-item index="2-1" @click="handleGoodsInfo()">商品信息管理</el-menu-item>
                    <el-menu-item index="2-2" @click="handleGoodsTypeInfo()">商品类型管理</el-menu-item>
                </el-menu-item-group>
            </el-submenu>
            <el-submenu index="3">
            <template slot="title">
                <i class="el-icon-tickets"></i>
                <span>订单管理</span>
            </template>
            <el-menu-item-group>
                <el-menu-item index="3-1" @click="handleOrderInfo()">订单管理</el-menu-item>
                <el-menu-item index="3-2" @click="handleOrderItem()">订单信息管理</el-menu-item>
            </el-menu-item-group>
        </el-submenu>
            <el-submenu index="4">
                <template slot="title">
                    <i class="el-icon-location"></i>
                    <span>地址管理</span>
                </template>
                <el-menu-item-group>
                    <el-menu-item index="4-1" @click="handleAddressInfo()">收货地址信息管理</el-menu-item>
                </el-menu-item-group>
            </el-submenu>
            <el-submenu index="5">
                <template slot="title">
                    <i class="el-icon-shopping-cart-full"></i>
                    <span>购物车管理</span>
                </template>
                <el-menu-item-group>
                    <el-menu-item index="5-1" @click="handleCartInfo()">购物车信息管理</el-menu-item>
                </el-menu-item-group>
            </el-submenu>
            <el-submenu index="6">
                <template slot="title">
                    <i class="el-icon-star-on"></i>
                    <span>收藏管理</span>
                </template>
                <el-menu-item-group>
                    <el-menu-item index="6-1" @click="handleCollectInfo()">收藏信息管理</el-menu-item>
                </el-menu-item-group>
            </el-submenu>
            <el-menu-item index="7">
                <i class="el-icon-setting"></i>
                <span slot="title">其他管理</span>
            </el-menu-item>
        </el-menu>
    </div>
</template>

<script>
    export default {
        name: "Aside",
        props: {
            isCollapse: Boolean,
            logoTextShow: Boolean
        },
        methods:{
            handleUserInfo(){
                this.$router.push('/main/index')
            },
            handleGoodsInfo(){
                this.$router.push('/main/goodsInfo')
            },
            handleGoodsTypeInfo(){
                this.$router.push('/main/goodsType')
            },
            handleCartInfo(){
              this.$router.push('/main/cart')
            },
            handleOrderInfo(){
                this.$router.push('/main/orders')
            },
            handleOrderItem(){
                this.$router.push('/main/orderItem')
            },
            handleAddressInfo(){
                this.$router.push('/main/address')
            },
            handleCollectInfo(){
                this.$router.push('/main/collectInfo')
            }
        }
    }
</script>

<style scoped>
    .el-menu-item.is-active {
        background-color: rgb(38, 52, 69) !important;
    }
    .el-menu-item:hover {
        background-color: rgb(38, 52, 69) !important;
    }

    .el-submenu__title:hover {
        background-color: rgb(38, 52, 69) !important;
    }
    /*解决收缩菜单文字不消失问题*/
    .el-menu--collapse span {
        visibility: hidden;
    }
</style>
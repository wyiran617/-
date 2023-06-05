<template>
    <el-container style="min-height: 100vh;" >
        <el-aside :width="sideWidth + 'px'" style="box-shadow: 2px 0 6px rgb(0 21 41 / 35%);background-color: rgb(48, 65, 86);">
            <Aside :isCollapse="isCollapse" :logoTextShow="logoTextShow" style="padding-bottom: 20px" />
        </el-aside>
        <el-container>
            <el-header style="border-bottom: 1px solid #ccc;">
                <Header :collapseBtnClass="collapseBtnClass" @asideCollapse="collapse" />
            </el-header>
            <el-main>
                <!--        表示当前页面的子路由会在 <router-view /> 里面展示-->
                <router-view/>
            </el-main>
        </el-container>

    </el-container>
</template>

<script>

    import Aside from "../../components/houtai/Aside";
    import Header from "../../components/houtai/Header";
    import {mapActions, mapGetters} from "vuex";
    export default {
        name: "Main",
        data() {
            return {
                collapseBtnClass: 'el-icon-s-fold',
                isCollapse: false,
                sideWidth: 200,
                logoTextShow: true,
            }
        },
        components: {
            Aside,
            Header
        },
        computed:{
            ...mapGetters(["getUser"])
        },
        created() {
            // 获取浏览器localStorage，判断用户是否已经登录
            if (localStorage.getItem("user")) {
                // 如果已经登录，设置vuex登录状态
                this.setUser(JSON.parse(localStorage.getItem("user")));
            }
        },
        methods: {
            ...mapActions(["setUser"]),
            collapse() {  // 点击收缩按钮触发
                this.isCollapse = !this.isCollapse
                if (this.isCollapse) {  // 收缩
                    this.sideWidth = 64
                    this.collapseBtnClass = 'el-icon-s-unfold'
                    this.logoTextShow = false
                } else {   // 展开
                    this.sideWidth = 200
                    this.collapseBtnClass = 'el-icon-s-fold'
                    this.logoTextShow = true
                }
            },
        }

    }
</script>


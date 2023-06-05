<template>
    <div style="line-height: 60px; display: flex">
        <div style="flex: 1;">
            <span :class="collapseBtnClass" style="cursor: pointer; font-size: 18px" @click="collapse"></span>

            <el-breadcrumb separator="/" style="display: inline-block; margin-left: 10px">
                <el-breadcrumb-item :to="'/main'">首页</el-breadcrumb-item>
                <el-breadcrumb-item>{{ currentPathName }}</el-breadcrumb-item>
            </el-breadcrumb>
        </div>
        <el-dropdown style="width: 150px; cursor: pointer; text-align: right">
            <div style="display: inline-block">
                <img :src="'http://localhost'+this.$store.getters.getUser.avatar" alt=""
                     style="width: 30px; border-radius: 50%; position: relative; top: 10px; right: 5px">
                <span>{{ this.$store.getters.getUser.username }}</span><i class="el-icon-arrow-down" style="margin-left: 5px"></i>
            </div>
            <el-dropdown-menu slot="dropdown" style="width: 100px; text-align: center">
                <el-dropdown-item style="font-size: 14px; padding: 5px 0">
                    <span @click="toEditPssWord">修改密码</span>
                </el-dropdown-item>
                <el-dropdown-item style="font-size: 14px; padding: 5px 0">
                    <span @click="toPerson">个人信息</span>
                </el-dropdown-item>
                <el-dropdown-item style="font-size: 14px; padding: 5px 0">
                    <span style="text-decoration: none" @click="logout">退出</span>
                </el-dropdown-item>
            </el-dropdown-menu>
        </el-dropdown>
    </div>
</template>

<script>
    import { mapGetters } from "vuex";
    export default {
        name: "Header",
        props: {
            collapseBtnClass: String,
            avatarUrl:String,
        },
        computed: {
            ...mapGetters(["getUser"]),
            currentPathName () {
                return this.$store.state.currentPathName;　　//需要监听的数据
            },
        },
        watch: {
            currentPathName (newVal,oldVal){
                console.log(newVal)
            }
        },
        data() {
            return {

            }
        },

        methods: {
            collapse() {
                // this.$parent.$parent.$parent.$parent.collapse()  // 通过4个 $parent 找到父组件，从而调用其折叠方法
                this.$emit("asideCollapse")
            },
            toEditPssWord(){
                this.$router.push('/main/password?uid='+this.$store.getters.getUser.uid)
            },
            toPerson(){
                this.$router.push('/main/person?uid='+this.$store.getters.getUser.uid)
            },
            logout() {
                this.$store.commit("logout");
                this.$message.success("退出成功")
            }
        }
    }
</script>

<style>
    /*.el-dropdown-link {*/
    /*    cursor: pointer;*/
    /*    color: #409EFF;*/
    /*}*/
    /*.el-icon-arrow-down {*/
    /*    font-size: 12px;*/
    /*}*/
</style>
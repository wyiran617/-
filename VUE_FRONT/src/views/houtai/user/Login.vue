<template>
    <div class="wrapper">
        <div style="margin: 200px auto; background-color: #fff; width: 350px; height: 280px; padding: 20px; border-radius: 10px">
            <div style="margin: 20px 0; text-align: center; font-size: 24px"><b>欢迎登录校园购物系统</b></div>
            <el-form :model="user" :rules="rules" ref="userForm">
                <el-form-item prop="username">
                    <el-input size="medium" prefix-icon="el-icon-user" v-model="user.username"></el-input>
                </el-form-item>
                <el-form-item prop="password">
                    <el-input size="medium" prefix-icon="el-icon-lock" show-password v-model="user.password"></el-input>
                </el-form-item>
                <el-form-item style="margin: 10px 0; text-align: right">
                    <el-button type="warning" size="small" autocomplete="off" @click="$router.push('/main/reg')">注册</el-button>
                    <el-button type="primary" size="small" autocomplete="off" @click="login">登录</el-button>
                </el-form-item>
            </el-form>
        </div>
    </div>
</template>

<script>
    import { mapActions } from "vuex";
    export default {
        name: "Login",
        data() {
            return{
                user:{},
                rules:{
                    username: [
                        {required: true, message: '请输入用户名', trigger: 'blur'},
                        {min: 3, max: 10, message: '长度在 3 到 5 个字符', trigger: 'blur'}
                    ],
                    password: [
                        {required: true, message: '请输入密码', trigger: 'blur'},
                        {min: 1, max: 20, message: '长度在 1 到 20 个字符', trigger: 'blur'}
                    ],
                }
            }
        },
        methods:{
            ...mapActions(["setUser"]),
            login(){
                this.$refs['userForm'].validate((valid) => {
                    if (valid) {
                        axios.post('http://localhost/user/login',this.user).then(function (resp) {
                            console.log(resp);
                            if(resp.data.code==="200"){
                                // 登录信息存到本地
                                let user = JSON.stringify(resp.data.data);
                                localStorage.setItem("user", user);
                                this.setUser(user);
                                this.$message.success("登录成功!")
                                this.$router.push('/main/home')
                            }else{
                                this.$message.error("用户名密码错误，请重新登录!")
                            }
                        }.bind(this))
                    } else {
                        console.log('error submit!!');
                        return false;
                    }
                });
            }
        }

    }
</script>

<style scoped>
 .wrapper{
     height: 100vh;
     background-image: linear-gradient(to bottom right,#FC4668,#3F5EF8);
     overflow: hidden;
 }
</style>
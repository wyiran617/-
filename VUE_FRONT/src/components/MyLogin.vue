<template>
    <div id="myLogin">
        <el-dialog title="登录" width="300px" center :visible.sync="isLogin">
            <el-form :model="user" :rules="rules" status-icon ref="ruleForm" class="demo-ruleForm">
                <el-form-item prop="username">
                    <el-input prefix-icon="el-icon-user-solid" placeholder="请输入账号" v-model="user.username"></el-input>
                </el-form-item>
                <el-form-item prop="password">
                    <el-input
                            prefix-icon="el-icon-view"
                            type="password"
                            placeholder="请输入密码"
                            v-model="user.password"
                    ></el-input>
                </el-form-item>
                <el-form-item>
                    <el-button size="medium" type="primary" @click="Login" style="width:100%;">登录</el-button>
                </el-form-item>
            </el-form>
        </el-dialog>
    </div>
</template>
<script>
    import { mapActions } from "vuex";

    export default {
        name: "MyLogin",
        data() {
            // 用户名的校验方法
            let validateName = (rule, value, callback) => {
                if (!value) {
                    return callback(new Error("请输入用户名"));
                }
                // 用户名以字母开头,长度在5-16之间,允许字母数字下划线
                const userNameRule = /^[a-zA-Z][a-zA-Z0-9_]{4,15}$/;
                if (userNameRule.test(value)) {
                    this.$refs.ruleForm.validateField("checkPass");
                    return callback();
                } else {
                    return callback(new Error("字母开头,长度5-16之间,允许字母数字下划线"));
                }
            };
            // 密码的校验方法
            let validatePass = (rule, value, callback) => {
                if (value === "") {
                    return callback(new Error("请输入密码"));
                }
                // 密码至少包含数字1到9位,
                const passwordRule = /^(?=.*\d).{1,9}$/;
                if (passwordRule.test(value)) {
                    this.$refs.ruleForm.validateField("checkPass");
                    return callback();
                } else {
                    return callback(
                        new Error("密码至少包含数字1到9位")
                    );
                }
            };
            return {
                user: {
                    username:"",
                    password:""
                },
                // 用户信息校验规则,validator(校验方法),trigger(触发方式),blur为在组件 Input 失去焦点时触发
                rules: {
                    username: [{ validator: validateName, trigger: "blur" }],
                    password: [{ validator: validatePass, trigger: "blur" }]
                }
            };
        },
        computed: {
            // 获取vuex中的showLogin，控制登录组件是否显示
            isLogin: {
                get() {
                    return this.$store.getters.getShowLogin;
                },
                set(val) {
                    this.$refs["ruleForm"].resetFields();
                    this.setShowLogin(val);
                }
            }
        },
        methods: {
            ...mapActions(["setUser", "setShowLogin"]),
            Login() {
                // 通过element自定义表单校验规则，校验用户输入的用户信息
                let _this = this;
                this.$refs["ruleForm"].validate(valid => {
                    //如果通过校验开始登录
                    if (valid) {
                       axios.post("http://localhost/user/login", this.user).then(function (resp) {
                           console.log(resp.data.code);
                               // “200”代表登录成功，其他的均为失败
                               if (resp.data.code==='200') {
                                   // 隐藏登录组件
                                   _this.isLogin = false;
                                   // 登录信息存到本地
                                   let user = JSON.stringify(resp.data.data);
                                   localStorage.setItem("user", user);
                                   console.log(user);
                                   // 登录信息存到vuex
                                   _this.setUser(user);
                                   // 弹出通知框提示登录成功信息
                                   _this.$message.success(resp.data.message);
                                   location.reload()
                               } else {
                                   // 清空输入框的校验状态
                                   // this.$refs["ruleForm"].resetFields();
                                   // 弹出通知框提示登录失败信息
                                   this.notifyError(resp.data.message);
                               }
                       } ).catch(err => {
                                return Promise.reject(err);
                            });
                    } else {
                        return false;
                    }
                });
            }
        }
    };
</script>

<style scoped>

</style>
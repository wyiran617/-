<template>
    <div id="register">
        <el-dialog title="用户注册" width="300px" center :visible.sync="isRegister">
            <el-form
                    ref="ruleForm"
                    :model="form"
                    :rules="rules"
                    status-icon
                    class="demo-ruleForm"
            >
                <el-form-item prop="username">
                    <el-input
                            prefix-icon="el-icon-user-solid"
                            placeholder="请输入账号"
                            v-model="form.username"
                    ></el-input>
                </el-form-item>
                <el-form-item prop="password">
                    <el-input
                            prefix-icon="el-icon-view"
                            type="password"
                            placeholder="请输入密码"
                            v-model="form.password"
                    ></el-input>
                </el-form-item>
                <el-form-item prop="confirmPass">
                    <el-input
                            prefix-icon="el-icon-view"
                            type="password"
                            placeholder="请再次输入密码"
                            v-model="form.confirmPass"
                    ></el-input>
                </el-form-item>
                <el-form-item>
                    <el-button size="medium" type="primary" @click="Register('form')" style="width:100%;">注册</el-button>
                </el-form-item>
            </el-form>
        </el-dialog>
    </div>
</template>

<script>
    export default {
        name: "MyRegister",
        props: ["register"],
        data() {
            // 用户名的校验方法
            let validateName = (rule, value, callback) => {
                if (!value) {
                    return callback(new Error("请输入用户名"));
                }
                // 用户名以字母开头,长度在5-16之间,允许字母数字下划线
                const userNameRule = /^[a-zA-Z][a-zA-Z0-9_]{4,15}$/;
                if (userNameRule.test(value)) {
                    //判断数据库中是否已经存在该用户名
                    fetch('http://localhost/user/username?username='+this.form.username).then(res => res.json().then(res => {
                        console.log("res.data:"+res.data);
                            // “null”代表用户名不存在，可以注册
                            if (res.data=== null) {
                                this.$refs.ruleForm.validateField("checkPass");
                                return callback();
                            } else {
                                return callback(new Error("该用户名已存在"));
                            }
                        }))
                } else {
                    return callback(new Error("字母开头,长度5-16之间,允许字母数字下划线"));
                }
            };
            // 密码的校验方法
            let validatePass = (rule, value, callback) => {
                if (value === "") {
                    return callback(new Error("请输入密码"));
                }
                // 密码包含1到9位数字
                const passwordRule = /^(?=.*\d).{1,9}$/;
                if (passwordRule.test(value)) {
                    this.$refs.ruleForm.validateField("checkPass");
                    return callback();
                } else {
                    return callback(
                        new Error("密码包含1到9位数字")
                    );
                }
            };
            // 确认密码的校验方法
            let validateConfirmPass = (rule, value, callback) => {
                if (value === "") {
                    return callback(new Error("请输入确认密码"));
                }
                // 校验是否以密码一致
                if (this.form.password !== "" && value === this.form.password) {
                    this.$refs.ruleForm.validateField("checkPass");
                    return callback();
                } else {
                    return callback(new Error("两次输入的密码不一致"));
                }
            };
            return {
                isRegister: false, // 控制注册组件是否显示
                form: {
                    uid:"",
                    username: "",
                    password: "",
                    confirmPass: ""
                },
                // 用户信息校验规则,validator(校验方法),trigger(触发方式),blur为在组件 Input 失去焦点时触发
                rules: {
                    username: [{ validator: validateName, trigger: "blur" }],
                    password: [{ validator: validatePass, trigger: "blur" }],
                    confirmPass: [{ validator: validateConfirmPass, trigger: "blur" }]
                }
            };
        },
        watch: {
            // 监听父组件传过来的register变量，设置this.isRegister的值
            register: function(val) {
                if (val) {
                    this.isRegister = val;
                }
            },
            // 监听this.isRegister变量的值，更新父组件register变量的值
            isRegister: function(val) {
                if (!val) {
                    this.$refs["ruleForm"].resetFields();
                    this.$emit("fromChild", val);
                }
            }
        },
        methods: {
            Register() {
                let _this = this;
                this.$refs["ruleForm"].validate(valid => {
                    if (valid) {
                        console.log("_this.form"+_this.form);
                        axios.post('http://localhost/user/reg/', {
                            username: this.form.username,
                            password: this.form.password
                        }).then(function (res)  {
                                // “1”代表注册成功，其他的均为失败
                                if (res.data === 1) {
                                    // 隐藏注册组件
                                    _this.isRegister = false;
                                    // 弹出通知框提示注册成功信息
                                    _this.notifySucceed("新用户注册成功");
                                } else {
                                    // 弹出通知框提示注册失败信息
                                    _this.notifyError("注册失败");
                                }
                            })
                            .catch(err => {
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
<template>
    <div style="width: 600px">
        <el-form ref="form" :rules="rules" :model="form" label-width="80px">
            <el-form-item label="用户名" prop="username">
                <el-input v-model="form.username"></el-input>
            </el-form-item>
            <el-form-item label="密码" prop="password">
                <el-input v-model="form.password"></el-input>
            </el-form-item>
<!--            <el-form-item label="个人信息" prop="proinfo">-->
<!--                <el-input v-model="form.proinfo"></el-input>-->
<!--            </el-form-item>-->
            <el-form-item>
                <el-button type="primary" @click="onSubmit('form')">提交</el-button>
                <el-button @click="toblack()">取消</el-button>
            </el-form-item>
        </el-form>
    </div>
</template>

<script>
    export default {
        name: "Reg",
        data(){
            return{
                form:{
                    uid:'',
                    username:'',
                    password:''
                },
                rules: {
                    username: [
                        { required: true, message: '用户名不能为空', trigger: 'blur' },
                        { min: 3, max: 5, message: '长度在 3 到 5 个字符', trigger: 'blur' }
                    ],
                    password: [
                        { required: true, message: '密码不能为空', trigger: 'blur' },
                        { min: 3, max: 10, message: '长度在 3 到 10 个字符', trigger: 'blur' }
                    ]
                    // proinfo: [
                    //     { required: true, message: '个人信息不能为空', trigger: 'blur' }
                    // ]
                }
            }
        },
        methods:{
            toblack(){
                this.$router.push('/main/index')
            },
            onSubmit(formName){
                let _this = this;
                this.$refs[formName].validate((valid) => {
                    if (valid) {
                        console.log(_this.form);
                        axios.post('http://localhost/user/reg/',this.form).then(function (resp) {
                            if (resp.data>0){
                                _this.$alert(_this.form.name+'已添加成功！', '提示', {
                                    confirmButtonText: '确定',
                                    callback: action => {
                                        _this.$router.push('/main/index')
                                    }
                                });
                            }
                        })
                    }
                });
            }
        }
    }
</script>

<style scoped>

</style>
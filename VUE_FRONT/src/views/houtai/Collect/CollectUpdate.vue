<template>
    <div style="width: 600px">
        <el-form ref="form" :rules="rules" :model="form" label-width="80px">
            <el-form-item label="uid" :rules="[
            {required:true, message:'uid不能为空'},
            {type:'number',message: 'uid必须为number'}
        ]" prop="uid">
                <el-input v-model.number="form.uid" ></el-input>
            </el-form-item>
            <el-form-item label="用户名" prop="username">
                <el-input v-model="form.username"></el-input>
            </el-form-item>
            <el-form-item label="密码" prop="password">
                <el-input v-model="form.password"></el-input>
            </el-form-item>
            <el-form-item>
                <el-button type="primary" @click="onSubmit('form')">提交</el-button>
                <el-button @click="toblack()">取消</el-button>
            </el-form-item>
        </el-form>
    </div>
</template>

<script>
    export default {
        name: "Update",
        created() {
            let _this = this;
            axios.get('http://localhost/user/'+this.$route.query.uid).then(function (resp) {
                _this.form=resp.data
            })
        },
        data(){
            return{
                form:{
                    uid:'',
                    username:'',
                    password:''
                },
                rules: {
                    name: [
                        { required: true, message: '用户名不能为空', trigger: 'blur' },
                        { min: 3, max: 5, message: '长度在 3 到 5 个字符', trigger: 'blur' }
                    ],
                    password: [
                        { required: true, message: '密码不能为空', trigger: 'blur' },
                        { min: 3, max: 10, message: '长度在 3 到 10 个字符', trigger: 'blur' }
                    ]
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
                        axios.put('http://localhost/user/update/',this.form).then(function (resp) {
                            if (resp.data>0){
                                _this.$alert(_this.form.username+'已被修改！', '提示', {
                                    confirmButtonText: '确定',
                                    callback: action => {
                                        _this.$router.push('/index')
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
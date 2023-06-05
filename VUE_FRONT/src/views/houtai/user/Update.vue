<template>
        <el-card style="width: 500px;">
            <el-form ref="form" :rules="rules" :model="form" label-width="80px" size="small">
                <el-form-item label="头像">
                    <el-upload
                            class="avatar-uploader"
                            action="http://localhost/upload/image"
                            :show-file-list="false"
                            :on-success="handleAvatarSuccess"
                    >
                        <img v-if="form.avatar" :src="'http://localhost'+form.avatar" class="avatar">
                        <i v-else class="el-icon-plus avatar-uploader-icon"></i>
                    </el-upload>
                </el-form-item>
                <el-form-item label="用户名">
                    <el-input v-model="form.username"  autocomplete="off"></el-input>
                </el-form-item>
                <el-form-item label="密码">
                    <el-input v-model="form.password"  autocomplete="off"></el-input>
                </el-form-item>
                <el-form-item label="邮箱">
                    <el-input v-model="form.email" autocomplete="off"></el-input>
                </el-form-item>
                <el-form-item label="电话">
                    <el-input v-model="form.phone" autocomplete="off"></el-input>
                </el-form-item>
                <el-form-item>
                    <el-button @click="toBlack">取 消</el-button>
                    <el-button type="primary" @click="onSubmit('form')">确 定</el-button>
                </el-form-item>
            </el-form>
        </el-card>
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
                    password:'',
                    email:'',
                    phone:'',
                    avatar:''
                },
                rules: {
                    name: [
                        { required: true, message: '用户名不能为空', trigger: 'blur' },
                        { min: 3, max: 5, message: '长度在 3 到 5 个字符', trigger: 'blur' }
                    ],
                    password: [
                        { required: true, message: '密码不能为空', trigger: 'blur' },
                        { min: 1, max: 10, message: '长度在 3 到 10 个字符', trigger: 'blur' }
                    ]
                }
            }
        },
        methods:{
            toBlack(){
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
                                        _this.$router.push('/main/index')
                                    }
                                });
                            }
                        })
                    }
                });
            },
            handleAvatarSuccess(res) {
                this.form.avatar = res
            }
        }
    }
</script>

<style scoped>

</style>
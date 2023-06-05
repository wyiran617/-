<template>
    <div class="order">
        <div class="order-header">
            <div class="order-header-content">
                <p>
                    <i class="el-icon-s-order" style="font-size: 30px;color: #ff6700;"></i>
                    个人资料
                </p>
            </div>
        </div>
        <div class="order-content">
            <div class="content">
                <el-card style="width: 600px;margin-left: 25%">
                    <el-form label-width="80px" size="small">
                        <div style="text-align: center; margin: 10px 0">
                            <el-upload
                                    class="avatar-uploader"
                                    action="http://localhost/upload/image"
                                    :show-file-list="false"
                                    :on-success="handleAvatarSuccess"
                            >
                                <img v-if="form.avatar" :src="'http://localhost'+form.avatar" class="avatar">
                                <i v-else class="el-icon-plus avatar-uploader-icon"></i>
                            </el-upload>
                        </div>
                        <el-form-item label="用户名">
                            <el-input v-model="form.username" autocomplete="off" disabled></el-input>
                        </el-form-item>
                        <el-form-item label="邮箱">
                            <el-input v-model="form.email" autocomplete="off"></el-input>
                        </el-form-item>
                        <el-form-item label="电话">
                            <el-input v-model="form.phone" autocomplete="off"></el-input>
                        </el-form-item>
                        <el-form-item label="地址">
                            <el-input v-model="form.address" autocomplete="off">{{this.showSelect.provinceName+this.showSelect.cityName+this.showSelect.areaName+this.showSelect.address}}</el-input>
                        </el-form-item>
                    </el-form>

                    <div style="text-align: center">
                        <el-button type="primary" @click="save">确 定</el-button>
                    </div>
                </el-card>
            </div>
        </div>

    </div>

</template>

<script>
    import {mapActions} from "vuex";

    export default {
        name: "UserInfo",
        data() {
            return {
                showSelect: [],
                options: [],
                form: {},
                user: this.$store.getters.getUser
            }
        },
        created() {
            this.load();
            let _this = this;
            axios.get('http://localhost/tAddress/allByUid/'+this.user.uid).then(function (resp) {
                _this.options=resp.data;
                _this.showSelect = _this.options[0];
                _this.form.address = _this.showSelect.provinceName+_this.showSelect.cityName+_this.showSelect.areaName+_this.showSelect.address
            })
        },
        methods: {
        ...mapActions(["setUser", "setShowLogin"]),
            load() {
                const username = this.user.username;
                if (!username) {
                    this.$message.error("当前无法获取用户信息！");
                    return
                }
                axios.get('http://localhost/user/username?username='+username).then(res => {
                    this.form = res.data.data
                })
            },
            save() {
               axios.put("http://localhost/user/update", this.form).then(res => {
                    if (res.data  === 1) {
                        this.$message.success("保存成功");
                        let user = JSON.stringify(this.form);
                        localStorage.setItem("user", user);
                        this.setUser(user);
                        this.$emit('refreshUser');
                        clearTimeout(this.timer);  //清除延迟执行
                        this.timer = setTimeout(()=>{   //设置延迟执行
                            window.location.reload();
                        },1000);
                    } else {
                        this.$message.error("保存失败")
                    }
                })
            },
            handleAvatarSuccess(res) {
                // res就是文件的路径
                this.form.avatar = res
            }
        }
    }
</script>

<style scoped>
    .order {
        background-color: #f5f5f5;
        padding-bottom: 20px;
    }
    /* 我的订单头部CSS */
    .order .order-header {
        height: 64px;
        border-bottom: 2px solid #ff6700;
        background-color: #fff;
        margin-bottom: 20px;
    }
    .order .order-header .order-header-content {
        width: 1225px;
        margin: 0 auto;
    }
    .order .order-header p {
        font-size: 28px;
        line-height: 58px;
        float: left;
        font-weight: normal;
        color: #424242;
    }
    /* 我的订单头部CSS END */
    .order .content {
        width: 1225px;
        margin: 0 auto;
        background-color: #fff;
        margin-bottom: 50px;
    }
</style>
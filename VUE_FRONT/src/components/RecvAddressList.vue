<template>
    <div class="order">
        <div class="order-header">
            <div class="order-header-content">
                <p>
                    <i class="el-icon-location-outline" style="font-size: 30px;color: #ff6700;"></i>
                    收货地址管理
                </p>
            </div>
        </div>
        <div class="order-content">
            <div class="content">
                <div class="address-body">
                    <ul>
                        <li :class="confirmAddress ===-1 ? 'in-section add-address' : 'add-address'" @click="toInsertAddress">
                            <i class="el-icon-circle-plus-outline"></i>
                            <p>添加新地址</p>
                        </li>
                        <li
                                v-for="(item,index) in address"
                                :class=" confirmAddress === index ? 'in-section' : ''"
                                :key="item.aid"
                                @click="recvAddress(index)"
                        >
                            <el-popover placement="top">
                                <p>确定删除吗？</p>
                                <div style="text-align: right; margin: 10px 0 0">
                                    <el-button type="primary" size="mini" @click="deleteAddress(item.aid)">确定</el-button>
                                </div>
                                <i class="el-icon-close delete" slot="reference" v-show="true"></i>
                            </el-popover>
                            <h2>收件人：{{item.name}}</h2>
                            <p class="phone">联系电话：{{item.phone}}</p>
                            <p class="address">收货地址：{{item.provinceName+item.areaName+item.address}}</p>
                        </li>
                        <el-dialog
                                title="添加新收货地址信息"
                                :visible.sync="dialogVisible"
                                width="880px"
                                :before-close="handleClose">
                            <RecvAddress />
                            <span slot="footer" class="dialog-footer">
                                <el-button @click="dialogVisible = false">取 消</el-button>
                                <el-button type="primary" @click="dialogBtn">确 定</el-button>
                             </span>
                        </el-dialog>
                    </ul>
                </div>
            </div>
        </div>
    </div>

</template>

<script>
    import RecvAddress from "./RecvAddress";
    export default {
        name: "RecvAddressList",
        components: {RecvAddress},
        data(){
            return {
                isShow:false,
                address: [],
                dialogVisible:false,
                confirmAddress: -1, // 选择的地址id
                radioStatus: 1,
                OrderInfo:{
                    uid:"",
                    recvName:"",
                    recvPhone:"",
                    recvProvince:"",
                    recvCity:"",
                    recvArea:"",
                    recvAddress:"",
                    totalPrice:""
                },
                user: this.$store.getters.getUser,
            }
        },
        created() {
            let _this = this;
            axios.get('http://localhost/tAddress/allByUid/'+this.user.uid).then(function (resp) {
                console.log(resp.data);
                _this.address=resp.data;
            })
        },
        methods:{
            dialogBtn(){
                this.dialogVisible = false;
                window.location.reload();
            },
            changeStatus(){},
            toInsertAddress(){
                this.dialogVisible = true;
            },
            handleClose(done) {
                this.$confirm('确认关闭？')
                    .then(_ => {
                        done();
                    })
                    .catch(_ => {});
            },
            deleteAddress(row){
                let _this = this;
                axios.delete('http://localhost/tAddress/delete/'+row).then(function (resp) {
                    if (resp.data===true){
                        _this.$alert('改地址地址已删除！', '提示', {
                            confirmButtonText: '确定',
                            callback: action => {
                                window.location.reload();
                            }
                        })
                    }
                })
            },
            recvAddress(index){
                this.confirmAddress = index;
                var temp = this.address[index];
                this.OrderInfo.uid = this.user.uid;
                this.OrderInfo.recvName = temp.name;
                this.OrderInfo.recvPhone = temp.phone;
                this.OrderInfo.totalPrice = this.getTotalPrice;
                this.OrderInfo.recvProvince = temp.provinceName;
                this.OrderInfo.recvCity = temp.cityName;
                this.OrderInfo.recvArea = temp.areaName;
                this.OrderInfo.recvAddress = temp.address;
                console.log(this.OrderInfo)
            },
            onSubmit(formName){
                let _this = this;
                this.$refs[formName].validate((valid) => {
                    if (valid) {
                        this.form.uid = this.$store.getters.getUser.uid;
                        this.form.isDefault = this.radioStatus;
                        console.log(_this.form);
                        axios.post('http://localhost/tAddress/',this.form).then(function (resp) {
                            _this.$alert('收货人为：'+_this.form.name+'的地址已添加成功！', '提示', {
                                confirmButtonText: '确定',
                                // callback: action => {
                                //     _this.$router.push('/main/address')
                                // }
                            });
                        })
                    }
                });
            },

        }
    }
</script>

<style scoped>

    .order {
        background-color: #f5f5f5;
        padding-bottom:  0px;
        min-height: 50vh;
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
    .order .order-content {
        width: 1225px;
        margin: 0 auto;
        background-color: #fff;
        margin-bottom: 50px;
    }
    /* 选择地址CSS */
    .content .section-address {
        margin: 0 48px;
        overflow: hidden;
    }
    .content .section-address .title {
        color: #333;
        font-size: 18px;
        line-height: 20px;
        margin-bottom: 20px;
    }
    .content .address-body li {
        float: left;
        color: #333;
        width: 220px;
        height: 178px;
        border: 1px solid #e0e0e0;
        padding: 15px 24px 0;
        margin-right: 17px;
        margin-bottom: 24px;
    }
    .content .address-body .in-section {
        border: 1px solid #ff6700;
    }
    .content .address-body ul li:hover {
        z-index: 2;
        -webkit-box-shadow: 0 15px 30px rgba(0, 0, 0, 0.1);
        box-shadow: 0 15px 30px rgba(0, 0, 0, 0.1);
        -webkit-transform: translate3d(0, -2px, 0);
        transform: translate3d(0, -2px, 0);
    }
    .content .address-body li .delete {
        position: absolute;
        top: 10px;
        right: 10px;
        display: none;
    }
    .content .address-body li:hover .delete {
        display: block
    }
    .content .address-body ul li .delete:hover {
        color: #ff6700;
    }
    .content .address-body li h2 {
        font-size: 18px;
        font-weight: normal;
        line-height: 30px;
        margin-bottom: 10px;
    }
    .content .address-body li p {
        font-size: 14px;
        color: #757575;
    }
    .content .address-body li .address {
        padding: 10px 0;
        max-width: 180px;
        max-height: 88px;
        line-height: 22px;
        overflow: hidden;
    }
    .content .address-body .add-address {
        text-align: center;
        line-height: 30px;
    }
    .content .address-body .add-address i {
        font-size: 30px;
        padding-top: 50px;
        text-align: center;
    }
    /* 选择地址CSS END */
</style>
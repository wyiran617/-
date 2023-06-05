<template>
    <div style="width: 840px">
        <div>
            <el-card>
                <el-input
                        placeholder="请输入地址"
                        v-model="input3"
                        class="input-with-select"
                >
                    <el-button slot="append" icon="el-icon-search" @click="inputfz">搜索</el-button>
                </el-input>
                <!--
              scroll-wheel-zoom是是否可以缩放
              @ready是图加载完后触发事件
              center是位置定位
              zoom是缩放大小限制
              inertial-dragging是允许惯性拖拽
             -->
                <baidu-map
                        class="bm-view"
                        :zoom="15"
                        :center="center"
                        inertial-dragging
                        @ready="mapReady"
                        @click="clickInfo"
                        :scroll-wheel-zoom="true"
                >
                    <!-- 定位控件   anchor="BMAP_ANCHOR_BOTTOM_RIGHT"代表放在右下角 -->
                    <bm-geolocation
                            anchor="BMAP_ANCHOR_BOTTOM_RIGHT"
                            :showAddressBar="true"
                            :autoLocation="true"
                    ></bm-geolocation>
                    <!-- 地区检索  keyword：关键字搜索   @searchcomplete：检索完成后的回调函数   auto-viewport：检索结束后是否自动调整地图视野  -->
                    <bm-local-search
                            :keyword="keyword"
                            @searchcomplete="search"
                            :auto-viewport="true"
                            class="search"
                    ></bm-local-search>
                    <!-- 缩放控件   anchor代表控件停靠位置   anchor="BMAP_ANCHOR_TOP_RIGHT"代表放在右上角-->
                    <bm-navigation anchor="BMAP_ANCHOR_TOP_RIGHT"></bm-navigation>
                </baidu-map>
            </el-card>
        </div>
        <el-form ref="form" :rules="rules" :model="form" label-width="120px">
            <el-form-item label="收货人名称" prop="name">
                <el-input v-model="form.name" style="width: 400px"></el-input>
            </el-form-item>
            <el-form-item label="联系电话" prop="phone">
                <el-input v-model="form.phone" style="width: 400px"></el-input>
            </el-form-item>
            <el-form-item label="省" prop="provinceName">
                <el-input v-model="form.provinceName" style="width: 400px"></el-input>
            </el-form-item>
            <el-form-item label="市" prop="cityName">
                <el-input v-model="form.cityName" style="width: 400px"></el-input>
            </el-form-item>
            <el-form-item label="区、县" prop="areaName">
                <el-input v-model="form.areaName" style="width: 400px"></el-input>
            </el-form-item>
            <el-form-item label="详细地址" prop="address">
                <el-input v-model="form.address" style="width: 400px"></el-input>
            </el-form-item>
            <el-form-item label="设为默认地址" prop="isDefault">
                <el-radio-group v-model="radioStatus" @change="changeStatus">
                    <el-radio :label="1">是</el-radio>
                    <el-radio :label="0">否</el-radio>
                </el-radio-group>
            </el-form-item>
            <el-form-item>
                <el-button type="primary" @click="onSubmit('form')">提交</el-button>
                <el-button @click="toblack()">取消</el-button>
            </el-form-item>
        </el-form>
    </div>
</template>

<script>
    import {
        BaiduMap,
        BmControl,
        BmView,
        BmAutoComplete,
        BmLocalSearch,
        BmMarker,
        BmGeolocation,
    } from "vue-baidu-map";
    export default {
        name: "AddressUpdate",
        data(){
            return{
                //点击处的地址信息
                addressComponents:{},
                //定位位置信息
                center: {},
                //检索关键字
                keyword: "",
                //输入框input值
                input3: "",
                radioStatus: 1,
                form: {
                    uid:"",
                    name:"",
                    phone:"",
                    provinceName:"",
                    cityName:"",
                    areaName:"",
                    address:"",
                    isDefault:""
                },
                rules: {
                    name: [
                        { required: true, message: '收货人名称不能为空', trigger: 'blur' },
                        { min: 1, max: 15, message: '长度在 1 到 15 个字符', trigger: 'blur' }
                    ],
                    phone: [
                        { required: true, message: '联系电话不能为空', trigger: 'blur' },
                        { min: 5, max: 15, message: '长度在 5 到 15 个字符', trigger: 'blur' }
                    ],
                }
            }
        },
        created() {
            let _this = this;
            axios.get('http://localhost/tAddress/'+this.$route.query.id).then(function (resp) {
                _this.form=resp.data;
                _this.radioStatus = _this.form.status
            });
        },
        //需要引入的组件
        components: {
            BaiduMap,
            BmControl,
            BmView,
            BmAutoComplete,
            BmLocalSearch,
            BmMarker,
            BmGeolocation,
        },
        methods:{
            changeStatus(){},
            toblack(){
                this.$router.push('/main/address')
            },
            onSubmit(formName){
                let _this = this;
                this.$refs[formName].validate((valid) => {
                    if (valid) {
                        this.form.uid = this.$store.getters.getUser.uid;
                        this.form.isDefault = this.radioStatus;
                        console.log(_this.form);
                        axios.put('http://localhost/tAddress/',this.form).then(function (resp) {
                            _this.$alert('收货人为：'+_this.form.name+'的地址已修改成功！', '提示', {
                                confirmButtonText: '确定',
                                callback: action => {
                                    _this.$router.push('/main/address')
                                }
                            });
                        })
                    }
                });
            },
            //输入框
            inputfz() {
                this.keyword = this.input3;
            },
            search(){},
            //地图加载后的回调
            mapReady({ BMap, map }) {
                //保存this指向，因为在百度的回调中this不指向vue
                const _this = this;
                // 获取自动定位方法
                var geolocation = new BMap.Geolocation();
                // 获取自动定位获取的坐标信息
                geolocation.getCurrentPosition(
                    function (r) {
                        //可以conso.log看一下这个r，他里面包含了检索到的位置信息。下面就把两个维度信息赋值给center来定位
                        _this.center = {
                            lng: r.point.lng,
                            lat: r.point.lat,
                        };
                    },
                    //启用高精度
                    { enableHighAccuracy: true }
                );
            },
            //获取点击处的地址信息
            clickInfo(e) {
                var geolocation = new BMap.Geocoder();
                geolocation.getLocation(e.point, res => {
                    this.addressComponents  = res.addressComponents;
                    console.log(this.addressComponents);
                    this.form.provinceName = this.addressComponents.province;
                    this.form.cityName = this.addressComponents.city;
                    this.form.areaName = this.addressComponents.district;
                    this.form.address = this.addressComponents.street + this.addressComponents.streetNumber;
                })
            }
        }
    }
</script>

<style scoped>
    /* 给个宽高 */
    .bm-view {
        height: 400px;
        width: 800px;
    }
    .input-with-select {
        width: 400px;
        margin-bottom: 5px;
    }
    .search {
        height: 300px;
        overflow: auto;
    }
</style>
<template>
    <div class="home" id="home" name="home">
        <!-- 轮播图 -->
        <div class="block">
            <el-carousel height="460px" >
                <el-carousel-item v-for="item in carousel" :key="item.name">
                    <img style="height:460px;width: 1225px;position: center" :src="item.src"  />
                </el-carousel-item>
            </el-carousel>
        </div>
        <!-- 轮播图END -->
        <div class="main-box">
            <div class="main">
                <!-- 电子产品展示区域 -->
                <div class="phone">
                    <div class="box-hd">
                        <div class="title">电子产品</div>
                    </div>
                    <div class="box-bd">
                        <div class="promo-list">
                            <router-link to>
                                <img :src="require('@/assets/imgs/phone/dzcp3.jpg')" />
                            </router-link>
                        </div>
                        <div class="list">
                            <MyList :list="electronicsList" :isMore="true"></MyList>
                        </div>
                    </div>
                </div>
                <!-- 电子产品展示区域END -->

                <!-- 办公文具展示区域 -->
                <div class="appliance" id="promo-menu">
                    <div class="box-hd">
                        <div class="title">办公文具</div>
                        <div class="more" id="more">
                            <MyMenu :val="2" @fromChild="getChildMsg">
                                <span slot="1">书本教材</span>
                                <span slot="2">记事本</span>
                                <span slot="3">打印用纸</span>
                                <span slot="4">笔</span>
                            </MyMenu>
                        </div>
                    </div>
                    <div class="box-bd">
                        <div class="promo-list">
                            <ul>
                                <li>
                                    <img :src="require('@/assets/imgs/phone/bangong.jpg')" />
                                </li>
                                <li>
                                    <img :src="require('@/assets/imgs/phone/bangong2.jpg')" />
                                </li>
                            </ul>
                        </div>
                        <div class="list">
                            <MyList :list="applianceList" :isMore="true"></MyList>
                        </div>
                    </div>
                </div>
                <!-- 办公文具展示区域END -->

                <!-- 生活用品展示区域 -->
                <div class="accessory" id="promo-menu">
                    <div class="box-hd">
                        <div class="title">生活用品</div>
                        <div class="more" id="more">
                            <MyMenu :val="3" @fromChild="getChildMsg2">
                                <span slot="1">毛巾</span>
                                <span slot="2">牙刷</span>
                                <span slot="3">沐浴露</span>
                            </MyMenu>
                        </div>
                    </div>
                    <div class="box-bd">
                        <div class="promo-list">
                            <ul>
                                <li>
                                    <img :src="require('@/assets/imgs/phone/showlife.jpg')" alt />
                                </li>
                                <li>
                                    <img :src="require('@/assets/imgs/phone/showlife2.jpg')" alt />
                                </li>
                            </ul>
                        </div>
                        <div class="list">
                            <MyList :list="accessoryList" :isMore="true"></MyList>
                        </div>
                    </div>
                </div>
                <!-- 生活用品展示区域END -->
            </div>
        </div>
    </div>
</template>

<script>
    export default {
        data() {
            return {
                carousel: [
                    {
                        name:"nav1",
                        src:require("@/assets/imgs/phone/nav4.jpg")
                    },
                    {
                        name:"nav2",
                        src:require("@/assets/imgs/phone/nav5.jpg")
                    }], // 轮播图数据
                electronicsList: "", // 电子产品列表
                miTvList: "", // 小米电视商品列表
                applianceList: "", // 办公文具列表
                applianceHotList: "", //热门家电商品列表
                accessoryList: "", //生活用品列表
                accessoryHotList: "", //热门配件商品列表
                protectingShellList: "", // 保护套商品列表
                chargerList: "", //充电器商品列表
                applianceActive: 1, // 家电当前选中的商品分类
                accessoryActive: 1 // 配件当前选中的商品分类
            };
        },
        watch: {
            // 家电当前选中的商品分类，响应不同的商品数据
            applianceActive: function(val) {
                // 页面初始化的时候把applianceHotList(热门家电商品列表)直接赋值给applianceList(家电商品列表)
                // 所以在切换商品列表时判断applianceHotList是否为空,为空则是第一次切换,把applianceList赋值给applianceHotList
                if (this.applianceHotList == "") {
                    this.applianceHotList = this.applianceList;
                }
                if (val == 1) {
                    // 1为热门商品
                    this.applianceList = this.applianceHotList;
                    return;
                }
                if (val == 2) {
                    // 2为电视商品
                    this.applianceList = this.miTvList;
                    return;
                }
            },
            accessoryActive: function(val) {
                // 页面初始化的时候把accessoryHotList(热门配件商品列表)直接赋值给accessoryList(配件商品列表)
                // 所以在切换商品列表时判断accessoryHotList是否为空,为空则是第一次切换,把accessoryList赋值给accessoryHotList
                if (this.accessoryHotList == "") {
                    this.accessoryHotList = this.accessoryList;
                }
                if (val == 1) {
                    // 1为热门商品
                    this.accessoryList = this.accessoryHotList;
                    return;
                }
                if (val == 2) {
                    // 2为保护套商品
                    this.accessoryList = this.protectingShellList;
                    return;
                }
                if (val == 3) {
                    //3 为充电器商品
                    this.accessoryList = this.chargerList;
                    return;
                }
            }
        },
        created() {
            // 获取各类商品数据
            //    获取electronicsList数据
            axios.get('http://localhost/tGoods/ElectronicsList').then(res =>{
                this.electronicsList = res.data;
                console.log(this.electronicsList)
            });
            //获取办公文具 applianceList数据
            axios.get('http://localhost/tGoods/ApplianceList').then(res =>{
                this.applianceList = res.data;
                console.log(this.applianceList)
            });
            //获取生活用品
            axios.get('http://localhost/tGoods/ShowLifeList').then(res =>{
                this.accessoryList = res.data;
                console.log(this.accessoryList)
            });
            // this.getPromo("办公用品", "miTvList");
            // this.getPromo("生活用品", "protectingShellList");
            // this.getPromo("充电器", "chargerList");
            // this.getPromo(
            //     ["电视机", "空调", "洗衣机"],
            //     "applianceList",
            //     ""
            // );
            // this.getPromo(
            //     ["保护套", "保护膜", "充电器", "充电宝"],
            //     "accessoryList",
            //     ""
            // );
        },
        methods: {
            // 获取家电模块子组件传过来的数据
            getChildMsg(val) {
                this.applianceActive = val;
            },
            // 获取配件模块子组件传过来的数据
            getChildMsg2(val) {
                this.accessoryActive = val;
            }
        }
    };
</script>

<style scoped>
    @import "../assets/css/index.css";
</style>
<template>
    <div style="width: 600px">
        <el-form ref="form" :rules="rules" :model="form" label-width="80px">
            <el-form-item label="商品类别" prop="itemType">
                <el-select v-model="form.itemType" filterable clearable  placeholder="请选择商品类型" >
                    <el-option
                            v-for="item in options"
                            :key="item.id"
                            :label="item.name"
                            :value="item.id+','+item.name"
                    >
                    </el-option>
                </el-select>
            </el-form-item>
            <el-form-item label="商品标题" prop="title">
                <el-input v-model="form.title"></el-input>
            </el-form-item>
            <el-form-item label="商品卖点" prop="sellPoint">
                <el-input v-model="form.sellPoint"></el-input>
            </el-form-item>
            <el-form-item label="商品单价" prop="price">
                <el-input v-model="form.price"></el-input>
            </el-form-item>
            <el-form-item label="库存数量" prop="num">
                <el-input v-model="form.num"></el-input>
            </el-form-item>
            <el-form-item label="商品图片" prop="image">
                <el-image v-for="item in fileList"
                        style="width: 146px; height: 146px"
                        :src="item.url"
                        fit="fill"></el-image>
            </el-form-item>
            <el-form-item label="图片数量" prop="imgNum">
                <el-input v-model="form.imgNum" disabled></el-input>
            </el-form-item>
            <el-form-item label="商品状态" prop="status">
                <el-radio-group v-model="radio" @change="changeRadio">
                    <el-radio :label="1">上架</el-radio>
                    <el-radio :label="2">下架</el-radio>
                    <el-radio :label="3">删除</el-radio>
                </el-radio-group>
            </el-form-item>
            <el-form-item label="显示优先级" prop="priority">
                <el-input v-model="form.priority"></el-input>
            </el-form-item>
            <el-form-item>
                <el-button type="primary" @click="onSubmit('form')">提交</el-button>
                <el-button @click="toBlack()">取消</el-button>
            </el-form-item>
        </el-form>
    </div>
</template>

<script>
    export default {
        name: "Update",
        created() {
            let _this = this;
            axios.get('http://localhost/tGoods/'+this.$route.query.id).then(function (resp) {
                _this.form=resp.data;
                _this.form.categoryId = resp.data.categoryId;
                for (let i =0;i<_this.form.imgNum;i++){
                    let item = {name:_this.imageList[i],url:"http://localhost"+_this.form.image+_this.imageList[i]};
                    _this.fileList.push(item)
                }
            });
            axios.get('http://localhost/tGoodsCategory/noParent').then(function (resp) {
                _this.options = resp.data
            });
        },
        data(){
            return{
                imageList:["collect.png","1_big.png","2_big.png","3_big.png","4_big.png","5_big.png"],
                fileList:[],
                options:[],
                radio: 1,
                form:{
                    id:'',
                    categoryId:'',
                    itemType:'',
                    title:'',
                    sellPoint:'',
                    price:'',
                    num:'',
                    image:'',
                    imgNum:'',
                    status:'',
                    priority:'',
                },
                rules: {
                    itemType: [
                        { required: true, message: '商品类别不能为空', trigger: 'blur' },
                        { min: 2, max: 15, message: '长度在 2 到 15 个字符', trigger: 'blur' }
                    ],
                    title: [
                        { required: true, message: '商品标题不能为空', trigger: 'blur' },
                        { min: 2, max: 15, message: '长度在 3 到 10 个字符', trigger: 'blur' }
                    ],
                }
            }
        },
        methods:{
            changeRadio(){
            },
            toBlack(){
                this.$router.push('/main/goodsInfo')
            },
            onSubmit(formName){
                let _this = this;
                this.$refs[formName].validate((valid) => {
                    if (valid) {
                        this.form.id = this.$route.query.id;
                        this.form.status = this.radio;
                        let temp = _this.form.itemType.split(",");
                        console.log(temp);
                        if (temp.length>1){
                            _this.form.categoryId = temp[0];
                            _this.form.itemType = temp[1];
                        }
                        axios.put('http://localhost/tGoods/',this.form).then(function (resp) {
                            if(resp.status===200){
                                _this.$alert(_this.form.title+'已修改成功！', '提示', {
                                    confirmButtonText: '确定',
                                    callback: action => {
                                        _this.$router.push('/main/goodsInfo')
                                    }
                                });
                            }
                        })
                    }
                });
                this.fileList = [];
            }
        }
    }
</script>

<style scoped>

</style>
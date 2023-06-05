<template>
    <div style="width: 600px">
        <el-form ref="form" :rules="rules" :model="form" label-width="100px">
            <el-form-item label="商品类别" prop="item_type">
                <el-select v-model="showSelect" filterable clearable  placeholder="请选择商品类型" >
                    <el-option
                            v-for="item in options"
                            :key="item.id"
                            :label="item.name"
                            :value="item.name">
                    </el-option>
                </el-select>
            </el-form-item>
            <el-form-item label="商品标题" prop="title">
                <el-input v-model="form.title"></el-input>
            </el-form-item>
            <el-form-item label="商品卖点" prop="sell_point">
                <el-input v-model="form.sell_point"></el-input>
            </el-form-item>
            <el-form-item label="商品单价" prop="price">
                <el-input v-model="form.price"></el-input>
            </el-form-item>
            <el-form-item label="库存数量" prop="num">
                <el-input v-model="form.num"></el-input>
            </el-form-item>
            <el-form-item label="图片路径" prop="image">
                <el-input v-model="form.image"></el-input>
            </el-form-item>
            <el-form-item label="商品状态" prop="status">
                <el-radio-group v-model="radio" @change="changeRadio">
                    <el-radio :label="1">上架</el-radio>
                    <el-radio :label="2">下架</el-radio>
                    <el-radio :label="3">删除</el-radio>
                </el-radio-group>
            </el-form-item>
            <el-form-item label="显示优先级" prop="status">
                <el-input v-model="form.status"></el-input>
            </el-form-item>
            <el-form-item label="创建时间" prop="created_time">
                <el-input v-model="form.created_time"></el-input>
            </el-form-item>
            <el-form-item label="创建人" prop="created_user">
                <el-input v-model="form.created_user"></el-input>
            </el-form-item>
            <el-form-item label="最后修改时间" prop="modified_time">
                <el-input v-model="form.modified_time"></el-input>
            </el-form-item>
            <el-form-item label="最后修改人" prop="modified_user">
                <el-input v-model="form.modified_user"></el-input>
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
        name: "goodsAdd",
        data(){
            return{
                showSelect:[],
                options:[],
                radio: 1,
                form:{
                    id:'',
                    category_id:'',
                    item_type:'',
                    title:'',
                    sell_point:'',
                    price:'',
                    num:'',
                    image:'',
                    status:'',
                    priority:'',
                    created_time:'',
                    modified_time:'',
                    created_user:'',
                    modified_user:'',

                },
                rules: {
                    item_type: [
                        { required: true, message: '商品类别不能为空', trigger: 'blur' },
                        { min: 2, max: 15, message: '长度在 2 到 15 个字符', trigger: 'blur' }
                    ],
                    title: [
                        { required: true, message: '商品标题不能为空', trigger: 'blur' },
                        { min: 2, max: 15, message: '长度在 3 到 10 个字符', trigger: 'blur' }
                    ],
                    price: [
                        { required: true, message: '商品单价不能为空', trigger: 'blur' },
                        { min: 1, max: 10, message: '长度在 1 到 10 个字符', trigger: 'blur' }
                    ],
                    num: [
                        { required: true, message: '商品库存不能为空', trigger: 'blur' },
                        { min: 1, max: 10, message: '长度在 1 到 10 个字符', trigger: 'blur' }
                    ],
                    image: [
                        { required: true, message: '商品图片不能为空', trigger: 'blur' }
                    ],
                    status: [
                        { required: true, message: '商品状态不能为空', trigger: 'blur' },,
                        { min: 1, max: 5, message: '长度在 1 到 5 个字符', trigger: 'blur' }
                    ],
                }
            }
        },
        created() {
                let _this = this;
                axios.get('http://localhost/tGoodsCategory/all').then(function (resp) {
                    _this.options = resp.data
                })

        },
        methods:{
            changeRadio(){
            },
            toblack(){
                this.$router.push('/goodsInfo')
            },
            onSubmit(formName){
                let _this = this;
                this.$refs[formName].validate((valid) => {
                    if (valid) {
                        console.log(_this.form);
                        this.form.status = this.radio;
                        axios.post('http://localhost/tGoods/',this.form).then(function (resp) {
                            if (resp.data>0){
                                _this.$alert(_this.form.title+'已添加成功！', '提示', {
                                    confirmButtonText: '确定',
                                    callback: action => {
                                        _this.$router.push('/goodsInfo')
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
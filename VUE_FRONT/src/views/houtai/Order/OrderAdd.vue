<template>
    <div style="width: 600px">
        <el-form ref="form" :rules="rules" :model="form" label-width="100px">
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
                <el-upload
                        action=""
                        :multiple="true"
                        :auto-upload="false"
                        :before-upload="beforeUploadImage"
                        accept="jpg,jpeg,png,PNG"
                        list-type="picture-card"
                        :file-list="fileList"
                        :limit="6"
                        :on-change="handleChange"
                >
                    <i slot="tip" class="el-upload__tip">最多上传6张png、jpg格式的图片</i>
                    <i class="el-icon-plus"></i>
                </el-upload>
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
        name: "goodsTypeAdd",
        data() {
            return {
                showSelect: [],
                options: [],
                radioIsParent:0,
                radioStatus: 1,
                form: {
                    id: '',
                    parentId: '',
                    name: '',
                    status: '',
                    sortOrder: '',
                    isParent: '',
                },
                rules: {
                    name: [
                        { required: true, message: '商品类别不能为空', trigger: 'blur' },
                        { min: 2, max: 15, message: '长度在 2 到 15 个字符', trigger: 'blur' }
                    ],
                }
            }
        },
        created() {
            let _this = this;
            axios.get('http://localhost/tGoodsCategory/isParent/').then(function (resp) {
                _this.options=resp.data;
            })
        },
        methods:{
            changeIsParent(val){
                let _this = this;
                axios.get('http://localhost/tGoodsCategory/isParent/').then(function (resp) {
                    _this.options=resp.data;
                })
            },
            changeStatus(){},
            toblack(){
                this.$router.push('/main/goodsType')
            },
            onSubmit(formName){
                let _this = this;
                this.$refs[formName].validate((valid) => {
                    if (valid) {
                        console.log(_this.form);
                        this.form.parentId = this.showSelect;
                        this.form.isParent = this.radioIsParent;
                        this.form.status = this.radioStatus;
                        axios.post('http://localhost/tGoodsCategory/',this.form).then(function (resp) {
                                _this.$alert(_this.form.title+'已添加成功！', '提示', {
                                    confirmButtonText: '确定',
                                    callback: action => {
                                        _this.$router.push('/main/goodsType')
                                    }
                                });
                        })
                    }
                });
            }
        }
    }
</script>

<style scoped>

</style>
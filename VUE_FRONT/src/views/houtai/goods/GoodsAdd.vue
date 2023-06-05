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
        name: "goodsAdd",
        data(){
            return{
                filePathPrefix:"",
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
                    price: [
                        { required: true, message: '商品单价不能为空', trigger: 'blur' },
                        { min: 1, max: 10, message: '长度在 1 到 10 个字符', trigger: 'blur' }
                    ],
                    num: [
                        { required: true, message: '商品库存不能为空', trigger: 'blur' },
                        { min: 1, max: 10, message: '长度在 1 到 10 个字符', trigger: 'blur' }
                    ],
                    priority: [
                        { required: true, message: '商品状态不能为空', trigger: 'blur' },,
                        { min: 1, max: 5, message: '长度在 1 到 5 个字符', trigger: 'blur' }
                    ],
                },
                user: this.$store.getters.getUser
            }
        },
        created() {
                let _this = this;
                axios.get('http://localhost/tGoodsCategory/noParent').then(function (resp) {
                    _this.options = resp.data
                });
                _this.createFilePathPrefix();
                console.log(this.filePathPrefix);
                _this.form.image =_this.filePathPrefix;
        },
        methods:{
            changeRadio(){
            },
            toBlack(){
                this.$router.push('/main/goodsInfo')
            },
            // 判断图片格式
            beforeUploadImage(file) {
                const isJpeg = file.type === 'image/png' || file.type === 'image/jpg'
                if (!isJpeg) {
                    this.$message.error('请选择正确的文件格式上传')
                }
                return isJpeg
            },
            // 选择文件时，往fileList里添加
            handleChange(fileList) {
                this.fileList.push(fileList)
            },
            createFilePathPrefix(){
                //生成12位随机数
                var mynum = Math.floor(Math.random()*1000000000000);
                var numlist = mynum.toString().split("");
                if(numlist.length !== 12){
                    for(let i=0;i<12-numlist.length;i++){
                        numlist.unshift('0')
                    }
                }
                this.filePathPrefix="/images/portal/"+numlist.join('')+"/";
            },
            onSubmit(formName){
                //保存图片信息
                if (this.fileList.length === 0) {
                    this.$message.warning('请选取文件');
                    return
                }
                const formData = new FormData();
                // 因为要传一个文件数组过去，所以要循环append
                this.fileList.forEach(file => {
                    formData.append('files', file.raw)
                });
                formData.append('path',this.filePathPrefix);
                axios.post('http://localhost/upload/product/image',formData).then(function (resp) {
                    this.$message.success('图片上传成功！')
                });
                //添加商品信息
                let _this = this;
                this.$refs[formName].validate((valid) => {
                    if (valid) {
                        console.log(_this.form);
                        this.form.imgNum = this.fileList.length;
                        this.form.status = this.radio;
                        let temp = _this.form.itemType.split(",");
                        _this.form.categoryId = temp[0];
                        _this.form.itemType = temp[1];
                        axios.post('http://localhost/tGoods/'+this.user.uid,this.form).then(function (resp) {
                                _this.$alert(_this.form.title+'已添加成功！', '提示', {
                                    confirmButtonText: '确定',
                                    callback: action => {
                                        _this.$router.push('/main/goodsInfo')
                                    }
                                });
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
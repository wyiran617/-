<template>
    <div style="width: 600px">
        <el-form ref="form" :rules="rules" :model="form" label-width="120px">
            <el-form-item label="标签是否为父类" prop="isParent">
                <el-radio-group v-model="radioIsParent" @change="changeIsParent">
                    <el-radio :label="1">是</el-radio>
                    <el-radio :label="0">否</el-radio>
                </el-radio-group>
            </el-form-item>
            <el-form-item label="类别父类" prop="parentId" v-if="this.radioIsParent===0">
                <el-select v-model="showSelect" filterable clearable  placeholder="请选择父类标签">
                    <el-option
                            v-for="item in options"
                            :key="item.id"
                            :label="item.name"
                            :value="item.id">
                    </el-option>
                </el-select>
            </el-form-item>
            <el-form-item label="类别名称" prop="name">
                <el-input v-model="form.name"></el-input>
            </el-form-item>
            <el-form-item label="类别排序" prop="sortOrder">
                <el-input v-model="form.sortOrder"></el-input>
            </el-form-item>
            <el-form-item label="类别状态" prop="status">
                <el-radio-group v-model="radioStatus" @change="changeStatus">
                    <el-radio :label="1">正常</el-radio>
                    <el-radio :label="0">删除</el-radio>
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
                                        _this.$router.push('/goodsType')
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
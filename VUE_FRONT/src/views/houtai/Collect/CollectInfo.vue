<template>
    <div class="index">
        <div style="margin: 10px 0">
            <el-input v-model="searchKey" placeholder="请输入关键字" style="width: 20%" />
            <el-button type="primary" style="margin-left: 5px" @click="toSearch">查询</el-button>
<!--            <el-button type="primary" @click="toAdd()">新增</el-button>-->
        </div>
        <el-table
                :data="tableData"
                border
                style="width: auto"
                max-height="850">
            <el-table-column
                    fixed
                    prop="cid"
                    label="cid"
                    width="150">
            </el-table-column>
            <el-table-column
                    prop="uid"
                    label="收藏用户id"
                    width="120">
            </el-table-column>
            <el-table-column
                    prop="id"
                    label="商品id"
                    width="120">
            </el-table-column>
            <el-table-column
                    prop="title"
                    label="商品标题"
                    width="120">
            </el-table-column>
            <el-table-column
                    prop="sellPoint"
                    label="商品卖点"
                    width="120">
            </el-table-column>
            <el-table-column
                    prop="price"
                    label="商品单价"
                    width="120">
            </el-table-column>
            <el-table-column
                    prop="image"
                    label="图片路径"
                    width="120">
            </el-table-column>
            <el-table-column
                    prop="createdUser"
                    label="创建人"
                    width="120">
            </el-table-column>
            <el-table-column
                    prop="createdTime"
                    label="创建时间"
                    width="120">
            </el-table-column>
            <el-table-column
                    prop="modifiedUser"
                    label="最后修改人"
                    width="120">
            </el-table-column>
            <el-table-column
                    prop="modifiedTime"
                    label="最后修改时间"
                    width="120">
            </el-table-column>
            <el-table-column
                    label="操作"
                    width="150">
                <template slot-scope="scope">
<!--                    <el-button-->
<!--                            type="warning"-->
<!--                            size="small"-->
<!--                            @click.native.prevent="handleEdit(scope.row)">编辑</el-button>-->
                    <el-button
                            size="small"
                            type="danger"
                            @click.native.prevent="handleDelete(scope.row)">删除</el-button>
                </template>
            </el-table-column>
        </el-table>
        <div style="margin: 10px 0;padding-left: 50px">
            <el-pagination
               @size-change="handleSizeChange"
               @current-change="handleCurrentChange"
               :current-page="pageNum"
               :page-sizes="[5, 10, 20]"
               :page-size="pageSize"
               layout="total, sizes, prev, pager, next, jumper"
               :total="total">
            </el-pagination>
        </div>
    </div>

</template>

<script>
    export default {
        name: "collectInfo",
        data() {
            return {
                tableData: '',
                total:0,
                pageNum:1,
                pageSize:10,
                searchKey:""
            }
        },
        created(){
          //请求分页查询数据
          this.load()
        },
        methods: {
            load(){
                fetch("http://localhost/tCollect/page?pageNum=" + this.pageNum + "&pageSize=" + this.pageSize).then(res => res.json().then(res => {
                    console.log(res),
                        this.tableData = res.data,
                        this.total = res.total
                }))
            },
            toSearch(){
                fetch("http://localhost/tCollect/searchPage?pageNum=" + this.pageNum + "&pageSize=" + this.pageSize + "&searchKey=" + this.searchKey).then(res => res.json().then(res => {
                    console.log(res);
                        this.tableData = res.data;
                        this.total = res.total
                }))
            },
            toAdd(){
                this.$router.push('/main/collectAdd')
            },
            handleEdit(row){
                this.$router.push('/main/collectUpdate?id='+row.cid)
            },
            handleDelete(row) {
                let _this = this;
                this.$confirm('此操作将永久删除'+row.title+'商品信息, 是否继续?', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(() => {
                    axios.delete('http://localhost/tCollect/delete/'+row.cid).then(function (resp) {
                        if (resp.data>0){
                            _this.$alert(row.title+'已被删除！', '提示', {
                                confirmButtonText: '确定',
                                callback: action => {
                                    location.reload()
                                }
                            });
                            }
                        })
                }).catch(() => {
                    this.$message({
                        type: 'info',
                        message: '已取消删除'
                    });
                });
            },
            handleSizeChange(pageSize){
                console.log(pageSize);
                this.pageSize = pageSize;
                if (this.searchKey!=''){
                    this.toSearch()
                }else {
                    this.load()
                }

            },
            handleCurrentChange(pageNum){
                this.pageNum = pageNum;
                if (this.searchKey!=''){
                    this.toSearch()
                }else{
                    this.load()
                }

            },
        }

    }
</script>


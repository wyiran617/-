<template>
    <div class="index">
        <div style="margin: 10px 0">
            <el-input v-model="searchKey" placeholder="请输入关键字" style="width: 20%" />
            <el-button type="primary" style="margin-left: 5px" @click="toSearch">查询</el-button>
            <el-button type="primary" @click="toAddGoodsType()">新增</el-button>
        </div>
        <el-table
                :data="tableData"
                border
                style="width: auto"
                max-height="850">
            <el-table-column
                    fixed
                    prop="id"
                    label="id"
                    width="150">
            </el-table-column>
            <el-table-column
                    prop="parentId"
                    label="分类父id"
                    width="120">
            </el-table-column>
            <el-table-column
                    prop="name"
                    label="类别名称"
                    width="120">
            </el-table-column>
            <el-table-column
                    prop="status"
                    label="类别状态"
                    width="120">
            </el-table-column>
            <el-table-column
                    prop="sortOrder"
                    label="类别排序"
                    width="120">
            </el-table-column>
            <el-table-column
                    prop="isParent"
                    label="是否为父标签"
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
                    <el-button
                            type="warning"
                            size="small"
                            @click.native.prevent="handleEdit(scope.row)">编辑</el-button>
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
        name: "goodsType",
        data() {
            return {
                tableData: '',
                total: 0,
                pageNum: 1,
                pageSize: 10,
                searchKey: ""
            }
        },
        created(){
            //请求分页查询数据
            this.load()
        },
        methods: {
            load() {
                fetch("http://localhost/tGoodsCategory/page?pageNum=" + this.pageNum + "&pageSize=" + this.pageSize).then(res => res.json().then(res => {
                    console.log(res),
                        this.tableData = res.data,
                        this.total = res.total
                }))
            },
            toSearch() {
                fetch("http://localhost/tGoodsCategory/searchPage?pageNum=" + this.pageNum + "&pageSize=" + this.pageSize + "&searchKey=" + this.searchKey)
                    .then(res => res.json().then(res => {
                    console.log(res);
                    this.tableData = res.data;
                    this.total = res.total
                }))
            },
            toAddGoodsType() {
                this.$router.push('/main/goodsTypeAdd')
            },
            handleEdit(row) {
                this.$router.push('/main/goodsTypeUpdate?id=' + row.id)
            },
            handleDelete(row) {
                let _this = this;
                this.$confirm('此操作将永久删除' + row.title + '商品信息, 是否继续?', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(() => {
                    axios.delete('http://localhost/tGoodsCategory/delete/' + row.id).then(function (resp) {
                        if (resp) {
                            _this.$alert(row.title + '已被删除！', '提示', {
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
            handleSizeChange(pageSize) {
                console.log(pageSize);
                this.pageSize = pageSize;
                if (this.searchKey != '') {
                    this.toSearch()
                } else {
                    this.load()
                }

            },
            handleCurrentChange(pageNum) {
                this.pageNum = pageNum;
                if (this.searchKey != '') {
                    this.toSearch()
                } else {
                    this.load()
                }

            },
        }

    }
</script>


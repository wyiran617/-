<template>
<!--    <el-input v-model="search" placeholder="请输入关键字" style="width: 20%"></el-input>-->
<!--    <el-button type="primary" style="margin-left: 5px">查询</el-button>-->
<!--    <el-button type="primary">新增</el-button>-->
    <div class="index">
<!--        <div style="margin: 10px 10px 10px 10px">-->
<!--            <el-breadcrumb separator="/">-->
<!--                <el-breadcrumb-item :to="{ path: '/' }">用户管理</el-breadcrumb-item>-->
<!--                <el-breadcrumb-item><a href="/">用户信息列表</a></el-breadcrumb-item>-->
<!--            </el-breadcrumb>-->
<!--        </div>-->
        <div style="margin: 10px 0">
            <el-input v-model="search" placeholder="请输入关键字" style="width: 20%"></el-input>
            <el-button type="primary" style="margin-left: 5px">查询</el-button>
            <el-button type="primary" @click="toAddUser()">新增</el-button>
        </div>
        <el-table
                :data="tableData"
                border
                style="width: 1300px">
            <el-table-column
                    fixed
                    prop="uid"
                    label="uid"
                    width="150">
            </el-table-column>
            <el-table-column
                    prop="username"
                    label="用户名"
                    width="120">
            </el-table-column>
            <el-table-column
                    prop="password"
                    label="密码"
                    width="120">
            </el-table-column>
            <el-table-column
                    prop="phone"
                    label="联系电话"
                    width="120">
            </el-table-column>
            <el-table-column
                    prop="email"
                    label="邮箱"
                    width="120">
            </el-table-column>
            <el-table-column
                    prop="avatar"
                    label="头像"
                    width="240">
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
            <el-table-column label="操作">
                <template slot-scope="scope">
                    <el-button
                            size="mini"
                            @click="handleEdit(scope.row)">编辑</el-button>
                    <el-button
                            size="mini"
                            type="danger"
                            @click="handleDelete(scope.row)">删除</el-button>
                </template>
            </el-table-column>
        </el-table>
    </div>

</template>

<script>
    export default {
        name: "index",
        created() {
            let _this = this;
            axios.get('http://localhost/user/all').then(function (resp) {
                    console.log(resp.data);
                _this.tableData = resp.data
            })
        },
        methods: {
            toAddUser(){
                this.$router.push('/main/reg')
            },
            handleEdit(row){
                this.$router.push('/main/update?uid='+row.uid)
            },
            handleDelete(row) {
                let _this = this;
                this.$confirm('此操作将永久删除'+row.username+'账号信息, 是否继续?', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(() => {
                    axios.delete('http://localhost/user/delete/'+row.uid).then(function (resp) {
                        if (resp.data>0){
                            _this.$alert(row.username+'已被删除！', '提示', {
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
            }
        },
        data() {
            return {
                tableData: ''
            }
        }
    }
</script>


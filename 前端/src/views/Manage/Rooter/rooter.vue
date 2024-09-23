<template>
    <div class="rooter">
        <!-- <div><el-input clearable suffix-icon="el-icon-search" style="width: 30%; float:right;"></el-input></div> -->
        <!-- <div style="margin-bottom: 20px; float:left;">
            <el-button size="small">设为普通用户</el-button>
            <el-button size="small">设为管理员</el-button>
            <el-button size="small">设为超级管理员</el-button>
        </div> -->
        <el-table :data="tableData" height="550" border style="width: 100%" ref="multipleTable"
            :default-sort="{ prop: 'admin', order: 'descending' }">
            <el-table-column type="selection" width="55">
            </el-table-column>
            <el-table-column prop="uname" label="用户名">
            </el-table-column>
            <el-table-column prop="uadmin" label="身份" width="150" sortable>
            </el-table-column>
            <el-table-column fixed="right" label="操作" width="280">
                <template slot-scope="scope">
                    <el-button @click="updateUser1(scope.row, 0)" type="text" size="small">设为普通用户</el-button>
                    <el-button @click="updateUser1(scope.row, 1)" type="text" size="small">设为管理员</el-button>
                    <!--<el-button @click="updateUser1(scope.row, 2)" type="text" size="small">设为超级管理员</el-button>-->
                </template>
            </el-table-column>
        </el-table>
    </div>
</template>

<script>
import { _getAllUser, _updateUser } from "@/api/user"
export default {
    data() {
        return {
            tableData: [{
                date: '1',
                name: 'aa',
                address: '上海市普陀区金沙江路 1518 弄',
                admin: '超级管理员'
            }, {
                date: '2',
                name: 'bb',
                address: '上海市普陀区金沙江路 1518 弄',
                admin: '管理员'
            }, {
                date: '3',
                name: 'cc',
                address: '上海市普陀区金沙江路 1518 弄',
                admin: '超级管理员'
            }, {
                date: '4',
                name: 'dd',
                address: '上海市普陀区金沙江路 1518 弄',
                admin: '普通用户'
            }, {
                date: '5',
                name: 'ee',
                address: '上海市普陀区金沙江路 1518 弄',
                admin: '普通用户'
            }]
        }
    },
    methods: {

        async getAllUser() {
            try {
                let res = await _getAllUser()
                if (res.status == 200) {
                    this.tableData = res.data
                }
            } catch (error) {
                console.log(error)
            }
        },
        updateUser1(item, idx) {
            this.$confirm('此操作将改变该用户身份, 是否继续?', '提示', {
                confirmButtonText: '确定',
                cancelButtonText: '取消',
                type: 'warning'
            }).then(() => {
                this.updateUser(item, idx)
            }).catch(() => {
                this.$message({
                    type: 'info',
                    message: '已取消'
                });
            });
        },
        async updateUser(item, idx) {
            try {
                let identi = "普通用户"
                if (idx == 1) {
                    identi = "管理员"
                } else if (idx == 2) {
                    identi = "超级管理员"
                }
                if (identi == item.uadmin) {
                    this.$message({
                        type: 'warning',
                        message: '身份与改变身份相同'
                    });
                } else {
                    let temp = item
                    temp.uadmin = identi
                    let res = await _updateUser(temp)
                    if (res.status == 200) {
                        if (res.data) {
                            item.uadmin = identi
                            this.$message({
                                type: 'success',
                                message: '身份改变成功!'
                            });
                        }
                    }
                }
            } catch (error) {
                console.log(error)
            }
        },
    },
    mounted() {
        this.getAllUser()
    }
}
</script>

<style lang="scss"></style>
<template>
    <div>
        <el-table :data="tableData" height="550" border style="width: 100%">
            <el-table-column prop="uinittime" label="入学时间" width="200">
            </el-table-column>
            <el-table-column prop="uname" label="用户名">
            </el-table-column>
            <!-- <el-table-column prop="address" label="是否实名" width="100">
            </el-table-column> -->
            <el-table-column prop="ukill" label="被举报次数" width="100">
            </el-table-column>
            <el-table-column fixed="right" label="操作" width="150">
                <template slot-scope="scope">
                    <el-button @click="rePwd(scope.row)" type="text" size="small">重置密码</el-button>
                    <el-button @click="handleClick(scope.row)" type="text" size="small">查看此用户</el-button>
                </template>
            </el-table-column>
        </el-table>
    </div>
</template>

<script>
import { _getAllUser, _rePwd } from "@/api/user"
export default {
    data() {
        return {
            tableData: [{
                date: '1',
                name: 'aa',
                address: '否',
                address1: '0'
            }, {
                date: '2',
                name: 'bb',
                address: '否',
                address1: '0'
            }, {
                date: '3',
                name: 'cc',
                address: '否',
                address1: '0'
            }, {
                date: '4',
                name: 'dd',
                address: '否',
                address1: '0'
            }, {
                date: '5',
                name: 'ee',
                address: '否',
                address1: '0'
            }]
        }
    },
    methods: {
        handleClick(item) {
            // userview
            // this.$store.state.saleView = item
            this.$store.commit("setSaleView", item)
            setTimeout(() => {
                this.$router.push('/userview')
            }, 500)
        },
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
        rePwd(item) {
            this.$confirm('此操作将重置该用户密码, 是否继续?', '提示', {
                confirmButtonText: '确定',
                cancelButtonText: '取消',
                type: 'warning'
            }).then(() => {
                this.updateUser(item)
            }).catch(() => {
                this.$message({
                    type: 'info',
                    message: '已取消'
                });
            });
        },
        async updateUser(item) {
            try {
                let res = await _rePwd(item)
                if (res.status == 200) {
                    if (res.data) {
                        this.$message({
                            type: 'success',
                            message: '重置成功!'
                        });
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
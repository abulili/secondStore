<template>
    <div>
        <el-table height="550" border style="width: 100%" :default-sort="{ prop: 'date', order: 'descending' }"
            :data="tableData">
            <!-- :data="tableData.filter(data => !search || data.name.toLowerCase().includes(search.toLowerCase()))" -->
            <!-- <el-table-column prop="date" label="举报时间" width="100" sortable>
            </el-table-column> -->
            <!-- <el-table-column prop="type" label="举报类别" width="200">
            </el-table-column> -->
            <!-- <el-table-column prop="address" label="用户ID" width="100">
            </el-table-column> -->
            <el-table-column prop="comment.ccontent" label="举报内容(评论)">
            </el-table-column>
            <el-table-column prop="store.scontent" label="举报内容(商品)">
            </el-table-column>
            <!-- <el-table-column prop="address" label="举报类别" width="130" sortable>
            </el-table-column> -->
            <el-table-column fixed="right" label="操作" width="150">
                <!-- <template slot="header" slot-scope="scope">
                    <el-input v-model="search" size="mini" placeholder="输入关键字搜索" />
                </template> -->

                <template slot-scope="scope">
                    <el-button @click="report(scope.row, '2')" type="text" size="small">删除</el-button>
                    <el-button @click="report(scope.row, '1')" type="text" size="small">忽略</el-button>
                    <!-- <el-button @click="handleClick(scope.row)" type="text" size="small">详情</el-button> -->
                </template>
            </el-table-column>
        </el-table>
    </div>
</template>

<script>
import { _listReport, _delReport } from "@/api/report"
import { _delComment } from "@/api/comment"
import { _updateUser, _serachUid } from "@/api/user"
import { _delStore } from "@/api/index"
export default {
    data() {
        return {
            search: '',
            tableData: []
        }
    },
    methods: {
        async listReport() {
            try {
                let res = await _listReport()
                if (res.status == 200) {
                    console.log(res.data)
                    this.tableData = res.data
                }
            } catch (error) {
                console.log(error)
            }
        },
        report(item, idx) {
            this.$confirm('确认执行此操作?', '提示', {
                confirmButtonText: '确定',
                cancelButtonText: '取消',
                type: 'warning'
            }).then(() => {
                this.delReport(item.report, idx)
                if (idx == '2') {
                    this.updateUser(item)
                    if (item.comment != null) {
                        this.delComment(item.comment)
                    } else {
                        this.delStore(item.store)
                    }
                }
                this.$message({
                    type: 'success',
                    message: '删除成功!'
                });
                this.listReport()
            }).catch(() => {
                this.$message({
                    type: 'info',
                    message: '已取消删除'
                });
            });
        },
        async delReport(data, idx) {
            try {
                data.isdeal = idx
                let res = await _delReport(data)
                if (res.status == 200) {
                    if (res.data) {
                        console.log('report处理成功')
                    }
                }
            } catch (error) {
                console.log(error)
            }
        },
        async delComment(data) {
            try {
                let res = await _delComment(data)
                if (res.status == 200) {
                    if (res.data) {
                        console.log("评论删除成功")
                    }
                }
            } catch (error) {
                console.log(error)
            }
        },
        async delStore(data) {
            try {
                let res = await _delStore(data)
                if (res.status == 200) {
                    if (res.data) {
                        console.log("商品删除成功")
                    }
                }
            } catch (error) {
                console.log(error)
            }
        },
        async updateUser(data) {
            try {
                // 先要去搜谁发的这个
                let tempId;
                if (data.comment != null) {
                    tempId = data.comment.suid
                } else {
                    tempId = data.store.suid
                }
                let res = await _serachUid({
                    uid: tempId
                })
                if (res.status == 200) {
                    let user = res.data
                    if (user.length != 0) {
                        user[0].ukill = -1;
                        console.log('user', user[0])
                        let ress = await _updateUser(user[0])
                        if (ress.status == 200) {
                            if (ress.data) {
                                console.log("用户更新成功")
                            }
                        }
                    }
                }
            } catch (error) {
                console.log(error)
            }
        },
    },
    mounted() {
        this.listReport()
    }
}
</script>

<style lang="scss"></style>
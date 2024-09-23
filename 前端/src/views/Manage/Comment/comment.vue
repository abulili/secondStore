<template>
    <div>
        <el-table height="550" border style="width: 100%" :default-sort="{ prop: 'ctime', order: 'descending' }"
            :data="tableData.filter(data => !search || data.name.toLowerCase().includes(search.toLowerCase()))">
            <el-table-column prop="ctime" label="评论时间" width="100" sortable>
            </el-table-column>
            <el-table-column prop="cid" label="帖子ID" width="200">
            </el-table-column>
            <el-table-column prop="suid" label="用户ID" width="100">
            </el-table-column>
            <el-table-column prop="ccontent" label="评论内容">
            </el-table-column>
            <el-table-column fixed="right" label="操作" width="150">
                <!-- <template slot="header" slot-scope="scope">
                    <el-input v-model="search" size="mini" placeholder="输入关键字搜索" />
                </template> -->

                <template slot-scope="scope">
                    <el-button @click="delComment1(scope.row, scope.index)" type="text" size="small">删除</el-button>
                    <!-- <el-button @click="handleClick(scope.row)" type="text" size="small">详情</el-button> -->
                </template>
            </el-table-column>
        </el-table>
    </div>
</template>

<script>
import { _listComment, _delComment } from "@/api/comment"
export default {
    data() {
        return {
            search: '',
            tableData: [{
                uid: 2,
                date: '2024-03-03',
                date1: 1,
                address: '3',
                name: '这破玩意送我都不要',
                address: '恶意中伤'
            }]
        }
    },
    methods: {
        delComment1(item, idx) {
            this.$confirm('确认删除此评论？', '提示', {
                confirmButtonText: '确定',
                cancelButtonText: '取消',
                type: 'warning'
            }).then(() => {
                this.delComment(item, idx)
            }).catch(() => {
                this.$message({
                    type: 'info',
                    message: '已取消'
                });
            });
        },
        async delComment(item, idx) {
            // 其实最好应该通知一下，太麻烦，放弃了
            try {
                let res = await _delComment(item)
                if (res.status == 200) {
                    if (res.data) {
                        this.tableData.splice(idx, 1);
                        this.$message({
                            type: 'success',
                            message: '成功删除该评论'
                        });
                    }
                }
            } catch (error) {
                console.log(error)
            }
        },
        async listComment() {
            try {
                let res = await _listComment()
                if (res.status == 200) {
                    this.tableData = res.data
                    console.log(res.data)
                }
            } catch (error) {
                console.log(error)
            }
        }
    },
    mounted() {
        this.listComment()
    }
}
</script>

<style lang="scss"></style>
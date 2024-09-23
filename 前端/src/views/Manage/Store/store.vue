<template>
    <div>
        <el-table :data="tableData" height="550" border style="width: 100%"
            :default-sort="{ prop: 'date', order: 'descending' }">
            <el-table-column prop="stime" label="发布时间" width="140" sortable>
            </el-table-column>
            <el-table-column prop="stitle" label="商品名" width="200">
            </el-table-column>
            <el-table-column label="商品图" width="150">
                <template slot-scope="scope">
                    <img :src="'data:image/png;base64,' + scope.row.spic" width="100" height="90"
                        style="object-fit: contain;">
                </template>
            </el-table-column>
            <el-table-column prop="scontent" label="商品介绍">
            </el-table-column>
            <el-table-column fixed="right" label="操作" width="150">

                <template slot-scope="scope">
                    <!-- <el-button @click="handleClick(scope.row)" type="text" size="small"> 要求修改，下架改正</el-button> -->
                    <el-button @click="delStore1(scope.row, scope.index)" type="text" size="small">删除商品</el-button>
                    <el-button @click="handleClick(scope.row)" type="text" size="small">查看详情</el-button>
                </template>
            </el-table-column>
        </el-table>
    </div>
</template>

<script>
import { _listAll, _delStore } from '@/api/index'
export default {
    data() {
        return {
            tableData: []
        }
    },
    methods: {
        handleClick(item) {
            // this.$store.state.storeDetail = item
            this.$store.commit("setStoreDetail", item)
            setTimeout(() => {
                this.$router.push('/store')
            }, 1000)
        },
        delStore1(item, idx) {
            this.$confirm('此操作将删除此商品, 是否继续?', '提示', {
                confirmButtonText: '确定',
                cancelButtonText: '取消',
                type: 'warning'
            }).then(() => {
                this.delStore(item, idx)
            }).catch(() => {
                this.$message({
                    type: 'info',
                    message: '已取消'
                });
            });
        },
        async delStore(item, idx) {
            // 其实最好应该通知一下，太麻烦，放弃了
            try {
                let res = await _delStore(item)
                if (res.status == 200) {
                    if (res.data) {
                        this.tableData.splice(idx, 1);
                        this.$message({
                            type: 'success',
                            message: '成功删除该商品'
                        });
                    }
                }
            } catch (error) {
                console.log(error)
            }
        },
        async listAll() {
            try {
                let res = await _listAll()
                console.log(res)
                if (res.status == 200) {
                    this.tableData = res.data
                    //     console.log(res.data)
                }
            } catch (error) {
                console.log(error)
            }
        }
    },
    mounted() {
        this.listAll()
    },
}
</script>

<style lang="scss"></style>
<template>
    <div>
        <div class="content">
            <el-row class="contentList" :gutter="20" v-for="(row, rowIndex) in Math.ceil(yjList.length / 5)"
                :key="rowIndex">
                <el-col :span="4" v-for="(item, colIndex) in yjList.slice(rowIndex * 5, (rowIndex + 1) * 5)"
                    :key="colIndex">
                    <el-card :body-style="{ padding: '0px' }" @click="runToStore(item)">
                        <img :src="'data:image/png;base64,' + item.store.spic" alt="" @click="runToStore(item)">
                        <div class="char">
                            <div class="bottom clearfix">
                                <el-row style="width:90%;margin: 0 auto;">
                                    <el-col :span="20" style="text-align: left; font-size: 14px;"><span>{{
                item.store.stitle
            }}</span></el-col>
                                    <el-col :span="4">
                                        <el-button type="warning" icon="el-icon-star-off" circle size="small"
                                            @click="changeIcon(rowIndex * 5 + colIndex, item)"></el-button></el-col>
                                </el-row>
                            </div>
                        </div>
                    </el-card>
                </el-col>
            </el-row>
        </div>
        <div class="pages">
            <el-pagination background layout="prev, pager, next" :total="MusicList.length" :page-size="25"
                @current-change="handleCurrentChange">
            </el-pagination>
        </div>
    </div>
</template>

<script>
import { _listStar, _delStar } from "@/api/star"
import { _listId } from "@/api/index"
export default {
    data() {
        return {
            input2: '',
            MusicList: ['a', 'a', 'a']
            , yjList: [],
            icon: '',
            currentPage: 1,
        }
    },
    methods: {
        runToStore(item) {
            // this.$store.state.storeDetail = item.store
            this.$store.commit("setStoreDetail", item.store)
            this.$router.push('/store')
        },
        // async storeDetail(sid) {
        //     try {
        //         let res = await _listId({
        //             sid: sid
        //         })
        //         if (res.state == 200) {
        //             this.$store.state.storeDetail = item
        //             // this.$router.push('/store')
        //             console.log(item)
        //         }
        //     } catch (error) {
        //         console.log(error)
        //     }
        // },
        changeIcon(idx, item) {
            // 删除被点击的项
            this.yjList.splice(idx, 1);
            // 更新总的音乐列表
            this.MusicList = this.MusicList.filter((_, index) => index !== idx + (this.currentPage - 1) * 26);
            this.delStar(item)
            // this.listUser()
        },
        async delStar(item) {
            try {
                console.log('delStar', item)
                let res = await _delStar(item.star)
                if (res.status == 200) {
                    if (res.data) {
                        console.log('取消收藏成功')
                    }
                }
            } catch (error) {
                console.log(error)
            }
        },
        handleCurrentChange(val) {
            console.log(`当前页: ${val}`)
            this.currentPage = val - 1;
            this.slicePage(val)
        },
        slicePage(page) {
            this.yjList = this.MusicList.slice((page - 1) * 26, page * 26 - 1)
        },
        async listUser() {
            try {
                let data = {
                    uid: this.$store.state.users.userInfo.uid
                }
                let res = await _listStar(data)
                if (res.status == 200) {
                    this.MusicList = res.data
                    console.log(res.data)
                    this.slicePage(1)
                }
            } catch (error) {
                console.log(error)
            }
        },
    },
    mounted() {
        this.listUser()
        this.slicePage(1)
    }
}
</script>

<style lang="scss" scoped>
.content {
    position: relative;
    width: 89%;
    margin: 0 auto;
    margin-left: 10%;
}

.contentList {
    margin-top: 15px;
    height: auto;
}

.pages {
    margin-top: 20px;
}

.el-card {
    img {
        width: 200px;
        height: 200px;
        object-fit: contain;
    }
}
</style>
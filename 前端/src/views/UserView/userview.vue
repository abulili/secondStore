<template>
    <div class="userview">
        <div class="user-status">
            <div class="back-img"></div>
            <div class="user">
                <div><el-image v-if="currentUser.uavatar" style="width: 100px; height: 100px"
                        :src="'data:image/png;base64,' + currentUser.uavatar" fit="cover"></el-image>
                    <el-image v-else style="width: 100px; height: 100px" :src="src" fit="cover"></el-image>
                </div>
                <div style="position:relative;">
                    <div class="user-name">{{ currentUser.uname }} </div>
                </div>
                <div class="user-num">
                    <el-row>
                        <el-col :span="8">商品数：<span>{{ storeLists.length }}</span></el-col>
                        <el-col :span="8
                    ">买家评价数：<span>{{ boughtLists.length }}</span></el-col>
                        <el-col :span="8
                    ">被举报次数：<span>{{ currentUser.ukill }}</span></el-col>
                    </el-row>
                </div>
            </div>
            <!-- <div>已实名</div> -->
            <!-- <el-button>关注/设置</el-button> -->
        </div>
        <el-divider></el-divider>
        <div class="content">
            <div class="products-status">
                <div> <el-button @click="nowIdx = false" :type="nowIdx == false ? 'primary' : ''">宝贝</el-button>
                    <el-button :type="nowIdx == true ? 'primary' : ''" @click="nowIdx = true">评价</el-button>
                    <!-- <el-button>已售出 -->
                    <!-- （就不用删除表了，查的话就不查已售出的了） -->
                    <!-- </el-button> -->
                </div>
            </div>
            <div style="position:relative;width:90%;margin:0 auto;margin-bottom: 20px;margin-top:15px;">
                <div v-show="nowIdx" v-for="(item, index) in boughtLists" :key="index">
                    <!-- <el-descriptions class="margin-top" title="评价" :column="3">
                        <el-descriptions-item label=""><el-image style="width: 50px; height: 50px" :src="src"
                                fit="cover"></el-image></el-descriptions-item>
                        <el-descriptions-item label="买家">{{ item.user.uname }}</el-descriptions-item>
                        <el-descriptions-item label="评价">{{ item.bought.mcontent }}</el-descriptions-item>
                    </el-descriptions> -->
                    <!-- <el-descriptions class="margin-top" title="" :column="2">
                        <el-descriptions-item label="买家">dirs</el-descriptions-item>
                        <el-descriptions-item label="评价">该用户觉得此宝贝不错</el-descriptions-item>
                    </el-descriptions> -->
                    <el-row style="position: relative;">
                        <el-col :span="2"><el-image style="width: 60px; height: 60px;border-radius: 50%;" :src="src"
                                fit="cover"></el-image></el-col>
                        <el-col :span="6" style="text-align: left;font-size:22px;margin-top:15px">{{ item.user.uname
                            }}</el-col>
                        <el-col :span="15" style="text-align: left; margin-top:15px;font-size: 15px;">
                            <el-divider direction="vertical"></el-divider>
                            评价{{
                    item.bought.mcontent }}</el-col>
                    </el-row>
                </div>
                <div v-show="!nowIdx">
                    <!-- (根据不同的点击事件进行查询) -->
                    <el-row class="contentList" :gutter="20" v-for="(row, rowIndex) in Math.ceil(storeLists.length / 5)"
                        :key="rowIndex">
                        <el-col :span="4" v-for="(item, colIndex) in storeLists.slice(rowIndex * 5, (rowIndex + 1) * 5)"
                            :key="colIndex">
                            <el-card shadow="never" :body-style="{ padding: '0px' }" @click="toStore(item)">
                                <img @click="toStore(item)" :src="'data:image/png;base64,' + item.spic" class="image"
                                    style="cursor: pointer;object-fit: fill;width: 250px; height: 250px;">
                                <div class="sold-price"><span>￥{{ item.sprice }}</span></div>
                                <div class="char">
                                    <!-- <span style="position:absolute;left:10px;">￥{{ item.sprice }}</span> -->
                                    <span v-if="!item.isdelete" style="position: absolute;
  color:  rgb(233, 72, 72);
  font-size: 20px;
  font-weight: bold;
  transform: interpolatematrix;
  rotate: 32deg;
  top: -10px;">已卖出</span>
                                    <span style="color: var(--alias-color-text-primary-default);font-size: 14px;margin-top:5px;
">{{ item.stitle }}</span>
                                </div>
                            </el-card>
                        </el-col>
                    </el-row>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
import { _listUser } from "@/api/index"
import { _listUserB } from "@/api/bought"
export default {
    data() {
        return {
            src: 'https://img1.baidu.com/it/u=2893147358,3393430132&fm=253&fmt=auto&app=138&f=JPEG?w=500&h=500',
            input2: '',
            MusicList: ['a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a'],
            currentUser: {},
            nowIdx: false,
            boughtLists: [],
            storeLists: []
        }
    },
    watch: {
        nowIdx: function (newVal, oldVal) {
            console.log(newVal)
            if (newVal) {
                this.listUserComment()
            } else {
                this.listUserStore()
            }
        }
    },
    methods: {
        toStore(e) {
            // this.$store.state.storeDetail = e

            this.$store.commit("setStoreDetail", e)
            // console.log(e)
            // 赋值
            // this.$store.state.storeDetail = e.?
            setTimeout(() => {
                this.$router.push('/store')
            }, 500)
        },
        async listUserComment() {
            try {
                let data = {
                    uid1: this.currentUser.uid
                }
                console.log(data)
                let res = await _listUserB(data)
                if (res.status == 200) {
                    // 不对，好像要和user一起查，唉我真服了
                    // 这下应该一起返回了，可能性能不高，这不重要
                    console.log('boughtLists', res.data)
                    this.boughtLists = res.data
                }
            } catch (error) {
                console.log(error)
            }
        },
        async listUserStore() {
            try {
                let data = {
                    suid: this.currentUser.uid
                }
                // console.log(data)
                let res = await _listUser(data)
                console.log(res)
                if (res.status == 200) {
                    this.storeLists = res.data
                    console.log('storeLists', res.data)
                }
            } catch (error) {
                console.log(error)
            }
        }
    },
    mounted() {
        setTimeout(() => {
            if (this.$store.state.users.saleView == '') {
                console.log("状态丢失，重新进入")
                this.$router.push('/index')
            }
            this.currentUser = this.$store.state.users.saleView
            this.listUserStore()
            this.listUserComment()
        }, 100)
    }
}
</script>

<style lang="scss" scoped>
.userview {
    .user-status {
        position: relative;
    }

    .back-img {
        background: url('https://img2.baidu.com/it/u=1493184835,681805579&fm=253&fmt=auto&app=138&f=JPEG?w=889&h=500');
        background-size: 100%;
        background-position: center center;
        width: 100%;
        height: 20vh;
    }

    .user {
        position: relative;

        .el-image {
            position: absolute;
            left: 10%;
            top: -60%;
        }

        .user-name {
            position: relative;
            width: 60%;
            margin: 0 auto;
            font-size: 50px;
            color: rgb(138, 121, 126);
            text-align: left;
        }

        .user-num {
            position: relative;
            top: -45%;
            left: 8%;
            font-size: 14px;
            // background-color: pink;
            width: 25%;

            span {
                font-weight: bold;
            }
        }
    }

    .head-img {
        width: 120px;
        height: 120px;
    }

    .el-divider--horizontal {
        width: 85%;
        margin: 0 auto;
        margin-top: 24px;
        margin-bottom: 24px;
    }

    .el-card {
        border: none;
    }

    .sold-price {
        position: absolute;
        width: 70px;
        height: 30px;
        background-color: rgba(5, 5, 5, 0.6);
        border-radius: 0px 20px 20px 0px;
        font-size: 16px;
        top: 75%;
        color: #fff;
        font-weight: 800;

        span {
            top: 10%;
            position: relative;
        }
    }

    .char {
        position: relative;
        font-size: 17px;

        span:first-child {
            color: rgb(237, 165, 165)
        }
    }

    .rows {
        .el-row {
            margin-top: 10px;
        }
    }

    .content {
        position: relative;
        width: 85%;
        margin: 0 auto;

        // margin-left: 10%;
        .el-divider--vertical {
            height: 1.5rem;
        }
    }

    .contentList {
        margin-top: 15px;
        height: auto;
    }
}
</style>
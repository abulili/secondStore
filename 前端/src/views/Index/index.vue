<template>
    <div>
        <div class="choose" v-show="!re">
            <div class="price">
                <el-row>
                    <el-col :span="2"><el-input v-model="input1" placeholder="" size="small"></el-input></el-col>
                    <el-col :span="1"><span>-</span></el-col>
                    <el-col :span="2"><el-input v-model="input2" placeholder="" size="small"></el-input></el-col>
                    <el-col :span="2"><el-button type="primary" size="small"
                            style="background-color:#99e1e5;border-color: #99e1e5;"
                            @click="priceSelect">价格筛选</el-button></el-col>
                </el-row>
            </div>
        </div>

        <div class="content">
            <div class="be-all" v-show="re"><el-button type="text" @click="ListAll">
                    < &nbsp;&nbsp;返回</el-button>
            </div>
            <el-row class="contentList" :gutter="20" v-for="(row, rowIndex) in Math.ceil(yjList.length / 5)"
                :key="rowIndex">
                <el-col :span="4" v-for="(item, colIndex) in yjList.slice(rowIndex * 5, (rowIndex + 1) * 5)"
                    :key="colIndex">
                    <el-card :body-style="{ padding: '0px' }" style="cursor:pointer">
                        <img v-if="item.spic == null" src="./../../assets/imgs/logo2.png" class="image"
                            @click="storeDetail(item)">
                        <!--  @click="location.href = './musicDetail.html?id=' + item.id" -->
                        <img v-else :src="'data:image/png;base64,' + item.spic" class="image" style="cursor: pointer;"
                            @click="storeDetail(item)">
                        <div class="char" v-if="item">
                            <el-row>
                                {{ item.stitle }}
                            </el-row>
                            <el-row>
                                <el-col :span="24" style="color:#eda5a5">￥<span style="font-size:25px;">{{
            item.sprice }}</span></el-col>
                            </el-row>
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
import { _listPrice, _listAll } from "@/api/index"
export default {
    data() {
        return {
            input1: '',
            input2: '',
            MusicList: [{
                stitle: '',
                sprice: 3,
                spic: '/tempImg/1.png'
            }, {
                stitle: '',
                sprice: 10,
                spic: '/tempImg/2.png'
            }, {
                stitle: '',
                sprice: 50,
                spic: '/tempImg/3.png'
            }, {
                stitle: '',
                sprice: 300,
                spic: '/tempImg/4.png'
            }, {
                stitle: '',
                sprice: 150,
                spic: '/tempImg/5.png'
            }],
            yjList: [],
            re: false
        }
    },
    methods: {
        storeDetail(data) {
            console.log(data)
            // this.$store.state.storeDetail = data;
            this.$store.commit("setStoreDetail", data)
            this.$router.push('/store')
        },
        handleCurrentChange(val) {
            // 当前页
            console.log(`当前页: ${val}`)
            this.slicePage(val)
        },
        slicePage(page) {
            this.yjList = this.MusicList.slice((page - 1) * 26, page * 26 - 1)
        },
        priceSelect() {
            // 按理说只输一个也行，但是懒得做了
            if (this.input1 != '' && this.input2 != '') {
                let p1 = Number(this.input1)
                let p2 = Number(this.input2)
                if (!isNaN(p1) && !isNaN(p2)) {
                    if (p1 > p2) {
                        let t = this.input2
                        this.input2 = this.input1
                        this.input1 = t
                        // p1 = p1 ^ p2
                        // p2 = p1 ^ p2
                        // p1 = p1 ^ p2
                    }
                    this.price()
                } else {
                    this.$message({
                        message: '请输入正确的价格格式',
                        type: 'warning',
                        offset: 80,
                    });
                }
            } else {
                this.$message({
                    message: '请全部输入',
                    type: 'warning',
                    offset: 80,
                });
            }
        },
        async price() {
            try {
                this.re = true
                let data = {
                    l: this.input1,
                    r: this.input2
                }
                let res = await _listPrice(data)
                if (res.status == 200) {
                    this.MusicList = res.data
                    this.slicePage(1)
                }
            } catch (error) {
                console.log(error)
            }
        },
        async ListAll() {
            try {
                this.re = false
                let res = await _listAll()
                if (res.status == 200) {
                    this.MusicList = res.data
                    this.slicePage(1)
                }
            } catch (error) {
                console.log(error)
            }

        }
    },
    mounted() {
        this.ListAll()
        // this.slicePage(1)
    },
    watch: {
        '$store.state.users.SerachList': function (newVal, oldVal) {
            console.log(newVal)
            this.re = true
            this.MusicList = newVal;
            this.slicePage(1)
        }
    }
}
</script>

<style lang="scss" scoped>
.choose {
    margin-top: 10px;
    font-size: 14px;

    input {
        border: 0.5px solid #000;
    }

    button {
        margin-left: 8px;
    }

    .price {
        width: 80%;
        margin: 0 auto;
        margin-top: 25px;

        span {
            font-size: 20px;
        }
    }

}

.content {
    position: relative;
    width: 89%;
    margin: 0 auto;
    margin-left: 10%;

    .be-all {
        position: relative;
        width: 5%;
        margin-left: 0px;
        margin-top: 5px;
        font-size: 25px;
    }
}

.contentList {
    margin-top: 15px;
    height: auto;

    .char {
        position: relative;
        width: 100%;
        height: 25px;
        font-size: 15px;
    }

    .el-card img {
        width: 100%;
        height: 200px;
        object-fit: cover;
    }

}

.pages {
    margin-top: 20px;
}
</style>
<template>
    <div class="car">
        <div class="buycar">
            <img src="./../../assets/imgs/car.png" alt="" />
            <span style="float: left">购物车（{{ carList.length }}件）</span>
        </div>
        <div class="content">
            <!-- <div> <el-checkbox :indeterminate="isIndeterminate" v-model="checkAll"
                    @change="handleCheckAllChange">全选</el-checkbox></div> -->
            <!--  @change="handleCheckedCitiesChange" -->
            <el-checkbox-group v-model="checkedStores">
                <el-checkbox v-for="(item, index) in carList" :label="index" :key="index">
                    <!-- <div v-for="(item, index) in 6" v-key="index" class="infinite-list"> -->
                    <el-card class="box-card">
                        <!-- <div slot="header" class="clearfix"> -->
                        <!-- <el-row>
                            不想再查了
                            <el-col :span="20">
                                <span>卖家：建材王哥</span>
                            </el-col>
                            <el-col :span="2"><el-button type="wrong" size="mini"
                                    style="float: right;">删除</el-button></el-col>
                            <el-col :span="2"> <el-radio v-model="radio"
                                    style="float: right; margin: 6px 0">全选</el-radio></el-col>
                        </el-row> -->

                        <!-- <el-radio v-model="radio" style="float: right; margin: 6px 0;">全选</el-radio> -->
                        <!-- <el-button type="wrong" size="mini"
                                style="position:relative;float: right;right:30px;">删除</el-button> -->
                        <!-- </div> -->
                        <!-- v-for="o in 5" :key="o" -->
                        <div class="text item" style="position: relative">
                            <!-- <div style="float:left;"><img src="./../../assets/logo.png" alt=""></div>
                                <div>aaaaaaa</div>
                                <div  style="float:right;"><el-input-number v-model="num" @change="handleChange" :min="1" :max="10"
                                        label="描述文字"></el-input-number></div> -->
                            <el-row style="width: 100%">
                                <el-col :span="1">
                                    <!-- <el-radio v-model="radio"></el-radio> -->
                                </el-col>
                                <el-col :span="3">
                                    <!-- <img :src="item.spic"> -->
                                    <img :src="'data:image/png;base64,' + item.spic" alt=""
                                        style="width: 100px; height: 100px; display: inherit" />
                                </el-col>
                                <el-col :span="10" class="list-text">
                                    {{ item.stitle }}
                                </el-col>
                                <el-col :span="2" class="list-text">
                                    <span style="font-size: 16px; margin-top: 7px">￥{{ item.sprice }}</span>
                                </el-col>
                                <el-col :span="5">
                                    <!-- 最多买99个 -->
                                    <el-input-number v-model="item.snum" @change="handleChange(item)" :min="1" :max="10"
                                        label="购买数量"></el-input-number>
                                </el-col>
                                <el-col :span="3"><el-button type="wrong" size="mini" style="margin-top: 4px"
                                        @click="del(index, item.cid)">删除</el-button><el-button type="wrong" size="mini"
                                        style="margin-top: 4px" @click="checked(item)">详情</el-button></el-col>
                            </el-row>
                        </div>
                    </el-card>
                    <!-- </div> -->
                </el-checkbox>
            </el-checkbox-group>
        </div>
        <div style="height: 20px"></div>
        <div class="sum-all">
            <span class="sum"><el-button style="background-color: #fbafaf; border-color: fbafaf; color: #fff" round
                    @click="sumAll">结算</el-button></span>
        </div>
    </div>
</template>

<script>
import Header from "@/components/Header";
import { _listCarId, _delCar, _updateCar } from "@/api/car";
import { _addOrder } from "@/api/order";
import { _listId, _updateStore } from "@/api/index";
// import eventBus from '@/api/eventBus.js';
export default {
    data() {
        return {
            radio: "1",
            carList: [
                {
                    spic: "/tempImg/1.png",
                    stitle: "毕业清仓甩卖",
                    sprice: 5,
                },
                {
                    spic: "/tempImg/2.png",
                    stitle: "相机三百便宜卖",
                    sprice: 5,
                },
            ],
            checkAll: false,
            checkedStores: [],
            isIndeterminate: true,
            boughtList: [],
            timer: null,
        };
    },
    components: {
        Header,
    },
    mounted() {
        this.listId();
    },
    methods: {
        sumAll() {
            console.log("checkedStores", this.checkedStores);
            let summ = 0;
            let numm = 0;
            // for (let i = 0; i < this.checkedStores.length; ++i) {
            for (let i = 0; i < this.checkedStores.length; ++i) {
                let idx = this.checkedStores[i];
                let t = Number(this.carList[idx].sprice);
                let num = Number(this.carList[idx].snum);
                if (!isNaN(t)) {
                    summ = summ + t * num;
                    numm = numm + num;
                }
            }
            // 如果可以的话，把选了
            this.$confirm("共计" + summ + "元，" + numm + "件商品，是否立即购买？", "提示", {
                confirmButtonText: "确定",
                cancelButtonText: "取消",
                type: "warning",
            })
                .then(() => {
                    // this.$message({
                    //     type: 'success',
                    //     message: '删除成功!'
                    // });
                    this.buyOp();
                    // setTimeout(() => {
                    //     this.$router.push('/selfchange')
                    // }, 500)
                })
                .catch(() => {
                    this.$message({
                        type: "info",
                        message: "已取消",
                    });
                });
        },
        buyOp() {
            // 查库存
            // 改库存
            // 改订单order
            for (let i = 0; i < this.checkedStores.length; ++i) {
                // this.carList[this.checkedStores[i]]
                this.restNum(
                    this.carList[this.checkedStores[i]],
                    this.carList[this.checkedStores[i]].snum
                );
            }
        },
        async restNum(item, num) {
            try {
                console.log("restNum", item);
                let res = await _listId({
                    sid: item.sidstore,
                });
                // console.log('restNum', res)
                if (res.status == 200) {
                    let totalNum = res.data;
                    totalNum = totalNum[0].snum;
                    if (totalNum == 0) {
                        console.log("此商品没了");
                    } else {
                        if (totalNum >= num) {
                            totalNum = totalNum - num;
                        } else totalNum = 0;
                        // console.log('totalNum', totalNum, num)
                        this.changeStoreNum(item, totalNum, num);
                    }
                }
            } catch (error) {
                console.log(error);
            }
        },
        async changeStoreNum(item, num, num2) {
            try {
                // 可能会出错，因为传的不是Store格式，是StoreUserDTO
                // item.snum = num
                let isdelete = 0;
                if (num == 0) {
                    isdelete = "1";
                }
                console.log("changeStoreNum", item);
                let res = await _updateStore({
                    sid: item.sidstore,
                    stitle: item.stitle,
                    scontent: item.scontent,
                    snum: num,
                    suid: item.suid,
                    sprice: item.sprice,
                    slng: item.slng,
                    slat: item.slat,
                    isdelete: isdelete,
                });
                console.log("changeStoreNum", res);
                if (res.status == 200) {
                    if (res.data) {
                        if (num >= num2) this.addOrder(item, num2);
                        else this.addOrder(item, num);
                    } else {
                        // 失败
                    }
                }
            } catch (error) {
                console.log(error);
            }
        },
        async addOrder(item, tempNum) {
            try {
                let data = {
                    uid: item.suid,
                    uid2: this.$store.state.users.userInfo.uid,
                    sid: item.sidstore,
                    bnum: tempNum,
                    bstatus: "未付款",
                };
                console.log("addOrder", data);
                let res = await _addOrder(data);
                console.log("addOrder", res);
                if (res.status == 200) {
                    if (res.data) {
                        // this.$router.push('/selfchange')
                        this.$message({
                            type: "success",
                            message: "购买成功",
                        });
                    } else {
                        // 失败
                    }
                }
            } catch (error) {
                console.log(error);
            }
        },
        checked(data) {
            // 把data带到那个商品页面
            // this.$store.state.storeDetail = data
            this.$store.commit("setStoreDetail", data);
            this.$router.push("/store");
        },
        async del(idx, cid) {
            try {
                let data = {
                    cid: cid,
                };
                let res = await _delCar(data);
                if (res.status == 200) {
                    this.carList.splice(idx, 1);
                }
            } catch (error) {
                console.log(error);
            }
        },
        async updateCar(item) {
            try {
                let res = await _updateCar(item);
                if (res.status == 200) {
                    console.log("成功改变购物车");
                }
            } catch (error) {
                console.log(error);
            }
        },
        handleCheckAllChange(val) {
            this.checkedStores = val ? cityOptions : [];
            this.isIndeterminate = false;
        },
        handleCheckedCitiesChange(value) {
            let checkedCount = value.length;
            this.checkAll = checkedCount === this.cities.length;
            this.isIndeterminate = checkedCount > 0 && checkedCount < this.cities.length;
        },
        async listId() {
            try {
                let res = await _listCarId({ uid: this.$store.state.users.userInfo.uid });
                if (res.status == 200) {
                    console.log(res.data);
                    // 我就不分类了
                    this.carList = res.data;
                    console.log(this.carList);
                    // let base64String = this.carList[0].spic;
                    // if (btoa(atob(base64String)) == base64String) {
                    //     console.log(" Base64!");
                    // } else {
                    //     console.log("Not a valid Base64 string.");
                    // }
                }
            } catch (error) {
                console.log(error);
            }
        },
        handleChange(item) {
            // 防抖
            var context = this;
            return () => {
                clearTimeout(this.timer);
                this.timer = setTimeout(() => {
                    console.log("?");
                    // this.updateCar(item);
                }, 1000);
            };
        },
        debounce(fn, delay) {
            var timer = null;
            var context = this; // 保存正确的上下文
            return function () {
                clearTimeout(timer);
                timer = setTimeout(function () {
                    fn.apply(context); // 使用保存的上下文调用函数
                }, delay);
            };
        },
    },
};
</script>

<style lang="scss">
.car {
    position: relative;
    width: 90%;
    height: 100vh;
    margin: 0 auto;
    margin-top: 5vh;

    .list-text {
        text-align: left;
        margin-top: 7px;
    }

    .el-checkbox__input {
        top: -60px;
    }

    .sum {
        position: absolute;
        right: 7px;
    }

    .buycar {
        position: relative;
        display: flex;
        margin-left: 10vh;
        bottom: 10px;
        z-index: 2;
        font-size: 18px;

        img {
            height: 10vh;
        }

        span {
            margin-top: 7px;
            margin-top: 15px;
        }
    }

    .content {
        margin: 0 auto;
        margin-top: 3vh;
        width: 85%;

        span {
            // float: left;
            font-size: 15px;
        }

        .el-checkbox,
        .el-checkbox__label {
            width: 100%;
        }
    }

    .sum-all {
        position: sticky;
        bottom: 10%;
    }

    .text {
        font-size: 14px;
    }

    .item {
        margin-top: 5px;

        img {
            width: 15vh;
        }
    }

    .clearfix:before,
    .clearfix:after {
        display: table;
        content: "";
    }

    .clearfix:after {
        clear: both;
    }

    .box-card {
        margin-top: 3vh;
        background-color: rgba(243, 232, 203, 0.7);
    }
}
</style>

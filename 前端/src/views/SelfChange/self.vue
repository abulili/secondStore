<template>
    <div class="own-manage">
        <el-container>
            <!-- <div class="nav"> -->
            <el-aside style="width: 14vw;">
                <el-menu :default-active="active" class="el-menu-vertical-demo">
                    <!-- <el-menu-item index="1" @click="active = 1">
                        <i class="el-icon-menu"></i>
                        <span slot="title">内容管理</span>
                    </el-menu-item> -->
                    <el-menu-item index="2" @click="active = '2'">
                        <i class="el-icon-menu"></i>
                        <span slot="title">账号设置</span>
                    </el-menu-item>
                    <el-menu-item index="3" @click="listCommentSuid"
                        v-if="$store.state.users.userInfo.uadmin == '普通用户'">
                        <i class="el-icon-document"></i>
                        <span slot="title">评论管理</span>
                    </el-menu-item>
                    <!-- <el-menu-item index="4" @click="active = 4">
                        <i class="el-icon-s-custom"></i>
                        <span slot="title">信息认证</span>
                    </el-menu-item> -->
                    <el-menu-item index="5" @click="orderListGet" v-if="$store.state.users.userInfo.uadmin == '普通用户'">
                        <i class="el-icon-document-copy"></i>
                        <span slot="title">订单管理</span>
                    </el-menu-item>
                    <el-menu-item index="7" @click="listStore" v-if="$store.state.users.userInfo.uadmin == '普通用户'">
                        <i class="el-icon-sold-out"></i>
                        <span slot="title">发布管理</span>
                    </el-menu-item>
                    <el-menu-item index="6" @click="dialogVisible = true">
                        <i class="el-icon-s-release"></i>
                        <span slot="title">注销</span>
                    </el-menu-item>
                </el-menu>
            </el-aside>
            <!-- </div> -->
            <el-container>
                <el-main>
                    <!-- 账号设置 -->
                    <div v-show="active == 2">
                        <el-card>
                            <div style="float:left">
                                <!-- <img src="https://fuss10.elemecdn.com/e/5d/4a731a90594a4af544c0c25941171jpeg.jpeg"
                                    alt="" style="width: 100px;height:100px;margin-bottom: 10px;margin-right:10px"> -->
                                <img v-if="userInfo.uavatar" :src="'data:image/png;base64,' + userInfo.uavatar" alt=""
                                    style="width: 100px;height:100px;margin-bottom: 10px;margin-right:10px;object-fit:cover">
                                <img v-else
                                    src="https://img1.baidu.com/it/u=2893147358,3393430132&fm=253&fmt=auto&app=138&f=JPEG?w=500&h=500"
                                    alt=""
                                    style="width: 100px;height:100px;margin-bottom: 10px;margin-right:10px;object-fit:cover">
                                <font style="font-size: 20px;color: #000;">{{ userInfo.uname }}</font>
                            </div>
                        </el-card>
                        <br />
                        <el-card style="min-height: 64vh;">
                            <el-form label-position="left" label-width="80px" :model="formLabelAlign">
                                <el-form-item label="昵  称" class="hover-display">
                                    <el-row>
                                        <el-col :span="8">{{ userInfo.uname }}</el-col>
                                        <el-col :span="10">
                                            <el-input v-model="formLabelAlign.uname" v-show="app1"></el-input>
                                        </el-col>
                                        <el-col :span="3">
                                            <el-button v-show="app1" @click="updateUser('uname')">确认</el-button>
                                        </el-col>
                                        <el-col :span="3"><el-button type="info" v-show="app1"
                                                @click="app1 = !app1">取消</el-button></el-col>
                                        <el-col :span="2"><el-button class="btn" size="small" @click="app1 = true"
                                                v-show="!app1">编辑</el-button></el-col>
                                    </el-row>
                                    <!-- 原先就是普通的span，之后v-show，hover出现编辑（这一步怎么弄），点击变成input，然后出现两个按钮，提交和取消 -->
                                </el-form-item>
                                <!-- <el-form-item label="用户ID" class="hover-display">
                                    <el-input v-model="formLabelAlign.region" v-show="app2"></el-input>
                                    <el-button v-show="app2">确认</el-button>
                                    <el-button type="info" v-show="app2" @click="app2 = !app2">取消</el-button>
                                    <el-button class="btn" size="small" @click="app2 = true"
                                        v-show="!app2">编辑</el-button>
                                </el-form-item> -->
                                <el-form-item label="密码" class="hover-display">
                                    <el-col :span="9">
                                        <el-input v-model="formLabelAlign.upwd" v-show="app5"
                                            placeholder="密码"></el-input></el-col>
                                    <el-col :span="9">
                                        <el-input v-model="formLabelAlign.upwd2" v-show="app5"
                                            placeholder="确认密码"></el-input>
                                    </el-col>
                                    <el-col :span="3">
                                        <el-button v-show="app5" @click="updateUser('upwd')">确认</el-button>
                                    </el-col>
                                    <el-col :span="3"><el-button type="info" v-show="app5"
                                            @click="app5 = !app5">取消</el-button></el-col>
                                    <el-col :span="2"><el-button class="btn" size="small" @click="app5 = true"
                                            v-show="!app5">编辑</el-button></el-col>
                                </el-form-item>
                                <!-- <el-form-item label="确认密码" class="hover-display">
                                    <el-input v-model="formLabelAlign.upwd2" v-show="app5"></el-input>
                                    <el-button v-show="app5">确认</el-button>
                                    <el-button type="info" v-show="app5" @click="app5 = !app5">取消</el-button>
                                    <el-button class="btn" size="small" @click="app5 = true"
                                        v-show="!app5">编辑</el-button>
                                </el-form-item> -->
                                <el-form-item label="手机" class="hover-display">
                                    <el-row>
                                        <el-col :span="8">{{ userInfo.uphone }}</el-col>
                                        <el-col :span="10">
                                            <el-input v-model="formLabelAlign.uphone" v-show="app6"></el-input>
                                        </el-col>
                                        <el-col :span="3">
                                            <el-button v-show="app6" @click="updateUser('uphone')">确认</el-button>
                                        </el-col>
                                        <el-col :span="3"><el-button type="info" v-show="app6"
                                                @click="app6 = !app6">取消</el-button></el-col>
                                        <el-col :span="2"><el-button class="btn" size="small" @click="app6 = true"
                                                v-show="!app6">编辑</el-button></el-col>
                                    </el-row>
                                </el-form-item>
                                <el-form-item label="邮箱" class="hover-display">
                                    <el-row>
                                        <el-col :span="8">{{ userInfo.uemail }}</el-col>
                                        <el-col :span="10">
                                            <el-input v-model="formLabelAlign.uemail" v-show="app7"></el-input>
                                        </el-col>
                                        <el-col :span="3">
                                            <el-button v-show="app7" @click="updateUser('uemail')">确认</el-button>
                                        </el-col>
                                        <el-col :span="3"><el-button type="info" v-show="app7"
                                                @click="app7 = !app7">取消</el-button></el-col>
                                        <el-col :span="2"><el-button class="btn" size="small" @click="app7 = true"
                                                v-show="!app7">编辑</el-button></el-col>
                                    </el-row>
                                </el-form-item>
                                <!-- <el-form-item label="简介" class="hover-display">
                                    <el-input v-model="formLabelAlign.type" v-show="app3"></el-input>
                                    <el-button v-show="app3">确认</el-button>
                                    <el-button type="info" v-show="app3" @click="app3 = !app3">取消</el-button>
                                    <el-button class="btn" size="small" @click="app3 = true" v-show="!app3">编辑</el-button>
                                </el-form-item> -->
                                <el-form-item label="入学时间" class="hover-display">
                                    <div class="container">
                                        <div class="block">
                                            <el-row>
                                                <el-col :span="10" v-if="userInfo.uinittime != undefined">{{
                    userInfo.uinittime
                }}</el-col>
                                                <el-col :span="7" v-show="app4"><el-date-picker
                                                        v-model="formLabelAlign.uinittime" type="month"
                                                        placeholder="选择月">
                                                    </el-date-picker></el-col>
                                                <el-col :span="3"><el-button v-show="app4"
                                                        @click="updateUser('year')">确认</el-button></el-col>
                                                <el-col :span="3"><el-button type="info" v-show="app4"
                                                        @click="app4 = !app4">取消</el-button></el-col>
                                                <el-col :span="1"><el-button class="btn" size="small"
                                                        @click="app4 = true" v-show="!app4">编辑</el-button></el-col>
                                            </el-row>
                                        </div>
                                    </div>
                                </el-form-item>
                            </el-form>
                        </el-card>
                    </div>
                    <!-- 隐私设置 -->
                    <!-- <div v-show="active == 2">
                        密码
                        手机
                        邮箱
                        账号注销
                    </div> -->
                    <!-- 信息认证 -->
                    <div v-show="active == 4">
                        <el-card>
                            <h2>实名认证，交易更放心</h2>
                        </el-card>
                        <el-card style="margin-top:10px;min-height: 73vh;">
                            <el-form label-position="left" label-width="80px" :model="formLabelAlign">
                                <!-- 如果已经认证过了，不能再认证了，v-if -->
                                <el-form-item label="真实姓名" class="hover-display">
                                    <el-input v-model="formLabelAlign.name" minlength="2"></el-input>

                                    <!-- 原先就是普通的span，之后v-show，hover出现编辑（这一步怎么弄），点击变成input，然后出现两个按钮，提交和取消 -->
                                </el-form-item>
                                <el-form-item label="身份证号" class="hover-display">
                                    <el-input v-model="formLabelAlign.type" maxlength="18" minlength="18"></el-input>
                                </el-form-item>
                                <el-form-item>
                                    <el-button>确认</el-button>
                                    <el-button>取消</el-button>
                                </el-form-item>
                                <!-- 身份证到时候再来 -->
                            </el-form>


                        </el-card>
                    </div>
                    <!-- 评论管理 -->
                    <div v-show="active == 3">
                        <el-card class="pin-lun">
                            <el-card v-for="(item, index) in commentsList" style="margin-top:5px">
                                <div @click="runTo(item)" style="cursor:pointer">
                                    <div style="font-size:15px;">{{ item.store.stitle }}</div>
                                    <div style="font-size:13px;margin-top:8px"><span
                                            style="color:#99e1e5;font-weight: bold;">|</span>
                                        {{ item.comment.ccontent }}</div>
                                </div>
                                <div style="float: right;top: -30px;position: relative;"> <el-button size="mini"
                                        @click="_delComment(item.comment)">删除</el-button> </div>
                            </el-card>
                            <!-- <el-card>
                                <div>
                                    <div style="font-size:15px;"> 毕业清仓甩卖</div>
                                    <div style="font-size:13px;">| 这本书有点老了不建议买我也不懂我瞎说的因为这是测试数据</div>
                                </div>
                                <div style="float: right;top: -30px;position: relative;"> <el-button
                                        size="mini">删除</el-button> </div>
                            </el-card> -->
                        </el-card>
                    </div>
                    <!-- 订单管理 -->
                    <div v-show="active == 5">
                        <!-- <el-button @click="activeItem = '5-4'"
                            type="primary">全部卖家评价买家，包含买卖（使用不同的颜色）</el-button> -->
                        <!-- <el-button @click="activeItem = '5-1'">已完成卖家评价买家，包含买卖（使用不同的颜色）</el-button> -->
                        <el-button :type="activeItem == '5-2' ? 'primary' : ''"
                            @click="orderListGet">买到的<!--（双方没有约定交易时间，但是拍下了,可取消订单）--></el-button>
                        <el-button :type="activeItem == '5-3' ? 'primary' : ''" @click="orderListSale">卖出的
                            <!--(确定交易地点转为未交付,发布被拍)--> </el-button>
                        <el-card style="margin-top:10px; min-height:76vh;">
                            订单管理
                            <!-- (应该是要分页的，别分，懒得分了) -->
                            <div v-show="activeItem == '5-2'">
                                <el-card style="margin-top:10px" v-for="(item, index) in getStores" :key="index">
                                    <div style="position:relative;">
                                        <div style="position:absolute;left:0px;"><img
                                                :src="'data:image/png;base64,' + item.user.uavatar" alt=""
                                                class="user-icon" style="object-fit: cover;">
                                            <span>{{ item.user.uname }}</span>
                                        </div>
                                        <div style="color:#f2cbb4;font-weight: bold;font-size: 15px;">{{
                    item.order.bstatus }}</div>
                                    </div>
                                    <div style="position:relative; margin-top:41px;">
                                        <el-row :gutter="20">
                                            <el-col :span="2"><img :src="'data:image/png;base64,' + item.store.spic"
                                                    alt="" class="product" style="object-fit: cover;">
                                            </el-col>
                                            <el-col :span="10" style="margin-top: 20px;">
                                                <i style="padding-left: 40px; font-size: 16px;">{{ item.store.stitle
                                                    }}</i>
                                            </el-col>
                                        </el-row>
                                    </div>
                                    <div style="float: right; top:10px;position:relative">
                                        <div style="font-size: 18px;top: -150px;
  position: absolute;
  right: 15px;">
                                            X {{ item.order.bnum }}
                                        </div>
                                        <div style="font-size: 18px; margin-top: -90px;margin-right: 10px;">
                                            <el-button style="margin-right: 10px;" @click="chatWith1(item.user)">
                                                联系卖家</el-button>
                                            <el-button style="margin-right: 10px;"
                                                @click="updateOrder(item.order, 0, index)"
                                                v-if="item.order.bstatus == '未付款'"> 取消订单</el-button>
                                            <el-button style="margin-right: 10px;" @click="addBought(item)"
                                                v-else>评价</el-button>
                                            <!-- 卖出价格 -->
                                            <i style="font-size: 20px;color:#fbafaf;font-weight:bold;">￥{{
                    item.store.sprice }}/个</i>
                                        </div>
                                    </div>
                                </el-card>
                            </div>
                            <div v-show="activeItem == '5-3'">
                                <el-card style="margin-top:10px" v-for="(item, index) in saleStores" :key="index">
                                    <div style="position:relative;">
                                        <div style="position:absolute;left:0px;"><img
                                                :src="'data:image/png;base64,' + item.user.uavatar" alt=""
                                                class="user-icon" style="object-fit: cover;">
                                            <span>{{ item.user.uname }}</span>
                                        </div>
                                        <div style="color:#f2cbb4;font-weight: bold;font-size: 15px;">{{
                    item.order.bstatus }}</div>
                                    </div>
                                    <div style="position:relative; margin-top:41px;">
                                        <el-row :gutter="20">
                                            <el-col :span="2"><img :src="'data:image/png;base64,' + item.store.spic"
                                                    alt="" class="product" style="object-fit: cover;">
                                            </el-col>
                                            <el-col :span="10" style="margin-top: 20px;">
                                                <i style="padding-left: 40px; font-size: 16px;">{{ item.store.stitle
                                                    }}</i>
                                            </el-col>
                                        </el-row>
                                    </div>
                                    <div style="float: right; top:10px;background-color: #99e1e5;position:relative">
                                        <div style="font-size: 18px;top: -150px;
  position: absolute;
  right: 15px;">
                                            X {{ item.order.bnum }}
                                        </div>
                                        <div style="font-size: 18px; margin-top: -90px;margin-right: 10px;">
                                            <el-button style="margin-right: 10px;" @click="chatWith1(item.user)"> 联系买家
                                            </el-button>
                                            <el-button style=" margin-right: 10px;"
                                                @click="updateOrder(item.order, 1, index)"
                                                v-if="item.order.bstatus == '未付款'"> 确认到款</el-button>
                                            <!-- 卖出价格 -->
                                            <i style="font-size: 20px;color:#fbafaf;font-weight:bold;">￥{{
                    item.store.sprice }}</i>
                                        </div>
                                    </div>
                                </el-card>
                            </div>
                        </el-card>
                    </div>
                    <!-- 商品管理 -->
                    <div v-show="active == 7">
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
                                    <el-button @click="delStore1(scope.row, scope.index)" type="text"
                                        size="small">下架商品</el-button>
                                    <el-button @click="handleClick(scope.row)" type="text" size="small">查看详情</el-button>
                                </template>
                            </el-table-column>
                        </el-table>
                    </div>
                </el-main>
            </el-container>
            <el-dialog title="提示" :visible.sync="dialogVisible" width="30%">
                <span>是否确认注销？</span>
                <span slot="footer" class="dialog-footer">
                    <el-button @click="dialogVisible = false">取 消</el-button>
                    <el-button type="primary" @click="delUser">确 定</el-button>
                </span>
            </el-dialog>
        </el-container>
    </div>
</template>

<script>
import { _updateUser, _delUser, _serachUser } from "@/api/user"
import { _delComment, _listCommentSuid } from "@/api/comment"
import { _listSale, _listOrderBought, _updateOrder } from "@/api/order"
import { _listAll, _delStore } from '@/api/index'
import { _addBought, _listIsCom } from '@/api/bought'
export default {
    data() {
        return {
            active: "2",
            activeItem: '',
            year: '',
            formLabelAlign: {
                uname: '',
                upwd: '',
                upwd2: '',
                uphone: '',
                uemail: '',
                uinittime: ''
            },
            app1: false,
            app2: false,
            app3: false,
            app4: false,
            app5: false,
            app6: false,
            app7: false,
            userInfo: {},
            dialogVisible: false,
            commentsList: [],
            getStores: [],
            saleStores: [],
            tableData: [],
            mcontnet: ''
        }
    },
    methods: {
        async addBought(data) {
            // console.log(data)
            // 先看评论过没有
            try {
                let res = await _listIsCom({ iscom: data.order.bid })
                if (res.status == 200) {
                    res = res.data
                    if (res.length == 0) {
                        let _this = this
                        this.$prompt('请输入评价', '提示', {
                            confirmButtonText: '确定',
                            cancelButtonText: '取消',
                            // inputPattern: /[\w!#$%&'*+/=?^_`{|}~-]+(?:\.[\w!#$%&'*+/=?^_`{|}~-]+)*@(?:[\w](?:[\w-]*[\w])?\.)+[\w](?:[\w-]*[\w])?/,
                            // inputErrorMessage: '邮箱格式不正确'
                        }).then(({ value }) => {
                            _this.addBought1(data, value)
                        }).catch((error) => {
                            console.log(error)
                            this.$message({
                                type: 'info',
                                message: '取消输入'
                            });
                        });
                    } else {
                        this.$message({
                            type: "warning",
                            message: "您已评价过该商品"
                        })
                    }
                }
            } catch (error) {
                console.log(error)
            }
        },
        async addBought1(data, value) {
            try {
                let res = await _addBought({
                    sid: data.store.sid,
                    iscom: data.order.bid,
                    uid1: data.user.uid,
                    uid2: this.$store.state.users.userInfo.uid,
                    mcontent: value
                })
                if (res.status == 200) {
                    if (res.data) {
                        this.$message({
                            type: 'success',
                            message: '评价成功'
                        });
                    }
                }
            } catch (error) {
                console.log(error)
            }
        },
        chatWith1(data) {
            this.$router.push({
                name: "chat",
                params: data,
            });
        },
        async listStore() {
            try {
                this.active = 7
                // 多一个条件就是只显示自己发布的,懒得后台写了
                let res = await _listAll()
                console.log(res)
                if (res.status == 200) {
                    // this.tableData = res.data
                    //     console.log(res.data)
                    res = res.data
                    let temp = []
                    let uid = this.$store.state.users.userInfo.uid
                    for (let i = 0; i < res.length; ++i) {
                        if (res[i].suid == uid) {
                            temp.push(res[i])
                        }
                    }
                    this.tableData = temp
                }
            } catch (error) {
                console.log(error)
            }
        },
        handleClick(item) {
            // this.$store.state.storeDetail = item
            this.$store.commit("setStoreDetail", item)
            setTimeout(() => {
                this.$router.push('/store')
            }, 1000)
        },
        delStore1(item, idx) {
            this.$confirm('将下架此商品, 是否继续?', '提示', {
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
        async updateOrder(item, idx, index) {
            try {
                if (idx == 0) {
                    item.bstatus = "已取消"
                    this.getStores[index].order.bstatus = "已取消"
                } else {
                    item.bstatus = "成功"
                    this.saleStores[index].order.bstatus = "成功"
                }
                let res = await _updateOrder(item)
                if (res.status == 200) {
                    if (res.data) {
                        this.$message({
                            message: '成功',
                            type: 'success'
                        });
                    }
                }
            } catch (error) {
                console.log(error)
            }
        },
        async orderListGet() {
            try {
                // 买到的
                this.active = '5'
                this.activeItem = '5-2'
                // 这是全部的，或者一开始就全部分开（卖出的，买到的）
                let res = await _listOrderBought({
                    uid2: this.userInfo.uid
                })
                if (res.status == 200) {
                    this.getStores = res.data
                }
            } catch (error) {
                console.log(error)
            }
        },
        async orderListSale() {
            try {
                this.activeItem = '5-3'
                let res = await _listSale({
                    uid: this.userInfo.uid
                })
                if (res.status == 200) {
                    this.saleStores = res.data
                }
            } catch (error) {
                console.log(error)
            }
        },
        runTo(item) {
            // this.$store.state.storeDetail = item.store
            this.$store.commit("setStoreDetail", item.store)
            this.$router.push('/store')
        },
        async listCommentSuid() {
            try {
                this.active = "3"
                let res = await _listCommentSuid({
                    suid: this.userInfo.uid
                })
                if (res.status == 200) {
                    this.commentsList = res.data
                }
            } catch (error) {
                console.log(error)
            }
        },
        async _delComment(item) {
            try {
                console.log(item)
                let res = await _delComment(item)
                if (res.status == 200) {
                    if (res.data) {
                        console.log("评论删除成功")
                        this.listCommentSuid()
                    }
                }
            } catch (error) {
                console.log(error)
            }
        },
        async updateUser(type) {
            try {
                // 这地方也可以防抖
                let flag = true
                if (type == 'uname') {
                    if (!this.checkNameBeta()) {
                        flag = false
                    }
                } else if (type == 'upwd') {
                    if (!this.checkPwd()) (
                        flag = false
                    )
                }
                // 这里好像不对，改完名字后才变
                console.log(this.formLabelAlign.uinittime)
                if (flag) {
                    console.log(this.formLabelAlign)
                    let res = await _updateUser(this.formLabelAlign)
                    if (res.status == 200) {
                        if (res.data) {
                            this.userInfo = this.formLabelAlign
                            this.$message({
                                message: '修改成功',
                                type: 'success'
                            });
                            // this.$store.state.userInfo = this.userInfo
                            this.$store.commit("setUserInfo", this.userInfo)
                            if (type == 'uname') {
                                this.app1 = false
                            } else if (type == 'upwd') {
                                this.app5 = false
                                this.upwd2 = ''
                            } else if (type == 'uphone') {
                                this.app6 = false
                            } else if (type == 'uemail') {
                                this.app7 = false
                            } else if (type == 'year') {
                                this.app4 = false
                            }
                        }
                    }
                }
                else {
                    this.$message({
                        message: '请重新输入',
                        type: 'warning'
                    })
                }
            } catch (error) {
                console.log(error)
            }
        },
        checkPwd() {
            if (this.formLabelAlign.upwd != this.formLabelAlign.upwd2) {
                // this.inputPwdNotiy = '两次密码输入不一致'
                return false
            } else {
                // this.inputPwdNotiy = ''
                return true
            }
        },
        async checkNameBeta() {
            // 查数据库有有没有相同的名字
            try {
                let data = {
                    uname: this.formLabelAlign.uname
                }
                let res = await _serachUser(data)
                console.log(res)
                if (res.status == 200) {
                    if (res.data.length > 0) {
                        // this.inputNameNotiy = '此用户名已被占用'
                        return false
                    } else {
                        // this.inputNameNotiy = ''
                        return true
                    }
                }
            } catch (error) {
                console.log(error)
            }
        },
        async delUser() {
            try {
                let res = await _delUser(this.userInfo)
                if (res.status == 200) {
                    this.dialogVisible = false
                    this.$store.commit("setIsLogin", 0)
                    this.$message({
                        message: '注销成功',
                        type: 'warning'
                    });
                    setTimeout(() => {
                        this.$router.push('/index')
                    }, 1000)
                }
            } catch (error) {
                console.log(error)
            }
        }
    },
    mounted() {
        this.userInfo = this.$store.state.users.userInfo
        this.formLabelAlign = this.$store.state.users.userInfo
        this.formLabelAlign.upwd = ""
        // this.orderListGet()
    },
}
</script>

<style lang="scss">
.own-manage {
    .el-menu {
        min-height: 88vh;
        width: 14vw;
        padding-top: 10px;
    }

    .el-main {
        margin: 0 auto;

        .el-input {
            width: 50%;
        }
    }



    .hover-display .btn {
        display: none;
    }

    .hover-display:hover .btn {
        display: block;
    }

    .pin-lun {
        min-height: 84vh;
        text-align: left;
    }

    .pin-lun>.el-card {
        margin-top: 10px;
    }

    .user-icon {
        width: 40px;
        height: 40px;
        border-radius: 50%;
        margin-right: 10px;
    }

    .product {
        width: 100px;
        height: 100px;
        margin-top: 10px;
    }
}
</style>
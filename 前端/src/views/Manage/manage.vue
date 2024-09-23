<template>
    <el-container>
        <!-- <div class="nav"> -->
        <el-aside width="230px">
            <el-menu :default-active="active" class="el-menu-vertical-demo" @open="handleOpen" @close="handleClose">
                <el-menu-item index="1" @click="active = '1'">
                    <i class="el-icon-menu"></i>
                    <span slot="title">首页</span>
                </el-menu-item>
                <el-submenu index="2" v-if="$store.state.users.userInfo.uadmin == '超级管理员'">
                    <template slot="title">
                        <i class="el-icon-location"></i>
                        <span>用户管理</span>
                    </template>
                    <el-menu-item-group>
                        <el-menu-item index="2-1" @click="active = '2-1'">账号管理</el-menu-item>
                        <el-menu-item index="3-1" @click="active = '3-1'"
                            v-if="$store.state.users.userInfo.uadmin == '超级管理员'">权限分配</el-menu-item>
                        <!-- <el-menu-item index="2-2" @click="active = '2-2'">用户请求</el-menu-item> -->
                    </el-menu-item-group>
                </el-submenu>
                <!-- <el-submenu index="3" v-if="$store.state.users.userInfo.uadmin == '超级管理员'">

                    <template slot="title">
                        <i class="el-icon-location"></i>
                        <span>超级管理员管理</span>
                    </template>
                    <el-menu-item-group>
                        <el-menu-item index="3-1" @click="active = '3-1'">权限分配</el-menu-item>
                    </el-menu-item-group>
                </el-submenu> -->
                <el-submenu index="4">

                    <template slot="title">
                        <i class="el-icon-location"></i>
                        <span>内容管理</span>
                    </template>
                    <el-menu-item-group>
                        <el-menu-item index="4-1" @click="active = '4-1'">商品管理</el-menu-item>
                        <el-menu-item index="4-2" @click="active = '4-2'">评论管理</el-menu-item>
                        <el-menu-item index="4-3" @click="active = '4-3'">举报管理</el-menu-item>
                    </el-menu-item-group>
                </el-submenu>
            </el-menu>
        </el-aside>
        <!-- </div> -->
        <el-container>
            <el-main>
                <el-card class="content">
                    <Index v-show="active == '1'"></Index>
                    <User1 v-show="active == '2-1'"></User1>
                    <User2 v-show="active == '2-2'"></User2>
                    <Rooter v-show="active == '3-1'"></Rooter>
                    <Store v-show="active == '4-1'"></Store>
                    <Comment v-show="active == '4-2'"></Comment>
                    <Accuse v-show="active == '4-3'"></Accuse>
                </el-card>
            </el-main>
        </el-container>
    </el-container>
</template>

<script>
import Index from "./Index/index"
import User1 from "./User/user1"
import User2 from "./User/user2"
import Rooter from "./Rooter/rooter"
import Store from "./Store/store"
import Comment from './Comment/comment'
import Accuse from './Accuse/accuse'
export default {
    data() {
        return {
            active: '1',
        }
    },
    methods: {
        handleOpen() {

        },
        handleClose() {

        }
    },
    watch: {
        '$store.state.users.active': function (old, newVal) {
            this.active = newVal
        }
    },
    components: {
        Index,
        User1,
        User2,
        Rooter,
        Store,
        Comment,
        Accuse
    }
}
</script>

<style lang="scss" scoped>
.el-menu {
    min-height: 87vh;
    width: 14vw;
}

.content {
    min-height: 83vh;
}
</style>
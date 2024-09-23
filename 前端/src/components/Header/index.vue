<template>
    <div class="header">
        <div class="left" v-if="PathRevert">
            <!-- <img src="./../../assets/imgs/logo.png" alt=""> -->

            <div style="position: absolute; margin-left: 15%;margin-top:12px;">
                <el-input placeholder="请输入内容" v-model="input2" clearable suffix-icon="el-icon-search">
                </el-input>
            </div>
            <div style="position: absolute; float:left;margin-left: 30%;margin-top:12px;">
                <el-button @click="SerchStore" type="primary" round
                    style="display:block;background-color: #fbafaf;border-color: #fbafaf;font-size: 13px;font-weight: 500;">搜索</el-button>
            </div>
        </div>
        <div class="right">
            <div class="top-text" style="cursor:pointer" @click="runTo(7)"
                v-if="$store.state.users.userInfo.uadmin != '超级管理员'">
                <div class="r-top"><img src="./../../assets/imgs/index.png" alt="">
                </div>
                <div class="r-bottom">首页 <span>|</span></div>
            </div>
            <div class="top-text" style="cursor:pointer" @click="runTo(0)"
                v-if="$store.state.users.userInfo.uadmin != '超级管理员' && $store.state.users.isLogin == 1">
                <div class="r-top"><img src="./../../assets/imgs/submit.png" alt="">
                </div>
                <div class="r-bottom">发布 <span>|</span></div>
            </div>
            <div class="top-text" style="cursor:pointer" @click="runTo(1)"
                v-if="$store.state.users.userInfo.uadmin != '超级管理员' && $store.state.users.isLogin == 1">
                <div class="r-top"><img src="./../../assets/imgs/car.png" alt="">
                </div>
                <div class="r-bottom">购物车 <span>|</span></div>
            </div>
            <div class="top-text" style="cursor:pointer" @click="runTo(2)"
                v-if="$store.state.users.userInfo.uadmin != '超级管理员' && $store.state.users.isLogin == 1">
                <div class="r-top"><img src="./../../assets/imgs/star.png" alt="">
                </div>
                <div class="r-bottom">收藏夹 <span>|</span></div>
            </div>
            <div class="top-text" style="cursor:pointer" @click="runTo(3)"
                v-if="$store.state.users.userInfo.uadmin != '超级管理员' && $store.state.users.isLogin == 1">
                <div class="r-top"><img src="./../../assets/imgs/message.png" alt="">
                </div>
                <div class="r-bottom">消息 <span>|</span></div>
            </div>
            <div class="top-text" style="cursor:pointer" @click="runTo(4)" v-if="$store.state.users.isLogin == 1">
                <div class="r-top"><img src="./../../assets/imgs/my.png" alt="">
                </div>
                <div class="r-bottom">我的 <span>|</span></div>
            </div>
            <div class="top-text" style="cursor:pointer" @click="runTo(6)"
                v-if="$store.state.users.userInfo.uadmin != '普通用户' && $store.state.users.isLogin == 1">
                <div class="r-top"><img src="./../../assets/imgs/manage.png" alt="">
                </div>
                <div class="r-bottom">管理 <span>|</span></div>
            </div>
            <div class="top-text" style="cursor:pointer" @click="runTo(5)">
                <div class="r-top"><img src="./../../assets/imgs/exit.png" alt="">
                </div>
                <div class="r-bottom">{{ $store.state.users.isLogin == 1 ? '退出登录' : '登录' }}</div>
            </div>
        </div>
    </div>
</template>

<script>
import { _serach } from '@/api/index'
import Cookies from 'js-cookie'
export default {
    data() {
        return {
            input2: '',
            isLogin: '登录'
        }
    },
    computed: {
    },
    watch: {
        '$store.state.users.isLogin': function (old, newVal) {
            console.log(newVal)
            if (newVal == 0) {
                this.isLogin = '登录'
            } else {
                this.isLogin = '退出登录'
            }
        }
    },
    computed: {
        PathRevert() {
            if (this.$route.path == '/' || this.$route.path == '/manage') {
                return false;
            } else {
                return true
            }
        }
    },
    methods: {
        runTo(idx) {
            let currentPath = this.$route.path
            if (idx == 0 && currentPath != '/release') {
                this.$router.push('/release');
            } else if (idx == 1 && currentPath != '/car') {
                this.$router.push('/car');
            } else if (idx == 2 && currentPath != '/star') {
                this.$router.push('/star');
            } else if (idx == 3 && currentPath != '/chat') {
                this.$router.push('/chat');
            } else if (idx == 4 && currentPath != '/selfchange') {
                this.$router.push('/selfchange');
            } else if (idx == 5 && currentPath != '/') {
                // if (this.isLogin == "登录") {
                //     this.$router.push('/')
                //     this.isLogin = "退出登录"
                // } else {
                //     this.isLogin = "登录"
                // }
                if (this.$store.state.users.isLogin == 1) {
                    this.$store.commit("setIsLogin", 0)
                    Cookies.remove('uid');
                    this.$router.push('/')
                } else {
                    this.$router.push('/')
                }
            } else if (idx == 6 && currentPath != '/manage') {
                this.$router.push('/manage');
            } else if (idx == 7 && currentPath != '/index') {
                this.$router.push('/index');
            }
        },
        async SerchStore() {
            // 当前页搜了再过去
            try {
                let data = {
                    serach: this.input2
                }
                let res = await _serach(data)
                if (res.status == 200) {
                    // this.$store.state.SerachList = res.data
                    this.$store.commit('setSerachList', res.data);
                    // this.$router.push({ path: '/result', query: { search: 1 } });
                    const path = this.$route.path;
                    if (path != '/index') {
                        this.$router.push('/index')
                    }
                }
            } catch (error) {
                console.log(error)
            }
        }
    }
}
</script>

<style lang="scss">
.header {
    position: relative;
    width: 100vw;
    height: 10vh;
    // background-color: #f3e8cb;
    background-color: #f2c6b4;
    background-image: linear-gradient(180deg, #f2c6b4 0%, #f3e8cb 60%);

    display: flex;
    z-index: 10;
    margin: 0 auto;
}

.left {

    img {
        margin-left: 10px;
        width: 14vh;
        float: left;
    }
}

.right {
    position: absolute;
    right: 8vh;
    // color: #fff;
    font-weight: 500;
    font-size: 20px;

    span {
        color: #f2c6b4;
        font-weight: 900;
    }

    .top-text {
        margin-right: 8px;
        margin-top: 8px;
        float: left;
        font-size: 13px;
    }

    .r-top {
        margin-left: -7px;
    }

    img {
        width: 4vh;
    }
}
</style>
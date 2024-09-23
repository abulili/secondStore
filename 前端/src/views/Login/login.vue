<template>

    <div class="login-box">
        <div class="img1">
        </div>

        <div class="login">
            <div class="top">
                <div v-if="current == '注册'">
                    <!-- <span class="av-words">+头像上传</span> -->
                    <div class="avr">
                        <el-upload class="avatar-uploader" action="http://localhost:80/store/upload"
                            :show-file-list="false" :on-success="handleAvatarSuccess"
                            :before-upload="beforeAvatarUpload">
                            <img v-if="imageUrl" :src="imageUrl" class="avatar">
                            <i v-else class="el-icon-plus avatar-uploader-icon av-words">头像上传</i>
                        </el-upload>
                    </div>
                </div>
            </div>
            <div class="bottom">
                <div style="margin-top:10px;color:#fff;padding-top:14px;">
                    <h1>{{ current }}</h1>
                </div>

                <div class="inputBox">
                    <el-row>
                        <el-col :span="23"><el-input placeholder="请输入内容" prefix-icon="el-icon-user" v-model="inputName"
                                @blur="checkName">
                            </el-input></el-col>
                        <el-col :span="8"><span class="notify">{{ inputNameNotiy }}</span></el-col>
                    </el-row>

                    <el-row>
                        <el-col :span="23"><el-input placeholder="请输入密码" prefix-icon="el-icon-lock" v-model="inputPwd"
                                show-password></el-input></el-col>
                        <el-col :span="2"></el-col>
                    </el-row>
                    <el-row v-if="current == '注册'">
                        <el-col :span="23"><el-input placeholder="确认密码" prefix-icon="el-icon-lock" v-model="inputPwd2"
                                show-password @blur="checkPwd"></el-input></el-col>
                        <el-col :span="8"><span class="notify">{{ inputPwdNotiy }}</span></el-col>
                    </el-row>
                    <div v-if="current == '登录'" style="margin-top: 5px;
  position: relative;
  width: 100%;
  height: 20px;"> <span style="position: absolute;
  right: 20px;
}"><el-button type="text" @click="dialogVisible = true">忘记密码</el-button> </span></div>
                    <el-row v-if="current == '注册'">
                        <el-col :span="12"> <el-button @click="current = '登录'" round
                                style="background-color: #99e1e5;color:#fff">去登录</el-button></el-col>
                        <el-col :span="12"> <el-button @click="reg" round
                                style="background-color: #f2c6b4;color:#fff">确认注册</el-button></el-col>

                    </el-row>
                    <el-row v-else>
                        <el-col :span="12"><el-button @click="current = '注册'" round
                                style="background-color: #f2c6b4;color:#fff">注册</el-button></el-col>
                        <el-col :span="12"><el-button @click="login" round
                                style="background-color: #99e1e5;color:#fff">登录</el-button></el-col>
                    </el-row>

                </div>
            </div>
        </div>

        <el-dialog title="请填写验证信息" :visible.sync="dialogVisible" width="30%">
            <div>
                <el-input v-model="emailNumber">
                    <template slot="prepend">邮箱</template>
                </el-input>
                <el-input style="margin-top:5px" v-model="phoneNumber">
                    <template slot="prepend">手机号</template>
                </el-input>
            </div>
            <span slot="footer" class="dialog-footer">
                <el-button @click="dialogVisible = false">取 消</el-button>
                <el-button type="primary" @click="findPwd">确 定</el-button>
            </span>
        </el-dialog>
    </div>
</template>

<script>
import { _listId, _addUser, _serachUser, _rePwd } from '@/api/user'
import * as imageConversion from 'image-conversion'
import Cookies from 'js-cookie'
export default {
    data() {
        return {
            inputName: '',
            inputPwd: '',
            inputPwd2: '',
            inputNameNotiy: '',
            inputPwdNotiy: '',
            current: '登录',
            imageUrl: '',
            avrUrl: '',
            dialogVisible: false,
            emailNumber: '',
            phoneNumber: ''
        }
    },
    methods: {
        async findPwd() {
            try {
                if (this.emailNumber == '' && this.phoneNumber == '') {
                    this.$message({
                        message: '请选择至少一个填入信息',
                        type: 'warning'
                    });
                } else {
                    let data = {
                        uname: this.inputName,
                        uemail: this.emailNumber,
                        uphone: this.phoneNumber
                    }
                    let res = await _rePwd(data)
                    console.log(res)
                    if (res.status == 200) {
                        if (res.data) {
                            this.$message({
                                message: '密码重置成功',
                                type: 'warning'
                            });
                        } else {
                            this.$message({
                                message: '信息错误',
                                type: 'warning'
                            });
                        }
                        this.dialogVisible = false
                    }
                }

            } catch (error) {

            }
        },
        handleAvatarSuccess(res, file) {
            // console.log(res)
            this.avrUrl = res
            this.imageUrl = URL.createObjectURL(file.raw);
        },
        beforeAvatarUpload(file) {
            const isJpgOrPng = file.type === 'image/jpeg' || file.type === 'image/png';
            if (!isJpgOrPng) {
                this.$message.error('上传头像图片只能是 JPG 或 PNG 格式!');
                return false;
            }
            return new Promise((resolve) => {
                // 压缩到100KB, 这里的100就是要压缩的大小, 可自定义
                imageConversion.compressAccurately(file, 100).then(res => {
                    // console.log('res', res)
                    resolve(res);
                });
            })
        },
        checkPwd() {
            if (this.inputPwd != this.inputPwd2) {
                this.inputPwdNotiy = '两次密码输入不一致'
            } else {
                this.inputPwdNotiy = ''
            }
        },
        login() {
            if (this.inputName == '' || this.inputPwd == '') {
                this.$message.error('请输入账号或密码');
            } else {
                this.loginMethod()
            }
        },
        async loginMethod() {
            try {
                let data = {
                    uname: this.inputName,
                    upwd: this.inputPwd
                }
                let res = await _listId(data);
                // console.log('login', res);
                if (res.status == 200) {
                    if (res.data.length != "") {
                        setTimeout(() => {
                            // this.$store.state.userInfo = res.data
                            // console.log(res.data)
                            // this.$store.state.isLogin = 1
                            this.$store.commit('setUserInfo', res.data)
                            this.$store.commit('setIsLogin', 1)
                            // console.log(this.$store.state.users.userInfo)
                            // superadmin
                            Cookies.set("uid", '1')
                            if (res.data.uadmin == '普通用户' || res.data.uadmin == '管理员') {
                                this.$router.push('/index')
                            } else {
                                this.$router.push('/manage')
                            }
                        }, 500)
                    }
                    else {
                        this.$message.error('账号名或密码错误');
                    }
                }

            } catch (error) {
                console.log(error)
            }
        },
        async reg() {
            try {
                if (this.inputName == '' || this.inputPwd == '' || this.inputPwd2 == '') {
                    this.$message({
                        type: 'warning',
                        message: '请在下方输入'
                    });
                    return;
                }
                this.checkName()
                this.checkPwd()
                if (this.inputNameNotiy != '') {
                    this.$message.error('该用户名已存在');
                } else if (this.inputPwdNotiy != '') {
                    this.$message.error('两次密码输入不一致');
                }
                else {
                    let data = {
                        uavatar: this.avrUrl,
                        uname: this.inputName,
                        upwd: this.inputPwd
                    }
                    let res = await _addUser(data)
                    // console.log(res)
                    if (res.status == 200) {
                        this.$message({
                            message: '注册成功',
                            type: 'success'
                        });
                        // this.$store.state.isLogin = 1
                        this.$store.commit("setIsLogin", 1)
                        this.inputName = ''
                        this.inputPwd = ''
                        this.inputPwd2 = ''
                        this.inputPwdNotiy = ''
                        this.imageUrl = ''
                        this.avrUrl = ''
                    }
                }
            } catch (error) {
                console.log(error)
            }
        },
        checkName() {
            if (this.current == '注册') {
                this.checkNameBeta()
            }
        },
        async checkNameBeta() {
            // 查数据库有有没有相同的名字
            try {
                let data = {
                    uname: this.inputName
                }
                let res = await _serachUser(data)
                if (res.status == 200) {
                    if (res.data.length > 0) {
                        this.inputNameNotiy = '此用户名已被占用'
                    } else {
                        this.inputNameNotiy = ''
                    }
                }
            } catch (error) {
                console.log(error)
            }
        },
    }
}
</script>

<style lang="scss">
.login-box {
    .av-words {
        font-size: 16px;
        color: #fbafaf;
        font-weight: blod;
        // position: absolute;
        // z-index: 1;
        // left: 43%;
        // bottom: 13%;
        font-weight: bold;
    }

    .avr {
        position: absolute;
        margin: 0 auto;
        top: 63px;
        left: 40%;

        .avatar-uploader .el-upload {
            border: 1px dashed #d9d9d9;
            border-radius: 6px;
            cursor: pointer;
            position: relative;
            overflow: hidden;
            width: 100px;
            height: 100px;
            margin: 0 auto;
            background-color: #fff;
            box-shadow: 3px 3px 0px 0px #c56f4f;
        }

        .avatar-uploader .el-upload:hover {
            border-color: #409EFF;
        }

        .avatar-uploader-icon {
            font-size: 28px;
            color: #8c939d;
            width: 178px;
            height: 178px;
            line-height: 178px;
            text-align: center;
        }

        .avatar {
            width: 100%;
            height: 100%;
            object-fit: cover;
            display: block;
        }

        .el-icon-plus {
            width: 100px;
            height: 100px;
            margin: 0 auto;
            top: -35%;
            position: relative;
            // display: none;
        }
    }

    .img1 {
        position: absolute;
        width: 500px;
        height: 500px;
    }

    position: relative;
    width: 100vw;
    height: 90vh;
    // background-image: linear-gradient(to right, #ffecd2 0%, #fcb69f 100%);
    background-color: #fbafaf;
    background-image: linear-gradient(135deg, #fbafaf 0%, #f2c6b4 33%, #f3e8cb 66%, #99e1e5 100%);

    .login {
        position: relative;
        width: 500px;
        height: 500px;
        margin: 0 auto;
        top: 5%;
    }

    .top {
        width: 500px;
        height: 151px;
        position: relative;
        background: url('/src/assets/imgs/logo.png') no-repeat;
        background-size: 100%;
    }

    .bottom {
        position: relative;
        height: 335px;
        width: 500px;
        margin: 0 auto;
        background-color: rgba(250, 250, 250, 0.3);
        border-radius: 20px;


    }

    .inputBox {
        position: relative;
        width: 80%;
        height: 80%;
        margin: 0 auto;
        top: 14%;

        .notify {
            font-size: 12px;
            color: #d25c5c;
        }

        .el-row {
            margin-top: 20px;
        }

        .el-row:first-child {
            margin-top: 0px;
        }

        .el-input--prefix.el-input__inner {
            border-radius: 18px;
        }
    }
}
</style>

<template>
    <div>
        <!-- 可以切换富文本编辑器
        https://www.cnblogs.com/jaaks/p/17892063.html
        https://blog.csdn.net/lll11122222/article/details/135205429
         -->
        <!-- 表单（类似闲鱼） -->
        <el-card class="content">


            <!-- 上传图片(多图,视频到时候再说) -->
            <!-- https://blog.csdn.net/Shids_/article/details/131855886 压缩图片 -->
            <div>
                <!-- <div style="margin-bottom: 10px;">最多上传5张照片，第一张将作为主图</div> -->
                <!-- multiple -->
                <el-upload action="http://localhost:80/store/upload" list-type="picture-card"
                    :on-preview="handlePictureCardPreview" :on-remove="handleRemove" :limit="1" :on-success="imgUp"
                    :before-upload="beforeup">
                    <i class="el-icon-plus"></i>
                </el-upload>
                <el-dialog :visible.sync="dialogVisible">
                    <img width="100%" :src="dialogImageUrl" alt="">
                </el-dialog>

                <!-- <el-upload action="https://jsonplaceholder.typicode.com/posts/" list-type="picture-card"
                    :on-preview="handlePictureCardPreview" :on-remove="handleRemove" :limit="5" multiple
                    accept="image/png, image/jpeg">
                    <i class="el-icon-plus"></i>
                </el-upload>
                <el-dialog :visible.sync="dialogVisible">
                    <img width="100%" :src="dialogImageUrl" alt="">
                </el-dialog> -->
            </div>

            <div class="middle">
                <el-form label-position="left" label-width="80px" :model="formLabelAlign">
                    <el-form-item label="商品标题">
                        <el-input class="title" maxlength="18" show-word-limit v-model="title"></el-input>
                    </el-form-item>
                    <el-form-item label="商品描述(2k字内)">
                        <div class="edit-div" contenteditable="true" @blur="desCheckw" id="scontent"></div>
                        <div style="float:right;" v-show="desWrong.length != 0"><span style="color:#fbafaf">{{ desWrong
                                }}</span></div>
                    </el-form-item>
                    <el-form-item label="价格">
                        <el-input v-model="price" placeholder="" clearable :minlength="1" :max="50000" :min="0"
                            :maxlength="5" @blur="priceCheck"><template slot="append">元/个</template></el-input>
                        <div style="float:right;" v-show="priceWrong.length != 0"><span style="color:#fbafaf">{{
                        priceWrong
                    }}</span></div>
                    </el-form-item>
                    <el-form-item label="商品数量(个)">
                        <el-input-number v-model="num" controls-position="right" @change="handleChange" :min="1"
                            :max="99" :step-strictly="true"></el-input-number>
                    </el-form-item>
                    <el-form-item label="交易地">
                        <el-row>
                            <el-col :span="24">
                                <div class="bmap-wrapper">
                                    <el-bmap class="bmap-box" :zoom="zoom" :center="center" :vid="'bmap-vue'" :tilt="73"
                                        :heading="90" @click="test">
                                        <el-bmap-info-window :visible="current.visible" :position="current.position"
                                            :content="current.content"></el-bmap-info-window>

                                        <el-bmapv-view>
                                            <el-bmapv-icon-layer :icon="icon" :width="width" :height="height"
                                                :data="datas" :enable-picked="true"></el-bmapv-icon-layer>
                                        </el-bmapv-view>
                                    </el-bmap>
                                </div>
                            </el-col>
                            <!-- <el-col :span="12">
                                <el-card>左侧点击图标选择交易地点，下侧输入地点具体信息</el-card>
                                <el-input type="textarea" :rows="2" placeholder="请输入内容" v-model="textarea"
                                    style="margin-top: 20px;">
                                </el-input>
                                <el-button round style="margin-top: 20px;">确认信息此处无误</el-button>
                            </el-col> -->
                        </el-row>
                    </el-form-item>
                    <!-- <el-form-item label="商品tag"> -->
                    <!-- <el-input v-model="formLabelAlign.type"></el-input> -->
                    <!-- <el-tag :key="tag" v-for="tag in dynamicTags" closable :disable-transitions="false"
                            @close="handleClose(tag)">
                            {{ tag }}
                        </el-tag>
                        <el-input class="input-new-tag" v-if="inputVisible" v-model="inputValue" ref="saveTagInput"
                            size="small" @keyup.enter.native="handleInputConfirm" @blur="handleInputConfirm" maxlength="5">
                        </el-input>
                        <el-button v-else class="button-new-tag" size="small" @click="showInput">+ New Tag</el-button>
                    </el-form-item> -->
                </el-form>

            </div>

            <div class="foot">
                <div class="btns">
                    <el-row>
                        <el-col :span="12"><router-link :to="{ path: '/index' }">
                                <el-button type="info">取消发布</el-button>
                            </router-link></el-col>
                        <el-col :span="12"><el-button type="primary" @click="submit">发布
                                <!--（自己发布的可以进行删除，点进自己的页面可以编辑，还有就是个人管理的时候） --></el-button></el-col>
                        <!-- <el-col :span="8"><el-button>继续发布</el-button></el-col> -->
                    </el-row>
                </div>
            </div>
        </el-card>
    </div>
</template>

<script>
// import  compressFile  from "@/api/utils"
import * as imageConversion from 'image-conversion'
import { _addStore } from '@/api/index'
import { _getLDB } from "@/api/index"
export default {
    data() {
        return {
            desCheck: '',
            dialogImageUrl: '',
            dialogVisible: false,
            price: 0,
            des: '',
            num: 1,
            // dynamicTags: ['标签一', '标签二', '标签三'],
            inputVisible: false,
            inputValue: '',
            formLabelAlign: {
                name: '',
                region: '',
                type: ''
            },
            title: '',
            zoom: 19,
            center: [103.738152, 29.56574],
            icon: '/src/assets/imgs/location.png',
            width: 24,
            height: 40,
            datas: [{
                geometry: {
                    type: 'Point',
                    coordinates: [103.738152, 29.56574],
                },
                properties: {
                    icon: '/src/assets/imgs/location.png'
                }
            }],
            current: {
                position: [103.738152, 29.56574],
                visible: false,
                // content: ''
            },
            textarea: '',
            base64List: [],
            desWrong: '',
            priceWrong: '',
        }
    },
    methods: {
        async getLDB() {
            try {
                let res = await _getLDB()
                if (res.status == 200) {
                    res = res.data
                    this.locat = res[0]
                    this.center = [res[0].slng, res[0].slat]
                    this.datas[0].geometry.coordinates = this.center;
                    this.getR()
                }
            } catch (error) {
                console.log(error)
            }
        },
        async submit() {
            try {
                if (this.title == '' || this.num == '' || this.price == '' || this.base64List[0] == '') {
                    this.$message({
                        message: '请将信息填写完整',
                        type: 'warning'
                    });
                    return;
                }
                let data = {
                    stitle: this.title,
                    scontent: this.des,
                    snum: this.num,
                    suid: this.$store.state.users.userInfo.uid,
                    sprice: this.price,
                    slng: this.datas[0].geometry.coordinates[0],
                    slat: this.datas[0].geometry.coordinates[1],
                    spic: this.base64List[0]
                }
                console.log(data);
                let res = await _addStore(data);
                if (res.status == 200) {
                    if (res.data != 0) {
                        this.$notify({
                            title: '成功',
                            message: '上传成功',
                            type: 'success'
                        });
                        setTimeout(() => {
                            this.$router.push('/index')
                        }, 1000);
                    }
                }
            } catch (error) {
                console.log(error)
            }
        },
        beforeup(file) {
            const isJpgOrPng = file.type === 'image/jpeg' || file.type === 'image/png';
            if (!isJpgOrPng) {
                console.log('上传头像图片只能是 JPG 或 PNG 格式!');
                return false;
            }
            return new Promise((resolve) => {
                // 压缩到100KB, 这里的100就是要压缩的大小, 可自定义
                imageConversion.compressAccurately(file, 100).then(res => {
                    console.log('res', res)
                    resolve(res);
                });
            })
        },
        // async clzpAfterRead(file, detail, name) {
        //     console.log(file.file.size, "未压缩大小");
        //     // 调用压缩图片的方法 this.$compressFile
        //     const compressedFile = await compressFile(file.file);
        //     console.log(compressedFile.size, "压缩后大小");
        //     let formData = new FormData();
        //     formData.append("file", compressedFile);
        //     uploadFile(formData).then((res) => {
        //         // 执行操作
        //     });
        // },
        desCheckw() {
            this.des = document.getElementById("scontent").innerHTML
            if (this.des.length >= 21840) {
                this.desWrong = '字数超限'
            } else {
                this.desWrong = ''
            }
        },
        priceCheck() {
            let p = Number(this.price)
            if (isNaN(p)) {
                this.priceWrong = "请输入正确格式的价格"
            } else {
                this.priceWrong = ''
            }
        },
        imgUp(response, file, fileList) {
            // console.log(response)
            this.base64List.push(response)
        },
        handleRemove(file, fileList) {
            console.log(file, fileList);
        },
        handlePictureCardPreview(file) {
            this.dialogImageUrl = file.url;
            this.dialogVisible = true;
        },
        handleChange(value) {
            console.log(value);
        },
        handleClose(tag) {
            this.dynamicTags.splice(this.dynamicTags.indexOf(tag), 1);
        },

        showInput() {
            this.inputVisible = true;
            this.$nextTick(_ => {
                this.$refs.saveTagInput.$refs.input.focus();
            });
        },

        handleInputConfirm() {
            let inputValue = this.inputValue;
            if (inputValue) {
                this.dynamicTags.push(inputValue);
            }
            this.inputVisible = false;
            this.inputValue = '';
        },
        test(e) {
            // console.log(e)
            const { latlng } = e
            // console.log(latlng)
            const { lat, lng } = latlng
            // this.current.visible = !this.current.visible
            // 在这里你可以做一些处理，比如将经纬度存储到data中，或者进行其他操作
            console.log(`点击位置的经度：${lng}，纬度：${lat}`)
            console.log(this.datas[0].geometry.coordinates)
            this.datas[0].geometry.coordinates = [lng, lat];
            console.log(this.datas[0].geometry.coordinates)
        }
    },
    mounted() {
        this.getLDB()
    }
}
</script>

<style lang="scss" scoped>
.content {
    position: relative;
    width: 80%;
    margin: 0 auto;
    border-radius: 4px;
}

.middle {
    margin-top: 20px;
}

.edit-div {
    height: 20vh;
    text-align: left;
    overflow-y: scroll;
    word-break: break-all;
    background-color: #FFF;
    background-image: none;
    border-radius: 4px;
    border: 1px solid #DCDFE6;
    padding-left: 15px;
    padding-right: 15px;
    color: #333;
    font: 14px/1.5 Microsoft YaHei, Heiti SC, tahoma, arial, Hiragino Sans GB, "\5B8B\4F53", sans-serif;
}

.el-tag+.el-tag {
    margin-left: 10px;
}

.button-new-tag {
    margin-left: 10px;
    height: 32px;
    line-height: 30px;
    padding-top: 0;
    padding-bottom: 0;
}

.input-new-tag {
    width: 90px;
    margin-left: 10px;
    vertical-align: bottom;
}

.bmap-wrapper {
    width: 1100px;
    height: 400px;
}

.foot {
    position: relative;
    padding-bottom: 12px;
    margin-top: 20px;
    // background-color: pink;

    .btns {
        // background-color: #fff;
        width: 40%;
        margin: 0 auto;
    }
}
</style>
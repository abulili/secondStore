<template>
    <div class="manage-index">
        <el-row :gutter="30" class="left-words" v-if="$store.state.users.userInfo.uadmin == '管理员'">
            <el-col :span="12">
                <el-card class="init-h">
                    <h2><i style="color:#f2cbb4;font-size: 24px;">|&nbsp;</i>用户举报</h2>
                    <div style="margin-top: 28px;">
                        <div class="words-message">- 待处理:<span class="messgae"><span>{{ num1 }}</span>条</span></div>
                        <!-- <div class="words-message">- 已处理<span class="messgae"><span style="color:#000">3</span>条</span> -->
                    </div>
                    <!-- <div class="words-message">- 最新消息</div> -->
                    <!-- </div> -->
                    <el-button class="mt" @click="runTo(0)">去处理</el-button>
                </el-card>
            </el-col>
            <!-- <el-col :span="12">
                <el-card class="init-h">
                    <h2><i style="color:blue;">|&nbsp;</i>请求重置密码</h2>
                    <div style="margin-top: 28px;">
                        <div class="words-message">- 待处理<span class="messgae"><span>0</span>条</span></div>
                        <div class="words-message">- 已处理<span class="messgae"><span style="color:#000">0</span>条</span>
                        </div>
                    </div>
                    <el-button class="mt">去处理</el-button>
                </el-card>
            </el-col>
        </el-row>
        <el-row :gutter="20" class="left-words"> -->
            <el-col :span="12">
                <el-card class="init-h">
                    <h2><i style="color:#f2cbb4;font-size: 24px;">|&nbsp;</i>评论管理</h2>
                    <div style="margin-top: 28px;">
                        <div class="words-message">- 待处理:<span class="messgae"><span>{{ num2 }}</span>条</span></div>
                        <!-- <div class="words-message">- 已处理<span class="messgae"><span style="color:#000">0</span>条</span> -->
                        <!-- </div> -->
                    </div>
                    <el-button class="mt" @click="runTo(1)">去处理</el-button>
                </el-card>
            </el-col>
        </el-row>

        <el-row :gutter="20" class="left-words" v-else>
            <el-col :span="12">
                <el-card class="init-h">
                    <h2><i style="color:#f2cbb4;font-size: 24px;">|&nbsp;</i>地址中心点管理</h2>
                    <div style="margin-top: 28px;">
                        <!-- <div class="words-message">- 待处理<span class="messgae"><span>{{ num2 }}</span>条</span></div> -->
                        <!-- <div class="words-message">- 已处理<span class="messgae"><span style="color:#000">0</span>条</span> -->
                        <!-- </div> -->
                    </div>
                    <el-button class="mt" @click="setMap" style="width:100%;margin-left:0px;">设置</el-button>
                </el-card>
            </el-col>
            <el-col :span="12">
                <el-card class="init-h">
                    <h2><i style="color:#f2cbb4;font-size: 24px;">|&nbsp;</i>地址范围管理</h2>
                    <div style="margin-top: 28px;">
                        <!-- <div class="words-message">- 待处理<span class="messgae"><span>{{ num2 }}</span>条</span></div> -->
                        <!-- <div class="words-message">- 已处理<span class="messgae"><span style="color:#000">0</span>条</span> -->
                        <!-- </div> -->
                    </div>
                    <el-button class="mt" @click="setCircle" style="width:100%;margin-left:0px;">设置</el-button>
                </el-card>
            </el-col>
        </el-row>

        <el-dialog title="地图中心点" :visible.sync="boxShow" width="30%">

            <!-- 怕是要用java -->
            <el-row style="width:90%;margin: 0 auto;">
                <el-col :span="18"> <el-input type="text" v-model="input"></el-input></el-col>
                <el-col :span="6"><el-button @click="serachLocation">检索</el-button></el-col>
            </el-row>

            <div class="bmap-wrapper">
                <el-bmap class="bmap-box" :zoom="zoom" :center="center" :vid="'bmap-vue'" :tilt="73" :heading="90">
                    <!-- <el-bmap-info-window :visible="current.visible" :position="current.position"
                        :content="current.content"></el-bmap-info-window> -->
                    <el-bmapv-view>
                        <el-bmapv-icon-layer :icon="icon" :width="width" :height="height" :data="datas"
                            :enable-picked="true"></el-bmapv-icon-layer>
                    </el-bmapv-view>
                </el-bmap>
            </div>

            <span slot="footer" class="dialog-footer">
                <el-button @click="boxShow = false">取 消</el-button>
                <el-button type="primary" @click="changeLocation">确 定</el-button>
            </span>
        </el-dialog>

        <el-dialog title="地址范围" :visible.sync="polygonsShow" width="70%">

            <div class="bmap-page-container" style="width:90%;margin: 0 auto;">
                <el-bmap vid="bmap" :zoom="zoom1" :bmap-manager="bmapManager" :center="center" ref="map"
                    class="bmap-demo">
                    <el-bmap-polygon v-for="(polygon, index) in polygons" :visible="visible" :key="index" :vid="index"
                        :ref="`polygon_${index}`" :path="polygon.path" :enable-editing="polygon.enableEditing"
                        :events="polygon.events"></el-bmap-polygon>
                </el-bmap>
            </div>

            <span slot="footer" class="dialog-footer">
                <el-button @click="polygonsShow = false">取 消</el-button>
                <el-button type="primary" @click="changeRange">确 定</el-button>
            </span>
        </el-dialog>

    </div>
</template>

<script>
import { _listComment } from "@/api/comment"
import { _listReport } from "@/api/report"
import { _getL, _getR, _getLDB, _updateStore } from "@/api/index"
import VueBMap from 'vue-bmap-gl';
let bmapManagerr = new VueBMap.BMapManager()
export default {
    data() {
        return {
            num1: 0,
            num2: 0,
            boxShow: false,
            input: '',
            location: '',
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
            locat: {},
            // current: {
            //     position: [103.738152, 29.56574],
            //     visible: false,
            //     // content: ''
            // },
            polygonsShow: false,
            visible: true,
            zoom1: 15,
            center: [121.5273285, 31.21515044],
            bmapManager: bmapManagerr,
            polygons: [
                {
                    enableEditing: true,
                    path: [[121.5273285, 31.21515044], [121.5293285, 31.21515044], [121.5293285, 31.21915044], [121.5273285, 31.21515044]],
                    events: {
                        editend: () => {
                            console.log(this.$refs.polygon_0[0].$$getPath())
                            let l = this.$refs.polygon_0[0].$$getPath()
                            if (l.length >= 1000000) {
                                this.polygon.enableEditing = false
                                this.$message({
                                    type: "warning",
                                    message: "达到最大可拖动点数"
                                })
                            }
                        }
                    }
                }
            ],
        }
    },
    methods: {
        changeRange() {
            let str = this.$refs.polygon_0[0].$$getPath()
            str = str.toString()
            this.locat.scontent = str
            this.changeLocation()
            this.polygonsShow = false
        },
        setCircle() {
            this.polygonsShow = true
            this.getLDB("polygons")
        },
        setMap() {
            this.boxShow = true
            this.getLDB("map")
        },
        // 地址逆解析
        async getR() {
            try {
                let res = await _getR({
                    lng: this.center[1],
                    lat: this.center[0]
                })
                if (res.status == 200) {
                    if (res.data.status == 0) {
                        this.input = res.data.result.business
                    }
                }
            } catch (error) {
                console.log(error)
            }
        },
        async getLDB(str) {
            try {
                let res = await _getLDB()
                if (res.status == 200) {
                    res = res.data
                    console.log('getLDb', res)
                    this.center = [res[0].slng, res[0].slat]
                    if (str == "map") {
                        this.locat = res[0]
                        this.datas[0].geometry.coordinates = this.center;
                        this.getR()
                    }
                    else if (str == "polygons") {
                        let st = res[0].scontent
                        let pairs = st.split(',')
                        let coordinates = []
                        for (let i = 0; i < pairs.length; i += 2) {
                            let longitude = parseFloat(pairs[i])
                            let latitude = parseFloat(pairs[i + 1])
                            // center变了之后，发现不太一样，就直接改
                            if (((Number(this.center[0]) - longitude) > 0.01 || ((Number(this.center[0]) - longitude) < -0.01)) && ((Number(this.center[1]) - latitude) > 0.01) || ((Number(this.center[1]) - latitude) < -0.01)) {
                                let temp1 = Number(this.center[0]) - 0.002
                                let temp2 = Number(this.center[1]) + 0.002
                                this.polygons[0].path = []
                                this.polygons[0].path.push([Number(this.center[0]), Number(this.center[1])])
                                this.polygons[0].path.push([temp1, Number(this.center[1])])
                                this.polygons[0].path.push([Number(this.center[0]), temp2])
                                this.polygons[0].path.push([Number(this.center[0]), temp2])
                                this.$message({
                                    type: "warning",
                                    message: "由于地址变更，已自动更换范围"
                                })
                                coordinates = this.polygons[0].path
                                break;
                            }
                            coordinates.push([longitude, latitude])
                        }
                        this.polygons[0].path = coordinates
                    }

                }
            } catch (error) {
                console.log(error)
            }
        },
        async serachLocation() {
            try {
                let res = await _getL({
                    address: this.input
                })
                if (res.status == 200) {
                    // console.log(res.data)
                    if (res.data.status == 0) {
                        this.locat.slng = res.data.result.location.lng
                        this.locat.slat = res.data.result.location.lat
                        this.center = [res.data.result.location.lng, res.data.result.location.lat]
                        this.datas[0].geometry.coordinates = this.center;
                        console.log(this.center)
                    }
                }
                // console.log("ttt")
                // let url = "https://api.map.baidu.com/geocoding/v3/?address=" + this.input + "&output=json&ak=njA0KpLRkXkbLX5x36Gsa648tBBnWjpW"
                // console.log(url)
                // axios.get(url).then(res => {
                //     console.log(res)
                // })
            } catch (error) {
                console.log(error)
            }
        },
        async changeLocation() {
            try {
                console.log("this.locat", this.locat)

                let res = await _updateStore(this.locat)
                if (res.status == 200) {
                    if (res.data) {
                        // 成功
                        this.$message({
                            type: "success",
                            message: "改变成功!"
                        })
                    }
                }
                this.boxShow = false
            } catch (error) {
                console.log(error)
            }
        },
        runTo(idx) {
            let data = '4-3'
            if (idx == 1) {
                data = '4-2'
            }
            this.$store.commit('setActive', data)
        },
        async listComment() {
            try {
                let res = await _listComment()
                if (res.status == 200) {
                    res = res.data;
                    this.num1 = res.length
                }
            } catch (error) {
                console.log(error)
            }
        },
        async listReport() {
            try {
                let res = await _listReport()
                if (res.status == 200) {
                    res = res.data
                    this.num2 = res.length
                }
            } catch (error) {
                console.log(error)
            }
        }
    },
    mounted() {
        this.listComment()
        this.listReport()
    },
    components: {
        VueBMap
    },
}
</script>

<style lang="scss">
.manage-index {

    .bmap-wrapper,
    .bmap-page-container {
        width: 90%;
        height: 300px;
        margin: 0 auto;
        margin-top: 10px;
    }

    .messgae {
        margin-left: 20px;
        margin-right: 20px;

        span:first-child {
            font-size: 25px;
            color: #fbafaf;
            margin-right: 10px;
        }
    }

    .left-words {
        text-align: left
    }

    .init-h {
        height: 34vh;
    }

    .el-card {
        margin-top: 10px;
    }

    .mt {
        margin-top: 7%;
        margin-left: 75%;
    }

    .words-message {
        font-size: 14px;
        margin-top: 11px;
    }

}
</style>
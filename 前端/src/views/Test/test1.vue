<template>
    <div class="content-box">
        <div class="container">
            <el-table :data="tableData" style="width: 100%">
                <el-table-column prop="date" label="日期" width="180"> </el-table-column>
                <el-table-column prop="name" label="姓名" width="180"> </el-table-column>
                <el-table-column prop="address" label="地址"> </el-table-column>
                <el-table-column label="定位">
                    <template slot-scope="scope">
                        <i class="el-icon-location hostlocation" @click="positioning(scope.row)"></i>
                    </template>
                </el-table-column>
            </el-table>
            <el-dialog title="地图定位" :visible.sync="dialogMap" width="35%" :closeOnClickModal="false" :showClose="false"
                :destroy-on-close="true" v-if="sign">
                <div class="location-box">
                    <template>
                        <!-- :scroll-wheel-zoom="true":开启滚轮缩放,center:"{lng: 116.404, lat: 39.915}",格式如下,定义地图经纬度 -->
                        <baidu-map style="height: 400px" class="map" :zoom="12" :center="center" :scroll-wheel-zoom="true">
                            <!-- BMAP_NORMAL_MAP:展示图片街道视图, BMAP_HYBRID_MAP:卫星和路网的混合视图-->
                            <bm-map-type :map-types="['BMAP_NORMAL_MAP', 'BMAP_HYBRID_MAP']"></bm-map-type>
                            <!-- BMAP_ANIMATION_BOUNCE:定位点跳动 -->
                            <bm-marker :position="center" :dragging="true" animation="BMAP_ANIMATION_BOUNCE">
                                <!-- 时间文本 -->
                                <bm-label :content="time" :labelStyle="{
                                    color: 'black',
                                    fontSize: '12px',
                                    borderColor: '#fff',
                                    borderRadius: 10
                                }" :offset="{ width: -48, height: 28 }" />
                            </bm-marker>
                        </baidu-map>
                    </template>
                </div>
                <span slot="footer" class="dialog-footer">
                    <el-button @click="cancel" size="mini">取 消</el-button>
                </span>
            </el-dialog>
        </div>
    </div>
</template>
 
<script>
export default {
    data() {
        return {
            tableData: [
                {
                    date: '2016-05-02',
                    name: '王小虎',
                    address: '上海市普陀区金沙江路 1518 弄',
                    longitude: 116.3683244,
                    latitude: 39.915085
                },
                {
                    date: '2016-05-04',
                    name: '王小虎',
                    address: '上海市普陀区金沙江路 1517 弄',
                    longitude: 116.405285,
                    latitude: 39.904989
                },
                {
                    date: '2016-05-01',
                    name: '王小虎',
                    address: '上海市普陀区金沙江路 1519 弄',
                    longitude: 117.190182,
                    latitude: 39.125596
                },
                {
                    date: '2016-05-03',
                    name: '王小虎',
                    address: '上海市普陀区金沙江路 1516 弄',
                    longitude: 112.549248,
                    latitude: 38.045474
                }
            ],
            dialogMap: false, //控制弹窗
            time: '', //定位点下的时间
            center: { lng: 0, lat: 0 }, //坐标
            zoom: 14, //缩放
            sign: false //弹窗开关
        };
    },
    mounted() { },
    methods: {
        positioning(row) {
            this.sign = true;
            this.dialogMap = true;
            //时间
            this.time = row.date;
            //经度和纬度
            this.center.lng = parseInt(row.longitude);
            this.center.lat = parseInt(row.latitude);
        },
        cancel() {
            this.dialogMap = false;
        }
    }
};
</script>
 
<style lang="scss" scoped></style>
<template>
    <div class="bmap-page-container">
        <el-bmap vid="bmap" :zoom="zoom" :bmap-manager="bmapManager" :center="center" ref="map" class="bmap-demo">
            <el-bmap-polygon v-for="(polygon, index) in polygons" :visible="visible" :key="index" :vid="index"
                :ref="`polygon_${index}`" :path="polygon.path" :enable-editing="polygon.enableEditing"
                :events="polygon.events"></el-bmap-polygon>
        </el-bmap>
        <div>
            <button @click="switchVisible">切换显隐</button>
            <button @click="sure">确认</button>
        </div>
    </div>
</template>


<script>
import VueBMap from 'vue-bmap-gl';
import { _getLDB } from "@/api/index"
let bmapManagerr = new VueBMap.BMapManager()
export default {
    data() {
        return {
            zoom: 15,
            center: [103.75764136268987, 29.565402386498825],
            bmapManager: bmapManagerr,
            visible: true,
            prepath: [],
            polygons: [
                {
                    enableEditing: true,
                    path: [[103.76209695801786, 29.56226087537925], [103.75376068288803, 29.562386538161334], [103.75864136268984, 29.566402397460624], [103.76209695801786, 29.56226087537925]],
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

                                // this.$refs.polygon_0[0].$$getPath() = this.prepath
                            }
                        }
                    }
                }
            ],
        }
    },
    methods: {
        sure() {
            // 先保存到数据库里
            console.log(this.$refs.polygon_0[0].$$getPath())
        },
        getNum() {

        },
        switchVisible() {
            this.visible = !this.visible;
        },
        async getLDB() {
            try {
                let res = await _getLDB()
                if (res.status == 200) {
                    res = res.data
                    this.center = [res[0].slng, res[0].slat]
                    this.polygons[0].path[0] = this.center
                    // alert(Object.prototype.toString.call(res[0].slng))
                    let temp1 = Number(res[0].slng) - 0.002
                    let temp2 = Number(res[0].slat) + 0.002
                    this.polygons[0].path[1] = [temp1.toString(), res[0].slat]
                    this.polygons[0].path[2] = [res[0].slng, temp2.toString()]
                    this.polygons[0].path[3] = [res[0].slng, temp2.toString()]
                }
            } catch (error) {
                console.log(error)
            }
        },
    },
    computed: {

    },
    watch: {

    },
    mounted() {
        this.getLDB()

    },
    components: {
        VueBMap
    },
}
</script>

<style lang="scss" scoped>
.bmap-page-container {
    width: 100%;
    height: 500px;
}
</style>
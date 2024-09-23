<template>
    <div id="app">
        <h3 class="title">{{ msg }}</h3>
        <div class="bmap-wrapper">
            <el-bmap class="bmap-box" :zoom="zoom" :center="center" :vid="'bmap-vue'" :tilt="73" :heading="90"
                @click="test">
                <el-bmap-info-window :visible="current.visible" :position="current.position"
                    :content="current.content"></el-bmap-info-window>

                <el-bmapv-view @click="test">
                    <el-bmapv-icon-layer :icon="icon" :width="width" :height="height" :data="data"
                        :enable-picked="true"></el-bmapv-icon-layer>
                </el-bmapv-view>
            </el-bmap>
        </div>
    </div>
</template>

<script>
import { lazyBMapApiLoaderInstance } from 'vue-bmap-gl'
export default {
    data() {
        return {
            msg: 'vue-bmap向你问好！',
            zoom: 19,
            center: [121.5273285, 31.21515044],
            icon: '/src/assets/imgs/location.png',
            width: 24,
            height: 40,
            data: [{
                geometry: {
                    type: 'Point',
                    coordinates: [121.5273285, 31.21515044],
                },
                properties: {
                    icon: '/src/assets/imgs/location.png'
                }
            }],
            current: {
                position: [121.5273285, 31.21515044],
                visible: false,
                content: 'hello world'
            },

        }
    },
    methods: {
        test(e) {
            const { point } = e
            const { lat, lng } = point
            this.current.visible = !this.current.visible
            // 在这里你可以做一些处理，比如将经纬度存储到data中，或者进行其他操作
            console.log(`点击位置的经度：${lng}，纬度：${lat}`)
        }
    }
}
</script>

<style>
.bmap-wrapper {
    width: 500px;
    height: 500px;
}
</style>
<template>
    <div id="app">
        <h3 class="title">{{ msg }}</h3>
        <div class="bmap-wrapper">
            <el-bmap class="bmap-box" :zoom="zoom" :center="center" :vid="'bmap-vue'" :tilt="73" :heading="90">
                <el-bmapv-view>
                    <el-bmapv-gltf-layer :scale="200" url="./assets/gltf/car2.gltf" :data="data"></el-bmapv-gltf-layer>
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
            data: [{
                geometry: {
                    type: 'Point',
                    coordinates: [121.5273285, 31.21515044],
                },
                angle: 0
            }]
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
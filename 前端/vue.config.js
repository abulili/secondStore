const { defineConfig } = require('@vue/cli-service')
const path = require('path');
module.exports = defineConfig({
  transpileDependencies: true,
  publicPath: './',
  assetsDir: './static',
  productionSourceMap: false,
  lintOnSave: false, // 是否开启eslint
  devServer: {
    proxy: {
      '/api': {
        target: 'http://localhost:80',
        changeOrigin: true, //这个是关键,看名字也知道,跨域的重点,允许代理服务器进行代理请求,
        //同源策略是浏览器的,所以服务器不存在这样的问题
        //因为这个/api是用来识别不同的target的标记,所以我们要替换它成空字符串,没有换的话,可以看cmd的proxy报错,路径问题
        pathRewrite: {
          '^/api': '',
        },
      },
    },
  },
  configureWebpack: {
    resolve: {
      alias: {
        '@': path.resolve(__dirname, 'src') // 确保路径正确指向了 'src' 目录  
      }
    }
  }
})

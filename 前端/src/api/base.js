import axios from 'axios'
import { Message } from 'element-ui'
import Cookies from 'js-cookie'
let baseUrl = 'http://localhost:80'
axios.defaults.headers['Content-Type'] = 'application/json;charset=utf-8'

const service = axios.create({
    // axios中请求配置有baseURL选项，表示请求URL公共部分
    baseURL: baseUrl,
    // 超时
    timeout: 1000 * 60 * 10,
})

// 请求拦截器
service.interceptors.request.use(
    (config) => {
        // let uid = Cookies.get('uid');
        // if (!uid) {
        //     alert('请先登录');
        //     return Promise.reject(new Error('用户未登录'));
        // }
        // console.log('config.url', config.url)
        if (config.url == 'http://localhost:80/store/addStore' || config.url == 'http://localhost:80/bought/addBought' || config.url == 'http://localhost:80/car/addCar' || config.url == 'http://localhost:80/comment/add' || config.url == 'http://localhost:80/reports/add' || config.url == 'http://localhost:80/stars/add') {
            let uid = Cookies.get('uid');
            if (!uid) {
                console.log('config', config)
                return Promise.reject(new Error('用户未登录'));
            }
        }
        return config
    },
    (error) => {
        return Promise.reject(error)
    }
)

// 响应拦截器
service.interceptors.response.use(
    (response) => {
        return response
    },
    (error) => {
        let { message } = error
        if (message === 'Network Error') {
            message = '后端接口连接异常'
        } else if (message.includes('timeout')) {
            message = '系统接口请求超时'
        }
        Message({
            message: message,
            type: 'error',
            duration: 5 * 1000,
        })
        return Promise.reject(error)
    }
)

export const base = baseUrl

export default service

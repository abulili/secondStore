import request, { base } from '@/api/base'
// 用户购买之后的评价，必须是买了之后才能添加
// 所有商品
export function _listBoughtAdmin(data) {
    return request({
        method: 'POST',
        url: `${base}/bought/list`,
        headers: {
            "Content-Type": "application/json;charset=utf-8",
        },
        data: data
    })
}
// 卖家被买家评论的列表
export function _listUserB(data) {
    return request({
        method: 'POST',
        url: `${base}/bought/listUser`,
        headers: {
            "Content-Type": "application/json;charset=utf-8",
        },
        data: data
    })
}
export function _listIsCom(data) {
    return request({
        method: 'POST',
        url: `${base}/bought/listIsCom`,
        headers: {
            "Content-Type": "application/json;charset=utf-8",
        },
        data: data
    })
}
// 购买过这个商品的人的评论
export function _listBought(data) {
    return request({
        method: 'POST',
        url: `${base}/bought/listBought`,
        headers: {
            "Content-Type": "application/json;charset=utf-8",
        },
        data: data
    })
}
// 买家给卖家增加评论
export function _addBought(data) {
    return request({
        method: 'POST',
        url: `${base}/bought/add`,
        headers: {
            "Content-Type": "application/json;charset=utf-8",
        },
        data: data
    })
}
// 应该是管理员的或者自己的去删除
export function _delBought(data) {
    return request({
        method: 'POST',
        url: `${base}/bought/addBought`,
        headers: {
            "Content-Type": "application/json;charset=utf-8",
        },
        data: data
    })
}
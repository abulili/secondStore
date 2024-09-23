import request, { base } from '@/api/base'
// 订单-购买的商品
// 卖家那边的
export function _listSale(data) {
    return request({
        method: 'POST',
        url: `${base}/order/listSale`,
        headers: {
            "Content-Type": "application/json;charset=utf-8",
        },
        data: data
    })
}
// 买家那边的
export function _listOrderBought(data) {
    return request({
        method: 'POST',
        url: `${base}/order/listBought`,
        headers: {
            "Content-Type": "application/json;charset=utf-8",
        },
        data: data
    })
}
// 购买的，可能没支付，状态传过来就行了
export function _addOrder(data) {
    return request({
        method: 'POST',
        url: `${base}/order/add`,
        headers: {
            "Content-Type": "application/json;charset=utf-8",
        },
        data: data
    })
}
export function _updateOrder(data) {
    return request({
        method: 'POST',
        url: `${base}/order/update`,
        headers: {
            "Content-Type": "application/json;charset=utf-8",
        },
        data: data
    })
}
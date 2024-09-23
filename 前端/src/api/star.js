import request, { base } from '@/api/base'
export function _listStar(data) {
    return request({
        method: 'POST',
        url: `${base}/stars/listUser`,
        headers: {
            "Content-Type": "application/json;charset=utf-8",
        },
        data: data
    })
}
export function _addStar(data) {
    return request({
        method: 'POST',
        url: `${base}/stars/add`,
        headers: {
            "Content-Type": "application/json;charset=utf-8",
        },
        data: data
    })
}
export function _storeStar(data) {
    return request({
        method: 'POST',
        url: `${base}/stars/storeStar`,
        headers: {
            "Content-Type": "application/json;charset=utf-8",
        },
        data: data
    })
}
export function _delStar(data) {
    return request({
        method: 'POST',
        url: `${base}/stars/del`,
        headers: {
            "Content-Type": "application/json;charset=utf-8",
        },
        data: data
    })
}
import request, { base } from '@/api/base'
// 查用户密码
export function _listId(data) {
    return request({
        method: 'POST',
        url: `${base}/user/listId`,
        headers: {
            "Content-Type": "application/json;charset=utf-8",
        },
        data: data
    })
}
// 查用户
export function _serachUser(data) {
    return request({
        method: 'POST',
        url: `${base}/user/serachUser`,
        headers: {
            "Content-Type": "application/json;charset=utf-8",
        },
        data: data
    })
}
export function _getAllUser(data) {
    return request({
        method: 'POST',
        url: `${base}/user/getAllUser`,
        headers: {
            "Content-Type": "application/json;charset=utf-8",
        },
        data: data
    })
}
export function _addUser(data) {
    return request({
        method: 'POST',
        url: `${base}/user/addUser`,
        headers: {
            "Content-Type": "application/json;charset=utf-8",
        },
        data: data
    })
}
export function _updateUser(data) {
    return request({
        method: 'POST',
        url: `${base}/user/updateUser`,
        headers: {
            "Content-Type": "application/json;charset=utf-8",
        },
        data: data
    })
}
export function _serachUid(data) {
    return request({
        method: 'POST',
        url: `${base}/user/serachUid`,
        headers: {
            "Content-Type": "application/json;charset=utf-8",
        },
        data: data
    })
}
export function _delUser(data) {
    return request({
        method: 'POST',
        url: `${base}/user/delUser`,
        headers: {
            "Content-Type": "application/json;charset=utf-8",
        },
        data: data
    })
}
export function _rePwd(data) {
    return request({
        method: 'POST',
        url: `${base}/user/rePwd`,
        headers: {
            "Content-Type": "application/json;charset=utf-8",
        },
        data: data
    })
}
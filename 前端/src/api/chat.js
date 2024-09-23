import request, { base } from '@/api/base'
// 详细聊天内容
export function _listChatUser(data) {
    return request({
        method: 'POST',
        url: `${base}/chat/listUser`,
        headers: {
            "Content-Type": "application/json;charset=utf-8",
        },
        data: data
    })
}
// 查未读的新信息
export function _listNew(data) {
    return request({
        method: 'POST',
        url: `${base}/chat/listNew`,
        headers: {
            "Content-Type": "application/json;charset=utf-8",
        },
        data: data
    })
}
// 增加聊天记录
export function _addChat(data) {
    return request({
        method: 'POST',
        url: `${base}/chat/add`,
        headers: {
            "Content-Type": "application/json;charset=utf-8",
        },
        data: data
    })
}
// 点进去触发已读
export function _updateChat(data) {
    return request({
        method: 'POST',
        url: `${base}/chat/update`,
        headers: {
            "Content-Type": "application/json;charset=utf-8",
        },
        data: data
    })
}
// 全部已读
export function _updateAllChat(data) {
    return request({
        method: 'POST',
        url: `${base}/chat/updateAll`,
        headers: {
            "Content-Type": "application/json;charset=utf-8",
        },
        data: data
    })
}
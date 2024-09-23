import request, { base } from '@/api/base'
export function _listComment(data) {
    return request({
        method: 'POST',
        url: `${base}/comment/list`,
        headers: {
            "Content-Type": "application/json;charset=utf-8",
        },
        data: data
    })
}
// 卖家下的（商品页）
export function _listCommentUser(data) {
    return request({
        method: 'POST',
        url: `${base}/comment/listUser`,
        headers: {
            "Content-Type": "application/json;charset=utf-8",
        },
        data: data
    })
}
export function _listCommentSuid(data) {
    return request({
        method: 'POST',
        url: `${base}/comment/listSuid`,
        headers: {
            "Content-Type": "application/json;charset=utf-8",
        },
        data: data
    })
}
// 商品详情页的
export function _listCommentStore(data) {
    return request({
        method: 'POST',
        url: `${base}/comment/listStore`,
        headers: {
            "Content-Type": "application/json;charset=utf-8",
        },
        data: data
    })
}
// 评论和商品一起查
export function _CommentUser(data) {
    return request({
        method: 'POST',
        url: `${base}/comment/CommentUser`,
        headers: {
            "Content-Type": "application/json;charset=utf-8",
        },
        data: data
    })
}
// 增加评论
export function _addComment(data) {
    return request({
        method: 'POST',
        url: `${base}/comment/add`,
        headers: {
            "Content-Type": "application/json;charset=utf-8",
        },
        data: data
    })
}
// 删除评论
export function _delComment(data) {
    return request({
        method: 'POST',
        url: `${base}/comment/del`,
        headers: {
            "Content-Type": "application/json;charset=utf-8",
        },
        data: data
    })
}
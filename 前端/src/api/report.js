import request, { base } from '@/api/base'
// 举报信息
export function _listReport(data) {
    return request({
        method: 'POST',
        url: `${base}/reports/list`,
        headers: {
            "Content-Type": "application/json;charset=utf-8",
        },
        data: data
    })
}
export function _addReport(data) {
    return request({
        method: 'POST',
        url: `${base}/reports/add`,
        headers: {
            "Content-Type": "application/json;charset=utf-8",
        },
        data: data
    })
}
export function _delReport(data) {
    return request({
        method: 'POST',
        url: `${base}/reports/del`,
        headers: {
            "Content-Type": "application/json;charset=utf-8",
        },
        data: data
    })
}
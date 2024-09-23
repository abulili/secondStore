import request, { base } from '@/api/base'
export function _listCarId(data) {
    return request({
        method: 'POST',
        // url: base + '/car/listId',
        url: `${base}/car/listId`,
        headers: {
            "Content-Type": "application/json;charset=utf-8",
        },
        data: data
    })
}
export function _delCar(data) {
    return request({
        method: 'POST',
        url: base + '/car/delCar',
        headers: {
            "Content-Type": "application/json;charset=utf-8",
        },
        data: data
    })
}
export function _addCar(data) {
    return request({
        method: 'POST',
        url: base + '/car/addCar',
        headers: {
            "Content-Type": "application/json;charset=utf-8",
        },
        data: data
    })
}
export function _updateCar(data) {
    return request({
        method: 'POST',
        url: base + '/car/updateCar',
        headers: {
            "Content-Type": "application/json;charset=utf-8",
        },
        data: data
    })
}
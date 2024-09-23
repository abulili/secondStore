import request, { base } from '@/api/base'
export function _serach(data) {
    return request({
        method: 'POST',
        url: `${base}/store/serach`,
        headers: {
            "Content-Type": "application/json;charset=utf-8",
        },
        data: data
    })
}
export function _listPrice(data) {
    return request({
        method: 'POST',
        url: `${base}/store/listPrice`,
        headers: {
            "Content-Type": "application/json;charset=utf-8",
        },
        data: data
    })
}
export function _listId(data) {
    return request({
        method: 'POST',
        url: `${base}/store/listId`,
        headers: {
            "Content-Type": "application/json;charset=utf-8",
        },
        data: data
    })
}
export function _listUser(data) {
    return request({
        method: 'POST',
        url: `${base}/store/listUser`,
        headers: {
            "Content-Type": "application/json;charset=utf-8",
        },
        data: data
    })
}
export function _listAll(data) {
    return request({
        method: 'POST',
        url: `${base}/store/list`,
        headers: {
            "Content-Type": "application/json;charset=utf-8",
        },
        data: data
    })
}
export function _addStore(data) {
    return request({
        method: 'POST',
        url: `${base}/store/addStore`,
        headers: {
            "Content-Type": "application/json;charset=utf-8",
        },
        data: data
    })
}
export function _updateStore(data) {
    return request({
        method: 'POST',
        url: `${base}/store/updateStore`,
        headers: {
            "Content-Type": "application/json;charset=utf-8",
        },
        data: data
    })
}
export function _delStore(data) {
    return request({
        method: 'POST',
        url: `${base}/store/delStore`,
        headers: {
            "Content-Type": "application/json;charset=utf-8",
        },
        data: data
    })
}

export function _getL(data) {
    return request({
        method: 'POST',
        url: `${base}/map/getL`,
        headers: {
            "Content-Type": "application/json;charset=utf-8",
        },
        data: data
    })
}

export function _getR(data) {
    return request({
        method: 'POST',
        url: `${base}/map/getR`,
        headers: {
            "Content-Type": "application/json;charset=utf-8",
        },
        data: data
    })
}

export function _getLDB(data) {
    return request({
        method: 'POST',
        url: `${base}/store/getLDB`,
        headers: {
            "Content-Type": "application/json;charset=utf-8",
        },
        data: data
    })
}

import request from '@/utils/request'

// 发送请求，获取预约数据
export function getAppoints(listQuery) {
    return request({
        url: '/maintain/getAppoints',
        method: 'get',
        params: listQuery,
    })
}

// 发起请求，获取维修师技能信息
export function getSkills(params) {
    return request({
        method: 'get',
        url: '/maintain/getSkills',
        params: params
    })
}

// 发起请求，获取维修师证书信息
export function getCertificates(params){
    return request({
        method: 'get',
        url: '/maintain/getCertificates',
        params: params
    })
}

// 发起请求，获取业务范围信息
export function getScopes(params){
    return request({
        method: 'get',
        url: '/maintain/getScopes',
        params: params
    })
}

// 发起请求，获取业务范围信息
export function getConsults(params){
    return request({
        method: 'get',
        url: '/maintain/getConsults',
        params: params
    })
}

// 发起请求，获取文章信息
export function getArticles(params){
    return request({
        method: 'get',
        url: '/maintain/getArticles',
        params: params
    })
}
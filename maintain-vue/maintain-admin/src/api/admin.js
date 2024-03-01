import request from '@/utils/request'

// 发送请求，获取用户数据
export function getUsers(params) {
    return request({
        url: '/admin/getUsers',
        method: 'get',
        params: params,
    })
}

// 发送请求，获取咨询数据
export function getConsults(params) {
    return request({
        url: '/admin/getConsults',
        method: 'get',
        params: params,
    })
}

// 发送请求，获取文章数据
export function getArticles(params) {
    return request({
        url: '/admin/getArticles',
        method: 'get',
        params: params,
    })
}

// 发送请求，获取反馈数据
export function getFeedbacks(params) {
    return request({
        url: '/admin/getFeedbacks',
        method: 'get',
        params: params,
    })
}

// 发送请求，获取举报数据
export function getReports(params) {
    return request({
        url: '/admin/getReports',
        method: 'get',
        params: params,
    })
}

// 发送请求，获取分类数据
export function getSorts(params) {
    return request({
        url: '/admin/getSorts',
        method: 'get',
        params: params,
    })
}

// 发送请求，获取待审核证书数据
export function getAuditCertificates(params) {
    return request({
        url: '/admin/getAuditCertificates',
        method: 'get',
        params: params,
    })
}

// 发送请求，获取待评分数据
export function getScores(params) {
    return request({
        url: '/admin/getScores',
        method: 'get',
        params: params,
    })
}
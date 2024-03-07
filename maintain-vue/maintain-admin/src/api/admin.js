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

// 发送请求，添加用户
export function addUser(params) {
    return request({
        url: '/admin/user',
        method: 'post',
        data: params,
    })
}

// 发送请求，修改用户
export function updateUser(params) {
    return request({
        url: '/admin/user',
        method: 'put',
        data: params,
    })
}

// 发送请求，删除用户
export function deleteUser(id) {
    return request({
        url: `/admin/user/${id}`,
        method: 'delete'
    })
}

// 发送请求，删除咨询
export function deleteConsult(id) {
    return request({
        url: `/admin/consult/${id}`,
        method: 'delete'
    })
}

// 发送请求，删除文章
export function deleteAdminArticle(id) {
    return request({
        url: `/admin/article/${id}`,
        method: 'delete'
    })
}

// 发送请求，删除反馈
export function deleteAdminFeedback(id) {
    return request({
        url: `/admin/feedback/${id}`,
        method: 'delete'
    })
}

// 发送请求，删除举报信息
export function deleteAdminReport(id) {
    return request({
        url: `/admin/report/${id}`,
        method: 'delete'
    })
}

// 发送请求，添加分类
export function addAdminSort(sort) {
    return request({
        url: `/admin/sort`,
        method: 'post',
        data: sort
    })
}

// 发送请求，编辑分类
export function updateAdminSort(sort) {
    return request({
        url: `/admin/sort`,
        method: 'put',
        data: sort
    })
}

// 发送请求，删除分类
export function deleteAdminSort(id) {
    return request({
        url: `/admin/sort/${id}`,
        method: 'delete'
    })
}

// 发送请求，通过文章
export function passArticle(id) {
    return request({
        url: `/admin/passArticle/${id}`,
        method: 'put'
    })
}

// 发送请求，拒绝文章
export function downArticle(article) {
    return request({
        url: `/admin/downArticle`,
        method: 'delete',
        data: article
    })
}

// 发送请求，通过证书
export function passCertificate(id) {
    return request({
        url: `/admin/passCertificate/${id}`,
        method: 'put'
    })
}

// 发送请求，拒绝证书
export function downCertificate(id) {
    return request({
        url: `/admin/downCertificate/${id}`,
        method: 'delete',
    })
}

// 发送请求，添加分数
export function addAdminScore(from) {
    return request({
        url: `/admin/score`,
        method: 'post',
        data: from
    })
}

// 发送请求，修改分数
export function updateAdminScore(from) {
    return request({
        url: `/admin/score`,
        method: 'put',
        data: from
    })
}

// 发送请求，删除分数
export function deleteAdminScore(id) {
    return request({
        url: `/admin/score/${id}`,
        method: 'delete'
    })
}
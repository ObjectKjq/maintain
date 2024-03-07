import request from '@/utils/request'

// 处理时间函数
export function formatDate (d) {
    var date = new Date(d);
    var YY = date.getFullYear() + '-';
    var MM =
      (date.getMonth() + 1 < 10
        ? '0' + (date.getMonth() + 1)
        : date.getMonth() + 1) + '-';
    var DD = date.getDate() < 10 ? '0' + date.getDate() : date.getDate();
    var hh =
      (date.getHours() < 10 ? '0' + date.getHours() : date.getHours()) + ':';
    var mm =
      (date.getMinutes() < 10 ? '0' + date.getMinutes() : date.getMinutes()) +
      ':';
    var ss =
      date.getSeconds() < 10 ? '0' + date.getSeconds() : date.getSeconds();
    return YY + MM + DD + ' ' + hh + mm + ss;
} 

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

// 删除预约信息
export function deleteAppoint(appoint){
    return request({
        method: 'delete',
        url: '/maintain/appoint',
        params: appoint,
    })
}

// 修改预约信息
export function updateAppoint(appoint){
    return request({
        method: 'put',
        url: '/maintain/appoint',
        params: appoint,
    })
}

// 添加预约信息
export function addAppoint(appoint){
    return request({
        method: 'post',
        url: '/maintain/appoint',
        params: appoint,
    })
}

// 添加预约成功后的价格
export function addPrice(appoint){
    return request({
        method: 'put',
        url: '/maintain/addPrice',
        params: appoint,
    })
}

// 提交订单
export function subAppoint(appoint){
    return request({
        method: 'put',
        url: '/maintain/subAppoint',
        params: appoint,
    })
}

// 提交订单
export function getSort(){
    return request({
        method: 'get',
        url: '/maintain/getSort'
    })
}

// 添加文章
export function addArticle(article){
    return request({
        method: 'post',
        url: '/maintain/addArticle',
        data: article,
    })
}

// 获取文章数据
export function getArticle(id){
    return request({
        method: 'get',
        url: `/maintain/getArticle/${id}`,
    })
}

// 修改文章信息
export function updateArticle(article){
    return request({
        method: 'put',
        url: '/maintain/updateArticle',
        data: article,
    })
}

// 删除文章信息
export function deleteArticle(id){
    return request({
        method: 'put',
        url: `/maintain/deleteArticle/${id}`,
    })
}

// 添加技能信息
export function addSkill(form){
    return request({
        method: 'post',
        url: `/maintain/skill`,
        data: form
    })
}

// 修改技能信息
export function updateSkill(form){
    return request({
        method: 'put',
        url: `/maintain/skill`,
        data: form,
    })
}

// 修改技能信息
export function deleteSkill(id){
    return request({
        method: 'delete',
        url: `/maintain/skill/${id}`,
    })
}

// 添加证书信息
export function addCertificate(form){
    return request({
        method: 'post',
        url: `/maintain/certificate`,
        data: form
    })
}

// 删除证书信息
export function deleteCertificate(id){
    return request({
        method: 'delete',
        url: `/maintain/certificate/${id}`,
    })
}

// 添加城市信息
export function addScope(form){
    return request({
        method: 'post',
        url: `/maintain/scope`,
        data: form,
    })
}

// 修改城市信息
export function updateScope(form){
    return request({
        method: 'put',
        url: `/maintain/scope`,
        data: form,
    })
}

// 删除城市信息
export function deleteScope(id){
    return request({
        method: 'delete',
        url: `/maintain/scope/${id}`,
    })
}

// 回复信息
export function reply(form){
    return request({
        method: 'post',
        url: `/maintain/reply`,
        data: form
    })
}

// 回复信息
export function updateUser(form){
    return request({
        method: 'put',
        url: `/updateMaintainAdminUser`,
        data: form
    })
}
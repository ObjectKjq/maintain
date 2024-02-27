import store from '../store/index'

// 暴露登录接口
export const login = () => {
    uni.login({
        provider: 'weixin', //使用微信登录
        success: async function (loginRes) {
            const {data: res} = await uni.$http.post('/signin', {
                code: loginRes.code,
            })
            // 请求成功
            if(res.code == 20000){
                getUser(res.data)
                return uni.$showMsg('登录成功')
            // 请求失败
            }else{
                return uni.$showMsg('登录失败')
            }
        }
    });
}

// 获取用户信息
export const getUser = (token) => {
    uni.$http.get('/info', {
        token,
    }).then((res) =>{
        // 添加用户信息和token到vuex中
        let user = {...res.data.data, "token": token}
        store.commit('ADDUSER', user)
    })
    
}



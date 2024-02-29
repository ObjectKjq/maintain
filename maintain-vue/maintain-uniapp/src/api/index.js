import store from '../store/index'

// 暴露登录接口
export const login = () => {
    uni.login({
        provider: 'weixin', //使用微信登录
        success: async function (loginRes) {
            const {data: res} = await uni.$http.post('/signin', {
                code: loginRes.code,
            },)
            getUser(res.data)
        }
    });
}

// 获取用户信息
export const getUser = (account) => {
    // 先获取token
    uni.$http.post('/login', {
        username: account,
        password: "123",
    }).then((res1)=>{
        console.log()
        uni.$http.get('/info', {
            token: res1.data.data.token
        }).then((res) =>{
            // 添加用户信息和token到vuex中
            let user = {...res.data.data, "token": res1.data.data.token}
            store.commit('ADDUSER', user)
            // 请求成功
            if(res.data.code == 20000){
                return uni.$showMsg('登录成功')
            // 请求失败
            }else{
                return uni.$showMsg('登录失败')
            }
        })
    })
}



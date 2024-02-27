import Vuex from 'vuex'
import Vue from 'vue'

// 应用vuex插件
Vue.use(Vuex)

//准备actions-用于响应组件中的动作
const actions = {}

//mutations-用于操作数据
const mutations = {
    // 添加user数据
    ADDUSER(state, user){
        // console.log(user)
        state.user = user
    }
}

//准备state-存储数据
const state = {
    user: {},
}

//创建store,并导出
export default new Vuex.Store({
    actions,
    mutations,
    state
})
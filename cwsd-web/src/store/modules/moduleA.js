
export default {
//必修加
    namespaced:true,
  //前端数据库中共享的数据
  state: {
    count:5
  },
  //get方法,可以做一些初始化动作
  getters:{

  },
  //set方法修改state数据
  mutations: {
    increment(state,payload){
      state.count += payload;
    },
  },
  //预处理数据，让后再存进数据,其他定义的方法
  actions: {

  },
  //模块化
  modules: {
  }
}

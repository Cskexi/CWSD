import Vue from 'vue'
import Vuex from 'vuex'
import ma from './modules/moduleA'
import mb from './modules/moduleB'
Vue.use(Vuex)

export default new Vuex.Store({
  //前端数据库中共享的数据
    state: {

    storeId:"",
    user:[
    ],
    count:1,
    list:[
    ],
    rootCount:100,
    //字典，后端获取
    //字典结构：字典类型value：具体的字典，字典类型value：具体的字典
    //F.E: sex=>[{1:boy,0:girl,2:unkown,3:secret}],state=>[{0:不可用，1：可用}]
    dicMap:{},
    // [{
    //   key:"sex",
    //   value:[{
    //     name:"boy",
    //     value:1
    //   },{
    //     name:"gril",
    //     value:0
    //   }]
    //   },{
    //   key:"state",
    //   value:[{
    //     name:"不可用",
    //     value:1
    //   },{
    //     name:"可用",
    //     value:0
    //   },]
    // }],
    //list:[]
    // list:[
    //   {
    //     name:"xmut",
    //     sex:1,
    //     age:42
    //   },
    //   {
    //     name:"fzu",
    //     sex:0,
    //     age:421
    //   },
    // ]
  },
  //get方法,可以做一些初始化动作
  getters:{
    getUser(state){
      return "123"
    },
    getCount(state){
      return state.rootCount
    },
    getList(state){
      let lst = state.list
      lst.forEach(item=>{
        item.sexName = item.sex=='1'?'boy':'girl'
      })
      return lst
      },
      getUserId(state){
        return state.userId
      },
      getStoreId(state){
        return state.storeId
      },
    
  },
  //set方法修改state数据
  mutations: {
    setUser(state,user){
      state.user=user
    },
    setList(state,list){
      state.list = list
    },
    setDicMap(state,map){
      state.dicMap = map
      },
      setUserId(state,userId){
        state.userId = userId
      },
      setStoreId(state, storeId) {
        state.storeId = storeId;
      }
  },
  //预处理数据，让后再存进数据,其他定义的方法
  actions: {
  },
  //模块化
  modules: {
    ma,
    mb,
  }
})


export default {
        //必修加
        namespaced:true,
    //前端数据库中共享的数据
    state: {
        list:[]
    },
    //get方法,可以做一些初始化动作
    getters:{
        filterList(state,getters){
            console.log(state.List)
            return state.list.map(item=>{
              item.sexName = item.sex=='1'?'男':'女'
              return item;
            })
          }
    },
    //set方法修改state数据
    mutations: {
        updateList(state,payload){

            state.list = payload;
          }
    },
    //预处理数据，让后再存进数据,其他定义的方法
    actions: {
        initList({commit},payload){
            //commit('setList',list)
            return  new Promise(rs =>{
              let newList =[
                    {
                      name:"张三",
                      sex:1,
                      id:1
                    },
                    {
                      name:"李四",
                      sex:2,
                      id:2
                    },
                  ]
                  if(3==payload){
                    console.log(4)    
                    newList =[
                      {
                        name:"张三",
                        sex:1,
                        id:1
                      },
                      {
                        name:"李四",
                        sex:2,
                        id:2
                      },
                      {
                        name:"王五",
                        sex:1,
                        id:3
                      },
                    ]
                  }
             commit("updateList",newList);
             rs(); 
          })},
    },
    //模块化
    modules: {
    }
  }
  
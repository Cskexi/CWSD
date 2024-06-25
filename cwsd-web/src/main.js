import Vue from 'vue'
import App from './App.vue'
import './registerServiceWorker'
import router from './router'
import store from './store'

import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
import axios from 'axios'

//import global
import global from '@/global'
//文件定义标记为MYGL 别名
//import mixin
import myMixin from '@/lib/mixin'

import { dictypeGetAllMap } from "@/api/modules/dictype"


Vue.prototype.MYGL = global
Vue.mixin(myMixin)


//axios
axios.defaults.baseURL="/api"

Vue.use(ElementUI,{
  size:'small',
  zIndex:300
});
Vue.config.productionTip = false

// Vue.directive("foc",{
//   inserted:function(el){
//     el.focus()
//   }
// })

Vue.directive("focus",{
  inserted:function(el){
    el.focus()
  }
})

new Vue({
  router,
  store,

  // mounted(){
  //   dictypeGetAllMap().then(result=>{
  //     this.$store.commit('setDicMap',result.data)
  //     //console.log(result.data)
  //   }).catch(err=>{
  //     console.log(err)
  //   })
  // },

  render: h => h(App)
}).$mount('#app')

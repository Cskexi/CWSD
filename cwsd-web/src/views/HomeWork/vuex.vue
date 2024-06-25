<template>
  <div>
    <p>{{ count1 }}</p>
    <button @click="add(1)">+1</button>
    <ul>
      <li v-for="item in filterList" :key="item.id">{{ item.name }}--{{ item.sexName }}</li>
    </ul>
    <button @click="getData(3)"> 获取3条数据</button>

    <h1>This is an Vuex page</h1><br>
<!-- 
    请输入提示信息：<input type="text" id="myTxt" v-foc> -->
    <!-- <ol>
      <li v-for="item in list">{{ item.name }}--{{ item.sexName }}--{{ item.age }}</li>
    </ol>
    <button @click="showRootCount">显示RootCount</button>
    <button @click="showMaCount">显示ma中Count</button>
    <button @click="showRootCountByGets">通过GETs显示RootCount</button>

    <button @click="updateList">修改List</button>
    <button @click="initList">初始化List</button>

    <select v-model="elsv">
            <option
            v-for="item in sexAry"
            :value="item.value">{{ item.name }}
            </option>
      </select> -->
  </div>
</template>

``
<script>
import { dictypeGetAllMap } from "@/api/modules/dictype"
export default {
  name: 'vuex',
  //局部指令，只在当前页面使用
  directives:{
    // foc:{
    //   inserted:function(el){
    //     //指令需要完成的内容
    //     el.focus()
    //   }
    // }
  },  
  data(){
    return{
      filterList:[],
      list:[],
      elsv:"",
      //这个后端数据库中获取的
      sexAry: []
      // [{
      //     value: '选项1',
      //     label: '黄金糕'
      //   }, {
      //     value: '选项2',
      //     label: '双皮奶'
      //   }, {
      //     value: '选项3',
      //     label: '蚵仔煎'
      //   }, {
      //     value: '选项4',
      //     label: '龙须面'
      //   }, {
      //     value: '选项5',
      //     label: '北京烤鸭'
      //   }],
    }   
  },
  computed:{
    count1(){
      return this.$store.state.ma.count;
    },
  },
  mounted(){
    this.$store.dispatch("mb/initList");
    this.filterList  = this.$store.getters['mb/filterList']; 
    //document.getElementById("myTxt").focus()

    //从后台获取数据，然后通过mutations方法赋值到state里头

      //通过key = sex 从vuex的STate里的dicMap把sex对应字典加载
      // this.sexAry = this.getDicMap(this.$store.state.dicMap,this.gl_sex)
      // //更新当前vue页面中的list
      // this.list = this.$store.getters['getList']

  },    
  methods:{
    getData(val){

      this.$store.dispatch("mb/initList",val);
      this.filterList  = this.$store.getters['mb/filterList']; 
    },
    add(val){
      this.$store.commit('ma/increment',val)
    },
    // showRootCount(){
    //   console.log(this.$store.state.rootCount)
    // },
    // showMaCount(){
    //   console.log(this.$store.state.ma.count)
    // },
    // showRootCountByGets(){
    //   console.log(this.$store.getters['getCount'])//模块化'ma/getCount'
    // },
    // updateList(){
    //   let list=[{
    //     name:"xmut1",
    //     sex:1,
    //     age:421
    //   },
    //   {
    //     name:"fzu1",  
    //     sex:0,
    //     age:4211
    //   },]
    //   //更新vuex中的list
    //   this.$store.commit('setList',list)
    //   //更新当前vue页面中的list
    //   this.list = this.$store.getters['getList']

    // },
    // initList(){
    //   let list=[{
    //     name:"xmut2",
    //     sex:1,
    //     age:422
    //   },
    //   {
    //     name:"fzu2",
    //     sex:0,
    //     age:4212
    //   },
    //   {
    //     name:"c++",
    //     sex:0,
    //     age:4
    //   },]
    //   this.$store.dispatch('initList',list)
    //         //更新当前vue页面中的list
    //         this.list = this.$store.getters['getList']
    // }
  }
}
</script>
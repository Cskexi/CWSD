<template>
    <div class="myRouter">
        <h1>myComponent</h1>
        <button @click="bFlag=true">显示</button>
        <component1 v-if="bFlag" :msg="msgFather" :obj="objFather" :ary="aryFather" :age="ageFather" @cb="cbFather"></component1>
        冒号传值，@传回调函数xxxxx
    </div>
  </template>
  
  <script>
import  component1  from '@/components/component1';
  export default {
    name: 'myComponent',
    components:{component1},
    data(){ 
        return{
          bFlag:false,
          msgFather:"xmut",
          objFather:{
            id:"1234",
            name:"21soft"
          },
          aryFather:['a','b','c'],
          ageFather:200
        }
    },
    methods:{
      cbFather(val){
        this.bFlag=val
        console.log(this.bFlag)
      }
    }
  }
  </script>
  